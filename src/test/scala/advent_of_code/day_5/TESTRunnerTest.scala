package advent_of_code.day_5

class TESTRunnerTest extends org.scalatest.FunSuite {

  test(
    "Properly executes 1002,4,3,4,33"
  ) {
    assert(
      TESTRunner
        .execute(
          1,
          Vector(1002, 4, 3, 4, 33)
        )
        ._1(4) == 99
    )
  }

  test("Properly works with input and output") {
    val input = 777
    val (resultingState, outputs) = TESTRunner
      .execute(
        input,
        Vector(3, 1, 4, 1, 99)
      )
    assert(
      resultingState == Vector(3, 777, 4, 1, 99)
    )
    assert(outputs === Vector(input))
  }

  test("Properly works with negative digits in 1101,100,-1,4,0") {
    assert(
      TESTRunner
        .execute(
          1,
          Vector(1101, 100, -1, 4, 0)
        )
        ._1(4) == 99
    )
  }

  test("Using position mode outputs 1 for input=8") {
    assert(
      TESTRunner
        .execute(
          8,
          Vector(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8)
        )
        ._2
        .head == 1
    )
  }

  test("Using position mode outputs 1 for input < 8") {
    assert(
      TESTRunner
        .execute(
          7,
          Vector(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8)
        )
        ._2
        .head == 1
    )
  }

  test("Using imediate mode outputs 1 for input < 8") {
    assert(
      TESTRunner
        .execute(
          1,
          Vector(3, 3, 1107, -1, 8, 3, 4, 3, 99)
        )
        ._2
        .head == 1
    )
  }

  test("Position mode with jumps outputs 0 if input is 0") {
    assert(
      TESTRunner
        .execute(
          0,
          Vector(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9)
        )
        ._2 == Vector(0)
    )
  }

  test("Immediate mode with jumps outputs 1 if input is != 0") {
    assert(
      TESTRunner
        .execute(
          2,
          Vector(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1)
        )
        ._2 == Vector(1)
    )
  }

  test("Longer program outputs 1000 if output = 8") {
    assert(
      TESTRunner
        .execute(
          8,
          Vector(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20,
            31, 1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46,
            104, 999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98,
            99)
        )
        ._2 == Vector(1000)
    )
  }
}
