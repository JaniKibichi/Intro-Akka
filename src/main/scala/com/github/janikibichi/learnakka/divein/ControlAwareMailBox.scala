package com.github.janikibichi.learnakka.divein

import akka.dispatch.ControlMessage
import akka.actor.{Props, Actor, ActorSystem}

case object NewControlMessage extends ControlMessage

object ControlAwareMailBox extends App{
  val actorSystem = ActorSystem("ControlMailbox")

  //Add and define an Actor to the ActorSystem
  val actor = actorSystem.actorOf(Props[Logger].withDispatcher("control-aware-dispatcher"))
  actor ! "hello"
  actor ! "how are you?"
  actor ! "you?"
  actor ! NewControlMessage
}

//Define And Actor
class Logger extends Actor{
  def receive ={
    case NewControlMessage => println("Oh, I have to process Control message first")
    case x => println(x.toString)
  }
}
