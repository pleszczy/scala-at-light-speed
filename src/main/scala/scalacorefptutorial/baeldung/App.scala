package org.scalaatlightspeed
package scalacorefptutorial.baeldung

import parts.Part2
import monad.LazyMonad.Lazy

import scala.util.chaining.*

object App {

  @main def main(): Unit = {
    monads()

  }

  def monads(): Unit = {
    val aMonad = Lazy("12")
      .map(it => it.pipe(it => it.toLong * 12).pipe(it => it / 2))
      .map(it => {
        println(s"executing side effect => $it * 2")
        it * 2
      })
      .tap(_ => println("Still constructing, still lazy"))
      .flatMap(it => Lazy(it))
    println(aMonad.get)
  }

}
