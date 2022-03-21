package org.scalaatlightspeed

object BalanceOrNot {


  //Consider a string consisting of the characters < and > only. The string is balanced if each < always appears before (i.e., to the left of) a corresponding > character
  // (they do not need to be adjacent). Moreover, each < and > act as a unique pair of symbols and neither symbol can be considered as part of any other pair of symbols.
  //To balance a string, any > character can be replaced with <>. Given an expression and a maximum number of replacements, determine whether the string can be balanced.
  //Example
  //expressions = ['<<>>', '<>', '<><>', '>>', '<<>', '><><']
  //maxReplacements = [0, 1, 2, 2, 2, 2]
  //Process a series of expressions and their corresponding maxReplacements. Each of the first three expressions is balanced already. The string expressions[3] = '>>' can be balanced
  // in two moves by replacing each > with a <> to make <><>. Neither of the last two strings can ever be balanced.
  def balancedOrNot(expressions: Array[String], maxReplacements: Array[Int]): Array[Int] = {
    expressions.zip(maxReplacements)
      .map((exp: String, max: Int) => {
        LazyList
          .iterate(exp)(_.replace("<>", ""))
          .dropWhile(_.contains("<>"))
          .head
        .match {
          case it if it.contains('<') => 0
          case it if it.length <= max => 1
          case _ => 0
        }
      })
  }

}
