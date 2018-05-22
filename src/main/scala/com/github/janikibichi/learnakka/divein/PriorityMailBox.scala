package com.github.janikibichi.learnakka.divein

import akka.actor.{Actor, ActorSystem, Props}
import akka.dispatch.{PriorityGenerator, UnboundedPriorityMailbox}
import com.typesafe.config.Config

object PriorityMailBox extends App{
  val actorSystem = ActorSystem("PriorityMailbox")
  val ourPriorityActor = actorSystem.actorOf(Props[PriorityActor].withDispatcher("prio-dispatcher"))

  ourPriorityActor ! 6.0
  ourPriorityActor ! 1
  ourPriorityActor ! 5.0
  ourPriorityActor ! 3
  ourPriorityActor ! "Hello"
  ourPriorityActor ! 5
  ourPriorityActor ! "I am priority actor"
  ourPriorityActor ! "I process string messages first, then integer, long and others"
}

//Create an Actor that receives any message type
class PriorityActor extends Actor {
  def receive: PartialFunction[Any,Unit] ={
    //Message type Int
    case x: Int => println(x)

    //Message type String
    case x: String => println(x)

    //Message type Long
    case x: Long => println(x)

    //Other Messages
    case x => println(x)
  }
}

//Create a priority mailbox
class PriorityActorMailBox(settings: ActorSystem.Settings, config: Config) extends UnboundedPriorityMailbox(
  //Create PriorityGenerator, low prio means more important
  PriorityGenerator{
    //Int Messages
    case x: Int => 1

      //String Messages
    case x: String => 0

      //Long Messages
    case x: Long => 2

      //Other Messages
    case _ => 3
  }
)