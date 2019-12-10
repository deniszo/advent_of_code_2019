package advent_of_code.utils

import scala.io.Source
import scala.util.Try

object InputReader {
  def getLines(fileName: String): Try[Iterator[String]] =
    Try(Source.fromResource(fileName).getLines)

  def listOfInts(fileName: String): Try[List[Int]] =
    getLines(fileName).map(_.toList map (_.toInt))

  def listOfIntVecs(fileName: String, delimiter: Char): Try[List[Vector[Int]]] =
    getLines(fileName)
      .map(_.map(_.split(delimiter).map(_.toInt).toVector).toList)
}
