package com.github.janikibichi.learnakka.divein

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

object BehaviorAndState extends App {
  val actorSystem = ActorSystem("HelloAkka")

  //Create the actor in the actor system. Note that The Summing Actor is an Object
  val sumThem = actorSystem.actorOf(Props[TheSummingActor], "theSummingActor")

  //Show the Actor Address
  println(sumThem.path)
}

class TheSummingActor extends Actor{
  //Actor state
  var theSum= 0

  //Actor Behavior
  override def receive: Receive = {
    //Receive a message as integer
    case x: Int =>
      theSum = theSum + x
      println(s"here is my state $theSum")

    //Receive a defaul message
    case _ =>
      println("This is the default message")
  }
}
