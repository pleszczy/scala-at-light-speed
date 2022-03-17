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
    val composeAndFilter = List(10, 11, 12, 13, 14).map(incComposed).filter(_ % 2 == 0)
    println(composeAndFilter)
    val compose = List(10, 11, 12, 13, 14).flatMap((it: Int) => List(incComposed(it)))
    println(compose)
    val zippingNumberWithLettersA = List(10, 11, 12, 13, 14).flatMap(number => List("a", "b", "c", "d", "e").map(letter => s"$number-$letter"))
    println(zippingNumberWithLettersA)
    val zippingNumberWithLettersB = List(10, 11, 12, 13, 14).zip(List("a", "b", "c", "d", "e"))
    println(zippingNumberWithLettersA)
  }
}
