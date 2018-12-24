package com.jhood.advent

import org.jhood.advent.DayNine
import org.scalatest.FunSuite

class DayNineTest extends FunSuite {
  test("part 1 - solve the example") {
    val result = DayNine.play(
      1,
      25,
      0,
      0,
      List.fill(9)(0),
      List(0)
    )

    assert(result == 32)
  }
}
