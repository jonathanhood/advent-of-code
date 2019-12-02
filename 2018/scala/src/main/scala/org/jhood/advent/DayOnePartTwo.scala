package org.jhood.advent

import scala.annotation.tailrec
import scala.util.Try

object DayOnePartTwo {
  def parse(input: Iterator[String]): List[Int] =
    input.flatMap(line => Try(line.replaceAll("\\+","").trim.toInt).toOption).toList

  def repeat(input: List[Int]): Stream[Int] =
    Stream.continually(input).flatten

  @tailrec
  def solve(input: Stream[Int], acc: Int = 0, frequencies: Set[Int] = Set(0)): Int = {
    val freq = input.head + acc
    if(frequencies.contains(freq)) {
      freq
    } else {
      solve(input.tail, freq, frequencies + freq)
    }
  }
}
