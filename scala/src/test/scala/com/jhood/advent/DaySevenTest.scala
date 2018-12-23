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

  test("part 1 - solve the example") {
    val parsed = DaySeven.CommandParser.parse(DaySeven.example)
    val result = DaySeven.sequence(parsed)
    assert(result == Seq("C", "A", "B", "D", "F", "E"))
  }

  test("part 1 - not give a bad solution") {
    val parsed = DaySeven.CommandParser.parse(DaySeven.input)
    val result = DaySeven.sequence(parsed)
    assert(result.mkString != "ABCDEFGIJHMLNOQPSTRVUWYXZK")
  }

  test("part 1 - solve") {
    val parsed = DaySeven.CommandParser.parse(DaySeven.input)
    val result = DaySeven.sequence(parsed)
    println(s"Day 7 Part 1 - ${result.mkString}")
  }

  test("part 2 - solve the example") {
    val parsed = DaySeven.CommandParser.parse(DaySeven.example)
    val sequenced = DaySeven.sequence(parsed)
    val depends = DaySeven.depends(sequenced, parsed)
    val time = DaySeven.execute(depends, Set.empty, Seq(None,None), 0, 0)
    assert(time == 15)
  }

  test("part 2 - solve") {
    val parsed = DaySeven.CommandParser.parse(DaySeven.input)
    val sequenced = DaySeven.sequence(parsed)
    val depends = DaySeven.depends(sequenced, parsed)
    val time = DaySeven.execute(depends, Set.empty, Seq(None,None,None,None,None), 0, 60)
    println(s"Day 7 part 2 = ${time}")
  }

}
