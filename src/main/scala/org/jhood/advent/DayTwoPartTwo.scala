package org.jhood.advent

object DayTwoPartTwo {
  def solve(ids: List[String]): String =
    ids.combinations(2)
       .collect {
          case List(left,right) => compare(left,right)
        }
        .toList
        .maxBy(_.length)

  def compare(left: String, right: String): String =
    left.zip(right)
        .filter { case (l,r) => l == r }
        .foldLeft("") { _ + _._1 }
}
