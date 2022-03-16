package org.scalaatlightspeed.baeldung
package monad
//scala-tutorials/scala-core-fp/src/main/scala/com/baeldung/scala/monad/LazyMonad.scala

// source: https://github.com/Baeldung/scala-tutorials/blob/master/scala-core-fp/src/main/scala/com/baeldung/scala/monad/LazyMonad.scala
object LazyMonad {
  class Lazy[+A](value: => A) {
    private lazy val internal: A = value

    def get: A = internal

    def flatMap[B](f: (=> A) => Lazy[B]): Lazy[B] = f(internal)

    def map[B](f: A => B): Lazy[B] = flatMap(x => Lazy(f(x)))
  }

  object Lazy {
    def apply[A](value: => A): Lazy[A] = new Lazy(value)
    def flatten[A](m: Lazy[Lazy[A]]): Lazy[A] = m.flatMap(x => x)
  }
}