package advent_of_code.day_3

import advent_of_code.utils.InputReader

object DistanceChecker {
  private def parseRaw(raw: String): List[Segment] =
    raw
      .split(",")
      .map { str =>
        Segment(str.toList)
      }
      .toList

  def check(rawSeg1: String, rawSeg2: String): Int = {
    val path1 = PathFinder.fullPath(parseRaw(rawSeg1))

    val path2 = PathFinder.fullPath(parseRaw(rawSeg2))

    val intersection = PathFinder.findInteresections(path1, path2)

    PathFinder.distanceToClosest(Point(0, 0), intersection)

  }
}

object FindManhattanDistance extends App {
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
