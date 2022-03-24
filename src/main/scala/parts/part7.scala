package org.scalaatlightspeed
package parts

object part7 extends App {

  val aList = List(1, 1, 3, 4)

  given descOrder: Ordering[Int] = Ordering.fromLessThan(_ > _)

  println(aList.sorted)

  trait Combinator[A] {
    def combine(x: A, y: A): A
  }

  def combineAll[A](list: List[A])(using combinator: Combinator[A]): A = list.reduce(combinator.combine)

  given intCombinator: Combinator[Int] = _ + _

  val theSum = combineAll(aList)
  println(theSum)

  // context bounds - when you are not going to use the implicit directly e.g. aList.sorted
  def combineAll2[A: Combinator](list: List[A]) = ???

  case class Person(name: String) {
    def hello() = s"Hello $name !"
  }

  extension (str: String)
    def hello() = Person(str).hello()

  println("Piotr".hello())

  extension[A] (list: List[A])
    def combineAll3(using combinator: Combinator[A]) = list.reduce(combinator.combine)

  println(aList.combineAll3)


}
