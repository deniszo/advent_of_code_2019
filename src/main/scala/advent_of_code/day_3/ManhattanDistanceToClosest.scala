package advent_of_code.day_3

import advent_of_code.utils.InputReader

object ManhattanDistanceToClosest extends App {
  println(
    InputReader
      .getLines("day_3.txt")
      .map { iter =>
        iter.toList match {
          case List(fstSegRaw, sndSegRaw) =>
            DistanceChecker.check(fstSegRaw, sndSegRaw)
        }
      }
  )
}
