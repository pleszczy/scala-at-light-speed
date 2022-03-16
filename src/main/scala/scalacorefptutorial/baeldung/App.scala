package org.scalaatlightspeed
package scalacorefptutorial.baeldung

import baeldung.monad.LazyMonad.Lazy
import parts.Part2

object App {

  @main def main(): Unit = {
    monads()

  }

  def monads(): Unit = {
    val value: Lazy[String] = Lazy("12")
    val value1: Lazy[String] = value.map(it => it + "1")
    val value2: Lazy[String] = value.map {
      it =>
        println(s"lazy evaluated side effect => $it")
        it
    }
    val value3: Lazy[String] = value2.flatMap(it => Lazy(it))
    println(value1.get)
    println(value2.get)
  }

}
