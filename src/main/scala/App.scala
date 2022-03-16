package org.scalaatlightspeed


import parts.Part2

object App {

  @main def main(): Unit = {
    println(Part2.factorial(4))
    println(Part2.factorialOptimized(4))
  }

}



