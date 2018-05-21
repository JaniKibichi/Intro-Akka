# Firing up AKKA
[The documentation can be seen here](https://akka.io/docs/).

AKKA is a word assembled from 'Actor Kernel'.

#### Importance of AKKA
The need:
Make use of all cores of hardware as well as distribute work across nodes when running an 
application.

The Solution:
An open source toolkit for writing concurrent, fault-tolerant, distributed,self healing, scalable apps 
that can run for months or years without stopping.

#### Actor Definitions:
'An actor is a process that executes a function.'
'An Actor is an object that encapsulates state and behavior.'

Actors communicate by exchanging messages. These messages are places in a recipients mailbox.
Actors are managed in an ActorSystem through hierarchies, i.e. there are parent and child actors. 
There should be one ActorSystem per logical application.

#### Actor Best Practises:
Actors Should not block
Actors Should exchange immutable messages
Actors Should be containers for state, behavior, mailbox, child actors, a supervisor strategy
Actors are represented using actor references: it is not possible to 'look' into an actor or access its state/behavior 
from outside.

#### What we do in this repo:
Create an AKKA project from Scratch using SBT

- Ensure you have [installed SBT.](https://www.scala-sbt.org/1.0/docs/Setup.html)
- [Create the build.sbt file.](https://asciinema.org/a/tlWDSF1jBYWbSaCaKBfrfzruN)
- Add AKKA dependency in build.sbt from [mvnrepository.](https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor)
- Run [sbt update.](https://asciinema.org/a/YquWSJ6d5c7OXRiBaM43FFwTL)

Start actors, communicate between actors, stop an actor.

- Create the ActorSystem in com.github.yourusername.helloakka, 
  in this case com.github.janikibichi.learnakka.divein
- Run the app to [fire the first actorSystem](https://asciinema.org/a/C36iHAwerZ8eNmjTQBukj3YcV)