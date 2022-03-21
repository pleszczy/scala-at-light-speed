package org.scalaatlightspeed

object FizzBuzz {


  def fizzBuzz(n: Int) = {
    (1 to n).foreach {
      case n if n % 3 == 0 && n % 5 == 0 => println("FizzBuzz")
      case n if n % 3 == 0 => println("Fizz")
      case n if n % 5 == 0 => println("Buzz")
      case it => println(it)
    }
  }

}
