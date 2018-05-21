package com.github.janikibichi.learnakka.divein


import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

object SendMessage extends App{
  val actorSystem = ActorSystem("SendMessage")

  //Create the actor in the actorsystem.
  val sumThem = actorSystem.actorOf(Props[SendAndSumActor],"SendAndSumActor")

  //Note how we create the Actor with an initial sum of 1000
  val sumThemWithCons = actorSystem.actorOf(Props(classOf[SendAndSumWithConstructor],1000),"SendAndSumWithConstructor")

  //Show the Actor Address
  println(sumThem.path)
  println(sumThemWithCons.path)

  //Send the Message
  sumThem ! 1
  sumThemWithCons ! 10
}

//Create Actor Class
class SendAndSumActor extends Actor{
  //Actor State
  var sum = 0

  //Actor behavior
  override def receive: Receive = {
    //Receive Message as integer
    case x: Int =>
      sum = sum + x
      println(s"Here is the state $sum")

    //Receive Default message
    case _ =>
      println("This is the default message")
  }
}

//Create Actor Class
class SendAndSumWithConstructor(initialSum: Int) extends Actor{
  //Actor State
  var newSum = 0

  //Actor Behavior
  override def receive: Receive = {
    //Receive a message as integer
    case x: Int =>
      newSum = initialSum + newSum + x
      println(s"The new state of newSum is $newSum")

    //Receive default message
    case _ =>
      println("This is the constructor default message")
  }

}