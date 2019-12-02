package org.jhood.advent

import scala.annotation.tailrec
import scala.collection.mutable

object DayNine {
  class BufferNode(var next: BufferNode, var last: BufferNode, val value: Long)

  class CircularBuffer {
    private var current: BufferNode = {
      val node = new BufferNode(null,null,0)
      node.next = node
      node.last = node
      node
    }

    @tailrec
    final def clockwise(x: Long): Unit =
      if(x > 0) {
        current = current.next
        clockwise(x - 1)
      }

    final def counterClockwise(x: Long): Unit =
      if(x > 0) {
        current = current.last
        counterClockwise(x - 1)
      }

    def get: Long =
      current.value

    def insertClockwise(v: Long): Unit = {
      val newNode = new BufferNode(current.next,current, v)
      current.next.last = newNode
      current.next = newNode
    }

    def removeAndClockwise: Unit = {
      current.next.last = current.last
      current.last.next = current.next
      current = current.next
    }
  }

  def score(players: Int, lastMarble: Long): Long =
    play(
      1,
      lastMarble,
      0,
      mutable.Buffer.fill(players)(0),
      new CircularBuffer
    )

  @tailrec
  def play(next: Long, max: Long, player: Int, scores: mutable.Buffer[Long], buffer: CircularBuffer): Long = {
    if(next == max + 1) {
      scores.max
    } else if((next % 23) == 0) {
      buffer.counterClockwise(7)
      val removed = buffer.get
      val score = next + removed
      buffer.removeAndClockwise
      scores.update(player,scores(player) + score)
      play(
        next + 1,
        max,
        (player + 1) % scores.length,
        scores,
        buffer
      )
    } else {
      buffer.clockwise(1)
      buffer.insertClockwise(next)
      buffer.clockwise(1)
      play(
        next + 1,
        max,
        (player + 1) % scores.length,
        scores,
        buffer
      )
    }
  }
}
