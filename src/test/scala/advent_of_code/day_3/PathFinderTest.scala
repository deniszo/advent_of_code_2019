package advent_of_code.day_3

class PathFinderTest extends org.scalatest.FunSuite {

  test(
    "Builds correct sequence of points for Left oriented segment starting at (0, 0)"
  ) {
    assert(
      PathFinder.segmentPath(
        Point(0, 0),
        Left,
        3
      ) === Seq(Point(-1, 0), Point(-2, 0), Point(-3, 0)).reverse
    )
  }

  test(
    "Builds correct sequence of points for Right oriented segment starting at (0, 0)"
  ) {
    assert(
      PathFinder.segmentPath(
        Point(0, 0),
        Right,
        3
      ) === Seq(Point(1, 0), Point(2, 0), Point(3, 0)).reverse
    )
  }

  test(
    "Builds correct sequence of points for Up oriented segment starting at (0, 0)"
  ) {
    assert(
      PathFinder.segmentPath(
        Point(0, 0),
        Up,
        3
      ) === Seq(Point(0, 1), Point(0, 2), Point(0, 3)).reverse
    )
  }

  test(
    "Builds correct sequence of points for Down oriented segment starting at (0, 0)"
  ) {
    assert(
      PathFinder.segmentPath(
        Point(0, 0),
        Down,
        3
      ) === Seq(Point(0, -1), Point(0, -2), Point(0, -3)).reverse
    )
  }

  test(
    "Builds correct sequence of points for the circular segments"
  ) {
    assert(
      PathFinder.fullPath(
        List(
          Segment(Right, 1),
          Segment(Up, 1),
          Segment(Left, 1),
          Segment(Down, 1)
        )
      ) === Seq(Point(0, 0), Point(0, 1), Point(1, 1), Point(1, 0))
    )
  }

  test("Finds correct distance between R8,U5,L5,D3 and U7,R6,D4,L4 segments") {
    assert(DistanceChecker.check("R8,U5,L5,D3", "U7,R6,D4,L4") == 6)
  }

  test(
    "Finds correct distance between R75,D30,R83,U83,L12,D49,R71,U7,L72 and U62,R66,U55,R34,D71,R55,D58,R83 segments"
  ) {
    assert(
      DistanceChecker.check(
        "R75,D30,R83,U83,L12,D49,R71,U7,L72",
        "U62,R66,U55,R34,D71,R55,D58,R83"
      ) == 159
    )
  }

  test(
    "Finds correct distance between R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R5 and U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 segments"
  ) {
    assert(
      DistanceChecker.check(
        "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R5",
        "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
      ) == 135
    )
  }
}
