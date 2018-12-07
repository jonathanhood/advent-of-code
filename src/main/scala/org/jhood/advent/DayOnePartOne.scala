package org.jhood.advent

import scala.util.Try

object DayOnePartOne {
  def parse(input: Iterator[String]): List[Int] =
    input.flatMap(line => Try(line.replaceAll("\\+","").trim.toInt).toOption).toList

  def solve(input: List[Int]): Int =
    input.sum
}
