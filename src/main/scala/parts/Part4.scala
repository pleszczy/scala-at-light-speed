package org.scalaatlightspeed
package parts

import scala.annotation.tailrec
import scala.collection.immutable
import scala.collection.immutable.AbstractSeq
import scala.util.chaining.*

object Part4 {

  val incA = new Function1[Int, Int] {
    override def apply(v1: Int) = v1 + 1
  }

  val incB = new (Int => Int) {
    override def apply(v1: Int) = v1 + 1
  }

  def incC: Function1[Int, Int] = v1 => v1 + 1

  def incD: Int => Int = v1 => v1 + 1

  def incE = (v1: Int) => v1 + 1

  def concatA: Function2[Int, Int, String] = (v1, v2) => s"$v1$v2"

  def concatB: (Int, Int) => String = (v1, v2) => s"$v1$v2"

  def concatC = (v1: Int, v2: Int) => s"$v1$v2"

  def higherOrderFnA = (agg: Int, fnA: Int => Int, fnB: Int => Int) => agg.pipe(fnA).pipe(fnB)

  def higherOrderFnB = (agg: Int, fns: Seq[Int => Int]) => fns.reduce((a, b) => a.compose(b))(agg)

  def printlnMany(x: Any*) = x.foreach(println)

  @main def main(): Unit = {
    val inc = Seq(incA, incB, incC, incD, incE)
    val incComposed = inc.reduce((a, b) => a.compose(b))

    printlnMany(incA(10), incB(11), incC(12), incD(13), incE(14))
    printlnMany(concatA(4, 2), concatB(4, 2), concatC(4, 2))
    printlnMany(higherOrderFnA(12, incA, incC), higherOrderFnB(12, inc))

    val numbers = List(10, 11, 12, 13, 14)
    val letters = List("a", "b", "c", "d", "e")

    val composeAndFilter = numbers.map(incComposed).filter(_ % 2 == 0)
    val compose = numbers.flatMap((it: Int) => List(incComposed(it)))

    val zippingNumbersWithLettersA = numbers.flatMap(number => letters.map(letter => s"$number-$letter"))
    val zippingNumbersWithLettersB = numbers.zip(letters)

    val zippingNumberWithLettersC = for
      number <- numbers
      letter <- letters
    yield s"$number-$letter"

    println(numbers.head)
    println(numbers.tail)
    println(9 :: numbers)
    println(numbers :+ 15)

    // sequences - just like ArrayList
    val aSequence: Seq[Int] = Seq(1, 2, 3)

    // a fast sequence implementation
    val aVector: Vector[Int] = Vector(1, 2, 3)

    val aSet: Set[Int] = Set(2, 2, 2)
    println(aSet.contains(2))
    println((aSet + 5) - 2)

    val aRange = 1 to 1000
    println(aRange.filter(_ % 2 == 0))

    val aTuple: (String, String, String) = ("Clojure", "Scala", "Java")

    println(s"My second favorite language is ${aTuple._2}")

    val aMap = Map(
      ("Favorite language", "Clojure"),
      "Second favorite language" -> "Scala"
    )
    println(aMap)

  }
}
