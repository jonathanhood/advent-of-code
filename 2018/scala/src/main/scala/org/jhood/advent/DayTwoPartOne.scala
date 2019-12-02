package org.jhood.advent

object DayTwoPartOne {
  def evaluateId(id: String): (Int,Int) = {
    val byChar = id.groupBy(identity)
    (if(byChar.exists(_._2.size == 2)) 1 else 0, if(byChar.exists(_._2.size == 3)) 1 else 0)
  }

  def checksum(evaluated: Seq[(Int,Int)]): Int = {
    val (left,right) = evaluated.reduce( (left,right) => (left._1 + right._1, left._2 + right._2) )
    left * right
  }
}
