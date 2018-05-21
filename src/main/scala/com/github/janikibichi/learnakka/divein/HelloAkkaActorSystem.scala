package com.github.janikibichi.learnakka.divein

import akka.actor.ActorSystem

object HelloAkkaActorSystem extends App {
  val actorSystem = ActorSystem("HelloAkka")

  println(actorSystem)
}