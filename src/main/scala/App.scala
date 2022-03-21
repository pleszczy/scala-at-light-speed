package org.scalaatlightspeed


import parts.Part2
import FizzBuzz.fizzBuzz

import BalanceOrNot.balancedOrNot

object App {

  @main def main(): Unit = {
    println(Part2.factorial(4))
    println(Part2.factorialOptimized(4))
    fizzBuzz(1000)
    println(balancedOrNot(Array("<<>>", "<>", "<><>", ">>", "<<>", "><><"), Array(0, 1, 2, 2, 2, 2)).mkString("Array(", ", ", ")"))
  }

}



