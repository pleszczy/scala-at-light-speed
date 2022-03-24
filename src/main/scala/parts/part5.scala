package org.scalaatlightspeed
package parts

object part5 extends App {
  val anInt = 69

  positioning(anInt)

  private def positioning(position: Int): Unit =
    position match {
      case 69 => println("That's the right position")
      case _ => println("That's not how you do it")
    }

  case class Position(x: Int, y: Int)

  Position(4, 4) match {
    case Position(4, 1) => println("no but close")
    case Position(4, 2) => println("yes")
    case Position(4, 3) => println("no but close")
    case Position(a, b) => println(s"no even close ($a, $b)")
  }

  (4, 2) match {
    case (4, 1) => println("no but close")
    case (4, 2) => println("yes")
    case (4, 3) => println("no but close")
    case (x, y) => println(s"no even close ($x, $y)")
  }

  List(4, 2, 2, 3) match {
    case ::(head, tail) if head == 3 => println(s"Head: $head, tail: $tail")
    case head :: tail => println(s"Alternative Head: $head, tail: $tail")
    case _ => println(s"The list is nil")
  }

  List() match {
    case ::(head, tail) => println(s"Head: $head, tail: $tail")
    case Nil => println(s"The list is empty")
  }

  List(4, 2, 2, 3) match {
    case 4 :: 2 :: 2 :: 3 :: Nil => println(s"yes")
    case _ => println(s"no")
  }

  val seq: Seq[Matchable] = List(1, 2, 3.14, 5.5, "one", "four", true, (6, 7), false, true)

  val result = seq.map {
    case 1 => "int 1"
    case i: Int => s"other int: $i"
    case d: (Double | Float) => s"a double or float: $d"
    case "one" => "string one"
    case s: String => s"other string: $s"
    case (x, y) => s"tuple: ($x, $y)"
    case unexpected => s"unexpected value: $unexpected"
  }

  println(result)

}
