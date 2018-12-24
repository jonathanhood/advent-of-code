package org.jhood.advent

import scala.annotation.tailrec

object DayNine {
  @tailrec
  def play(next: Int, max: Int, current: Int, player: Int, scores: List[Int], marbles: List[Int]): Int = {
    if(next == max + 1) {
      scores.max
    } else if((next % 23) == 0) {
      val reverseStart = Stream.continually(marbles.reverse).flatten.dropWhile(_ != current)
      val streamStart = Stream.continually(marbles).flatten.dropWhile(_ != current)
      val removed = reverseStart.drop(7).head
      print(s"Score! ${marbles.mkString(" ")}")
      val score = next + removed
      val updatedMarbles = marbles.filter(_ != removed)
      val updatedCurrent = streamStart.dropWhile(_ != current).head
      play(
        next + 1,
        max,
        updatedCurrent,
        (player + 1) % scores.length,
        scores.updated(player,scores(player) + score),
        updatedMarbles
      )
    } else {
      val streamStart = Stream.continually(marbles).flatten.dropWhile(_ != current)
      val updatedMarbles = (streamStart.take(1) #::: next #:: streamStart.drop(1)).take(marbles.length + 1).toList
      play(
        next + 1,
        max,
        next,
        (player + 1) % scores.length,
        scores,
        updatedMarbles
      )
    }
  }
}
