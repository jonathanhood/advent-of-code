package org.jhood.advent

import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

import scala.annotation.tailrec
import scala.util.parsing.combinator.RegexParsers

object DayFour {

  sealed abstract class GuardEvent {
    def ts: LocalDateTime
    def id: Int
  }

  case class Begins(ts: LocalDateTime, id: Int) extends GuardEvent
  case class Asleep(ts: LocalDateTime, id: Int) extends GuardEvent
  case class Wakes(ts: LocalDateTime, id: Int) extends GuardEvent

  sealed abstract class RawEvent {
    def ts: LocalDateTime
  }

  case class RawBegins(ts: LocalDateTime, id: Int) extends RawEvent
  case class RawAsleep(ts: LocalDateTime) extends RawEvent
  case class RawWakes(ts: LocalDateTime) extends RawEvent

  object LogParser extends RegexParsers {
    val timestamp : Parser[LocalDateTime] = "[" ~ "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}".r ~ "]" ^^ {
      case _ ~ stamp ~ _ =>
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        LocalDateTime.parse(stamp, formatter)
    }

    def begins(ts: LocalDateTime): Parser[RawBegins] = "Guard" ~ "#" ~ "[0-9]+".r ~ "begins shift" ^^ {
      case _ ~ _ ~ id ~ _ => RawBegins(ts, id.toInt)
    }

    def asleep(ts: LocalDateTime): Parser[RawAsleep] = "falls asleep" ^^ { _ => RawAsleep(ts)}

    def wakes(ts: LocalDateTime): Parser[RawWakes] = "wakes up" ^^ { _ => RawWakes(ts)}

    def message(ts: LocalDateTime): Parser[RawEvent] = begins(ts) | asleep(ts) | wakes(ts)

    val log: Parser[Seq[RawEvent]] = rep(timestamp into message)

    @tailrec
    def resolve(raw: Seq[RawEvent], id: Int, acc: Seq[GuardEvent]): Seq[GuardEvent] = raw match {
      case RawBegins(ts, newId) :: tail => resolve(tail, newId, acc :+ Begins(ts, newId))
      case RawAsleep(ts) :: tail => resolve(tail, id, acc :+ Asleep(ts, id))
      case RawWakes(ts) :: tail => resolve(tail, id, acc :+ Wakes(ts, id))
      case Nil => acc
    }

    def parse(input: String): Seq[GuardEvent] = parseAll(log, input) match {
      case Success(result,_) => resolve(result.sortBy(_.ts), 0, Nil)
      case NoSuccess(msg, _) => throw new Exception(msg)
    }
  }

  def solvePart1(log: Seq[GuardEvent]): Int = {
    val concentration = sleepConcentration(log)
    val guard = asleepMost(concentration)
    val minute = asleepMostAt(concentration, guard)
    guard * minute
  }

  def solvePart2(log: Seq[GuardEvent]): Int = {
    val concentration = sleepConcentration(log)
    val asleepMostMinute = concentration.keys.map(id => id -> asleepMostAt(concentration, id)).toMap
    val maxSleepPerGuard = asleepMostMinute.map {
      case (id,min) => id -> concentration(id)(min)
    }
    val (id, _) = maxSleepPerGuard.maxBy(_._2)

    id * asleepMostMinute(id)
  }


  def sleepConcentration(log: Seq[GuardEvent]): Map[Int,Map[Int,Int]] =
    asleepAt(log, 0, LocalDateTime.now(), Map.empty)
      .mapValues(_.groupBy(identity).map(times => (times._1,times._2.length)))

  def asleepMost(concentration: Map[Int,Map[Int,Int]]): Int =
    concentration
      .mapValues(_.toList.map(t => t._1 * t._2).sum)
      .toList
      .maxBy(_._2)
      ._1

  def asleepMostAt(concentration: Map[Int,Map[Int,Int]], id: Int): Int =
    concentration
      .get(id)
      .map(_.maxBy(_._2)._1)
      .getOrElse(0)

  @tailrec
  final def asleepAt(log: Seq[GuardEvent], currentGuard: Int, lastTime: LocalDateTime, acc: Map[Int,Seq[Int]]): Map[Int,Seq[Int]] =
    log match {
      case Begins(ts, id) :: tail => asleepAt(tail, id, ts, acc)
      case Asleep(ts, id) :: tail => asleepAt(tail, id, ts, acc)
      case Wakes(ts, id) :: tail => asleepAt(tail, id, ts, acc.updated(id,acc.getOrElse(id,Nil) ++ asleepMinutes(lastTime,ts)))
      case Nil => acc
    }

  def asleepMinutes(start: LocalDateTime, end: LocalDateTime): Seq[Int] =
    (0 until end.getMinute - start.getMinute).map(_ + start.getMinute)

  implicit def ordering: Ordering[LocalDateTime] =
    Ordering.fromLessThan { (l,r) =>
      l.isBefore(r)
    }
}
