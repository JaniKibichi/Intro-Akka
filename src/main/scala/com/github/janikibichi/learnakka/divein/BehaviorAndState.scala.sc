package com.github.janikibichi.learnakka.divein

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

object BehaviorAndState extends App {
  val actorSystem = ActorSystem("HelloAkka")

  //Create the actor in the actor system. Note that The Summing Actor is an Object
  val sumThem    = actorSystem.actorOf(Props[TheSummingActor], "theSummingActor")

  //Note how we Create the Actor with an initial sum of 1000
  val consTheSum = actorSystem.actorOf(Props(classOf[TheSummingActorWithConstructor],1000),"ConsSumActor")

  //Show the Actor Address
  println(sumThem.path)
  println(consTheSum.path)
}

//Create Actor Class
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

//Create Actor Class
class TheSummingActorWithConstructor(initialSum: Int) extends Actor{
  //Actor State
  var consSum = 0

  //Actor Behavior
  override def receive: Receive = {
    //Receive a message as integer
    case x: Int =>
      consSum = initialSum + consSum + x
      println(s"The new state of consSum is $consSum")

    //Receive default message
    case _ =>
      println("This is the constructor default message")
  }
}