package advent_of_code.day_6

import advent_of_code.utils.InputReader

object NumberOfTransfers extends App {
  val lines = InputReader
    .getLines("day_6_pt2.txt")
    .map(_.toList)
    .get

  val planets = OrbitMapper.parseInput(lines)

  println(
    OrbitMapper.numberOfTransfers("YOU", "SAN", planets)
  )
}
