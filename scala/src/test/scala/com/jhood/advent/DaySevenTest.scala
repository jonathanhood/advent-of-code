package com.jhood.advent

import org.jhood.advent.DaySeven
import org.jhood.advent.DaySeven.Command
import org.scalatest.FunSuite

class DaySevenTest extends FunSuite {

  test("parse a sequence of commands") {
    val result = DaySeven.CommandParser.parse(DaySeven.example)
    assert(result == Seq(
      Command("F", "E"),
      Command("C", "A"),
      Command("C", "F"),
      Command("A", "B"),
      Command("A", "D"),
      Command("B", "E"),
      Command("D", "E")
    ))
  }

  test("solve the example") {
    val parsed = DaySeven.CommandParser.parse(DaySeven.example)
    val result = DaySeven.sequence(parsed)
    assert(result == Seq("C", "A", "B", "D", "F", "E"))
  }

  test("not give a bad solution") {
    val parsed = DaySeven.CommandParser.parse(DaySeven.input)
    val result = DaySeven.sequence(parsed)
    assert(result.mkString != "ABCDEFGIJHMLNOQPSTRVUWYXZK")
  }

  test("solve part 1") {
    val parsed = DaySeven.CommandParser.parse(DaySeven.input)
    val result = DaySeven.sequence(parsed)
    println(s"Day 7 Part 1 - ${result.mkString}")
  }

}
