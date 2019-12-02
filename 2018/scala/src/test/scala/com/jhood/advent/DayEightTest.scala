package com.jhood.advent

import org.jhood.advent.DayEight
import org.scalatest.FunSuite

class DayEightTest extends FunSuite {
  test("parse example") {
    val result = DayEight.LicenseParser.parse(DayEight.example)

    assert(result == DayEight.LicenseNode(
      List(
        DayEight.LicenseNode(Nil, List(10,11,12)),
        DayEight.LicenseNode(
          List(DayEight.LicenseNode(Nil, List(99))),
          List(2)
        )
      ),
      List(1,1,2)
    ))
  }

  test("Part 1 - Solve") {
    val parsed = DayEight.LicenseParser.parse(DayEight.input)
    val sum = DayEight.sum(parsed)
    println(s"Day 8 Part 1 - $sum")
  }

  test("Part 2 - solve example") {
    val parsed = DayEight.LicenseParser.parse(DayEight.example)
    val score = DayEight.score(parsed)
    assert(score == 66)
  }

  test("Part 2 - Solve") {
    val parsed = DayEight.LicenseParser.parse(DayEight.input)
    val score = DayEight.score(parsed)
    println(s"Day 8 Part 2 - $score")
  }
}
