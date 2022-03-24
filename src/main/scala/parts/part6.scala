package org.scalaatlightspeed
package parts

import scala.concurrent.{Await, Future, blocking}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, SECONDS}
import scala.concurrent.impl.Promise
import scala.language.postfixOps
import scala.util.{Failure, Random, Success}
import scala.util.chaining.*
import scala.concurrent.duration._

object part6 extends App {

  lazy val lazyValWithSideEffect = {
    println("lazy")
    43
  }

  println("Happens before")

  val a = 12 + lazyValWithSideEffect

  private val maybeString = Option("sth")
  maybeString match {
    case Some(value) => println(value)
    case None => println("None")
  }

  None.toRight(42) match {
    case Left(value) => println("left")
    case Right(value) => println("right")
  }

  None.toLeft(42) match {
    case Right(value) => println("right")
    case Left(value) => println("left")
  }

  println(None.fold("default")(it => it))
  println(Some("some").fold("default")(it => s"$it enhanced"))

  println(List(1, 2, 3).fold(100)(_ + _))


  val aFuture: Future[Int] = Future(
    blocking {
      println("Loading...")
      Thread.sleep(1000)
      val number = Random.between(1, 100)
      println(s"Staring with number $number")
      number
    })


  aFuture onComplete {
    case Success(number) => {
      println(s"Firing onComplete callback with $number")
      number * 2
    }
    case Failure(t) => println("An error has occurred: " + t.getMessage)
  }

  aFuture foreach (number => {
    println(s"Firing foreach callback with $number")
    number * 2
  })

  val ab: Future[Int] = for {
    a: Int <- aFuture
    b <- Future({
      a
    })
  } yield a + b

  ab.foreach(it => println(s"Firing foreach callback with $it"))

  Await.ready(ab, 5 second)

  implicit class MyRichInteger(n: Int) {
    def isEven = n % 2 == 0
  }

  println(2.isEven)


}
