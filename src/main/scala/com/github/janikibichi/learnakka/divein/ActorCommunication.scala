package com.github.janikibichi.learnakka.divein

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.util.Random._

object Messages{
  case class Done(randomNumber: Int)
  case object GiveMeARandomNumber
  case class Start(actorRef: ActorRef)
}

object ActorCommunication extends App{
  import Messages._

  val actorSystem = ActorSystem("2WayCommunication")

  //Create the actor in the Actor System
  val randomNumberGeneratorActor = actorSystem.actorOf(Props[RandomNumberGeneratorActor],"randomNumberGeneratorActor")
  val queryActor = actorSystem.actorOf(Props[QueryActor],"queryActor")

  //Show Actor Addresses
  println(randomNumberGeneratorActor.path)
  println(queryActor.path)

  //Send a Message to Start the Query Actor
  queryActor ! Start(randomNumberGeneratorActor)
}

//Actor that generates a random number and sends it back to the Query Actor
class RandomNumberGeneratorActor extends Actor{
  import Messages._

  override def receive: Receive = {
    case GiveMeARandomNumber =>
      println("Received a message to generate a random integer")
      val randomNumber = nextInt
      sender ! Done(randomNumber)
  }
}

//Actor that requests for a random number from RandomNumberGeneratorActor by sending a message
class QueryActor extends Actor{
  import Messages._

  override def receive: Receive = {
    case Start(actorRef) =>
      println(s"Hey RandomGeneratorGuy, send me a random number")
      actorRef ! GiveMeARandomNumber

    case Done(randomNumber) =>
      println(s"Received a random number $randomNumber")
  }
}