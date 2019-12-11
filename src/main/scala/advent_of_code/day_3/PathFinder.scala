package advent_of_code.day_3

sealed trait SegmentDirection

object Up extends SegmentDirection
object Down extends SegmentDirection
object Left extends SegmentDirection
object Right extends SegmentDirection

case class Point(val x: Int, val y: Int)
case class Segment(direction: SegmentDirection, length: Int)

object SegmentParsingError extends Error

object Segment {
  def apply(rawSegment: List[Char]): Segment = {
    val len = rawSegment.tail.mkString("").toInt

    rawSegment.head match {
      case 'R' => Segment(Right, len)
      case 'L' => Segment(Left, len)
      case 'U' => Segment(Up, len)
      case 'D' => Segment(Down, len)
      case _   => throw SegmentParsingError
    }
  }

  def unapply(rawSegment: List[Char]): Option[Segment] =
    Some(apply(rawSegment))

}

object PathFinder {
  type Path = Seq[Point]

  def fullPath(segments: List[Segment]): Path =
    segments.foldLeft(Seq.empty[Point]) {
      case (path, Segment(direction, length)) =>
        segmentPath(path match {
          case Nil                   => Point(0, 0)
          case endOfPrevSegment +: _ => endOfPrevSegment
        }, direction, length) ++ path
    }

  def segmentPath(
      start: Point,
      direction: SegmentDirection,
      length: Int
  ): Path = {
    ((1 to length) map { diff =>
      direction match {
        case Right => start.copy(x = start.x + diff)
        case Left  => start.copy(x = start.x - diff)
        case Up    => start.copy(y = start.y + diff)
        case Down  => start.copy(y = start.y - diff)
      }
    }).reverse
  }

  def findInteresections(path1: Path, path2: Path): Seq[Point] =
    path1.intersect(path2)

  def distanceToClosest(target: Point, points: Seq[Point]): Int =
    points.map { point =>
      (point.x - target.x).abs + (point.y - target.y).abs
    }.min
}
