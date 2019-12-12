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

  def distanceToClosest(target: Point, points: Seq[Point]): Int =
    points.map { point =>
      (point.x - target.x).abs + (point.y - target.y).abs
    }.min

  def check(rawSeg1: String, rawSeg2: String): Int = {
    val path1 = PathFinder.fullPath(parseRaw(rawSeg1))
    val path2 = PathFinder.fullPath(parseRaw(rawSeg2))

    val intersection = PathFinder.findInteresections(path1, path2)

    distanceToClosest(Point(0, 0), intersection)
  }

  def pointsWithSteps(rawSeg: String): Map[Point, Int] = {
    val p = PathFinder.fullPath(parseRaw(rawSeg))

    p.zipWithIndex.map {
      case (point, idx) => (point, p.length - idx)
    }.toMap
  }

  def minSteps(rawSeg1: String, rawSeg2: String): Int = {
    val m1 = pointsWithSteps(rawSeg1)
    val m2 = pointsWithSteps(rawSeg2)

    m1.keys.toSet
      .intersect(m2.keys.toSet)
      .map { k =>
        m1(k) + m2(k)
      }
      .min
  }
}
