package com.github.janikibichi.learnakka.divein

import akka.actor.{PoisonPill,Props,ActorSystem,Actor}

//Create a Stop Message
case object Stop

//Use context.stop(actorRef) or PoisonPill to shutdown Actor
object ActorShutdown extends App{
  val actorSystem = ActorSystem("ActorShutdown")

  //Create and define Actor and add it to the ActorSystem
  val shutdownActor1 = actorSystem.actorOf(Props[ShutdownActor],"ShutDown1")
  //Reflect Actor Address
  println(shutdownActor1.path)
  //Message the Actor
  shutdownActor1 ! "hello"
  shutdownActor1 ! PoisonPill
  shutdownActor1 ! "Are you there?"

  //Create and define Actor and add it to the ActorSystem
  val shutdownActor2 = actorSystem.actorOf(Props[ShutdownActor],"ShutDown2")
  //Reflect Actor Address
  println(shutdownActor2.path)
  //Message the Actor
  shutdownActor2 ! "hello Actor1"
  shutdownActor2 ! Stop
  shutdownActor2 ! "Are you there?"
}

//Create the Actor
class ShutdownActor extends Actor{
  override def receive: Receive = {
    case msg: String => println(s"$msg")
    case Stop =>context.stop(self)
  }
}
