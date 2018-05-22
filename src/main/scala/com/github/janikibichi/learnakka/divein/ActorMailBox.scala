package com.github.janikibichi.learnakka.divein

import java.util.concurrent.ConcurrentLinkedQueue
import akka.actor.{Props, Actor, ActorSystem, ActorRef}
import akka.dispatch.{MailboxType, ProducesMessageQueue, Envelope, MessageQueue}
import com.typesafe.config.Config


object ActorMailBox extends App{
  val actorSystem = ActorSystem("ActorMailBox")

  //Create Actors from ActorSystem
  val actor = actorSystem.actorOf(Props[SpecialActor].withDispatcher("custom-dispatcher"))

  val actor1 = actorSystem.actorOf(Props[AnActor],"WhatGoingOn")

  val actor2 = actorSystem.actorOf(Props[AnActor],"MyActor")

  actor1 ! ("hello",actor)
  actor2 ! ("hello",actor)
}

//Actor that uses created configuration at src/main/resources/application.conf
class SpecialActor extends Actor {
  override def receive: Receive = {
    case msg: String => println(s"msg is $msg")
  }
}

//Actor that tries to talk to the Special Actor
class AnActor extends Actor {
  override def receive: Receive ={
    case (msg: String, actorRef: ActorRef) =>
      actorRef ! msg

    case msg => println(msg)
  }
}

//Create a MessageQueue
class MyMessageQueue extends MessageQueue{
  private final val queue = new ConcurrentLinkedQueue[Envelope]()

  def enqueue(receiver: ActorRef, handle: Envelope): Unit = {
    if(handle.sender.path.name == "MyActor"){
      handle.sender ! "Hey dude, How are you? Processing!"
      queue.offer(handle)
    }
    else handle.sender ! "I don't talk to strangers. No processing!"
  }

  def dequeue(): Envelope = queue.poll()

  def numberOfMessages: Int = queue.size()

  def hasMessages: Boolean = !queue.isEmpty

  def cleanUp(owner: ActorRef, deadLetters: MessageQueue): Unit ={
    while (hasMessages){
      deadLetters.enqueue(owner,dequeue())
    }
  }
}

//Custom mailbox implementation
class MyUnboundedMailbox extends MailboxType with ProducesMessageQueue[MyMessageQueue]{

  def this(settings: ActorSystem.Settings, config: Config) ={
    this()
  }

  //Create method called to create MessageQueue
  final override def create(owner: Option[ActorRef], system: Option[ActorSystem]): MessageQueue = new MyMessageQueue()
}