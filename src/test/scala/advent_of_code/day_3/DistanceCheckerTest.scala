package advent_of_code.day_3

class DistanceCheckerTest extends org.scalatest.FunSuite {
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

  test(
    "Finds correct minimum number of steps between R8,U5,L5,D3 and U7,R6,D4,L4 segments"
  ) {
    assert(DistanceChecker.minSteps("R8,U5,L5,D3", "U7,R6,D4,L4") == 30)
  }

  test(
    "Finds correct minimum number of steps between R75,D30,R83,U83,L12,D49,R71,U7,L72 and U62,R66,U55,R34,D71,R55,D58,R83 segments"
  ) {
    assert(
      DistanceChecker.minSteps(
        "R75,D30,R83,U83,L12,D49,R71,U7,L72",
        "U62,R66,U55,R34,D71,R55,D58,R83"
      ) == 610
    )
  }

  test(
    "Finds correct minimum number of steps between R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R5 and U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 segments"
  ) {
    assert(
      DistanceChecker.minSteps(
        "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R5",
        "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
      ) == 410
    )
  }
}
