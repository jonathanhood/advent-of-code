package org.jhood.advent


object DayFive {
  def optimumRemoval(input: String): String = {
    val removalChars = input.toSet.map((c : Char) => c.toLower)
    val results = removalChars.map(c => react(input.filterNot((i : Char) => i.toLower == c)))
    results.minBy(_.length)
  }

  def react(input: String): String = {
    reactRec(input.toSeq, Nil).mkString
  }

  def reactRec(input: Seq[Char], acc: Seq[Char]): Seq[Char] =
    input.foldRight(Seq[Char]()) {
      case (next, head :: tail) if next.toLower == head.toLower && head != next => tail
      case (x, acc) => x +: acc
    }
}
