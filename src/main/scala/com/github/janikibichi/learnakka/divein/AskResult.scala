package com.github.janikibichi.learnakka.divein

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Await

object AskResult extends App {
  implicit val timeout = Timeout(10 seconds)
  val actorSystem = ActorSystem("AskResult")

  //Create the actor in the Actor System
  val actor = actorSystem.actorOf(Props[CalculateFibonacciActor], "FibonacciNumber")

  //Show the Actor Address
  println(actor.path)

  //Ask for results from Actor
  val future = (actor ? 10).mapTo[Int]

  val fibonacciNumber = Await.result(future, 10 seconds)
  println(fibonacciNumber)
}

class CalculateFibonacciActor extends Actor{
  override def receive: Receive = {
    case num: Int =>
      val fibonacciNumber = fib(num)
      //Send Message to Actor
      sender ! fibonacciNumber
  }

  def fib(n: Int) : Int = n match {
    case 0 | 1 => n
    case _ => fib(n-1) + fib( n-2)
  }
}
