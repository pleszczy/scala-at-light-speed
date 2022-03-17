package org.scalaatlightspeed
package scalacorefptutorial.baeldung

import parts.Part2
import parts.Part3.MyList
import monad.LazyMonad.Lazy

import scala.util.chaining.*

object App {

  @main def main(): Unit = println(lazyMonad().get)

  def lazyMonad(): Lazy[Long] =
    Lazy("12")
      .map(it => it.pipe(it => it.toLong * 12).pipe(it => it / 2))
      .map(it => {
        println(s"executing side effect => $it * 2")
        it * 2
      })
      .tap(_ => println("Still constructing, still lazy"))
      .flatMap(it => Lazy(it))
}
