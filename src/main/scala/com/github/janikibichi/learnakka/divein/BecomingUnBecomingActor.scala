package com.github.janikibichi.learnakka.divein

import akka.actor.{Props,ActorSystem,Actor}

object BecomingUnBecomingActor extends App{
  val actorSystem = ActorSystem("BecomeUnBecome")

  //Create and Define an Actor, Add it to the ActorSystem
  val becomeUnBecome = actorSystem.actorOf(Props[BecomeUnBecomeActor],"BecomeUnBecome")

  //Send a message to the actor
  becomeUnBecome ! true
  becomeUnBecome ! "Hello How are you?"
  becomeUnBecome ! false
  becomeUnBecome ! 1100
  becomeUnBecome ! true
  becomeUnBecome ! "What do u do?"
}

class BecomeUnBecomeActor extends Actor {
  def receive: Receive = {
    case true => context.become(isStateTrue)
    case false => context.become(isStateFalse)
    case _ => println("I don't know what you want to say!")
  }
  def isStateTrue: Receive ={
    case msg: String => println(s"$msg")
    case false => context.become(isStateFalse)
  }
  def isStateFalse: Receive = {
    case msg: Int => println(s"$msg")
    case true => context.become(isStateTrue)
  }
}

