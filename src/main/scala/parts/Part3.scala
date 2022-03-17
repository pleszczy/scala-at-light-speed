package org.scalaatlightspeed
package parts

import scala.annotation.tailrec
import scala.collection.immutable
import scala.collection.immutable.AbstractSeq

object Part3 {

  class MyList[T](val h: T, val rest: T*) extends AbstractSeq[T] {
    override def apply(i: Int) = ???

    override def length: Int = ???

    override def iterator: Iterator[T] = ???

    override def head: T = ???

    override def tail: MyList[T] = new MyList[T](rest.head , rest.tail:_*)

  }

  object MyList {
    def apply[T](head: T, rest: T*): MyList[T] = new MyList[T](head, rest:_*)
  }

}
