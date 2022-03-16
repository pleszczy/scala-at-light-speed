package org.scalaatlightspeed
package parts

import scala.annotation.tailrec

object Part2 {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else n * factorial(n - 1)

  @tailrec
  def factorialOptimized(n: Int, result: Int = 1): Int =
    if (n <= 1) result
    else factorialOptimized(n - 1, result * n)

}
