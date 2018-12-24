package com.jhood.advent

import org.jhood.advent.DayNine
import org.scalatest.FunSuite

class DayNineTest extends FunSuite {
  test("part 1 - solve the example") {
    val result = DayNine.score(5,25)

    assert(result == 32)
  }

  test("part 1 - solve example 2") {
    val result = DayNine.score(10,1618)
    assert(result == 8317)
  }

  test("part 1 - solve example 3") {
    val result = DayNine.score(13,7999)
    assert(result == 146373)
  }

  test("part 1 - solve example 4") {
    val result = DayNine.score(17,1104)
    assert(result == 2764)
  }

  test("part 1 - solve example 5") {
    val result = DayNine.score(21,6111)
    assert(result == 54718)
  }

  test("part 1 - solve example 6") {
    val result = DayNine.score(30,5807)
    assert(result == 37305)
  }

  test("part 1 - solve") {
    val result = DayNine.score(410, 72059)

    println(s"Day 9 Part 1 - $result")
  }

  test("part 2 - solve") {
    val result = DayNine.score(410, 7205900)

    println(s"Day 9 Part 2 - $result")
  }
}
