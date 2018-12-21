package org.jhood.advent

import scala.util.parsing.combinator.RegexParsers

object DayThree {
  case class Claim(id: Int, x: Int, y: Int, width: Int, height: Int) {
    def claimedSquares: Seq[(Int,Int)] =
      (x until (x + width)).flatMap { x =>
        (y until (y + height)).map { y =>
          (x, y)
        }
      }
  }

  object ClaimParser extends RegexParsers {
    val number: Parser[Int] = "([0-9]+)".r ^^ { _.toInt }
    val claim: Parser[Claim] = "#" ~ number ~ "@" ~ number ~ "," ~ number ~ ":" ~ number ~ "x" ~ number ^^ {
      case _ ~ id ~ _ ~ left ~ _ ~ right ~ _ ~ w ~ _ ~ h =>
          Claim(id, left, right, w, h)
    }

    val manyClaims: Parser[Seq[Claim]] = rep(claim)

    def parse(input: String): Claim = parseAll(claim, input) match {
      case Success(result,_) => result
      case NoSuccess(msg, _) => throw new Exception(msg)
    }

    def parseMany(input: String): Seq[Claim] = parseAll(manyClaims, input) match {
      case Success(result,_) => result
      case NoSuccess(msg, _) => throw new Exception(msg)
    }
  }

  def countDuplicates(claims: Seq[Claim]): Int =
    claims.flatMap(_.claimedSquares)
      .groupBy(identity)
      .count(_._2.length >= 2)

  def uniqueClaims(claims: Seq[Claim]): Seq[Claim] = {
    val uniquelyClaimedSquares : Map[(Int,Int),Seq[(Claim,(Int,Int))]] =
      claims.flatMap(c => c.claimedSquares.map(square => c -> square))
            .groupBy(_._2)
            .filter(_._2.length == 1)

    val potentialClaims = uniquelyClaimedSquares.flatMap(_._2.map(_._1)).toSet
    potentialClaims.filter(c => c.claimedSquares.forall(uniquelyClaimedSquares.contains)).toSeq
  }

}
