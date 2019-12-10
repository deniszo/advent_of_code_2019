package advent_of_code.day_2

class ProgramAlarmTest extends org.scalatest.FunSuite {
  test("execute returns correct Vector of modified values") {
    assert(
      ProgramAlarm.execute(Vector(1, 0, 0, 0, 99)) === Vector(2, 0, 0, 0, 99)
    )
    assert(
      ProgramAlarm.execute(Vector(2, 3, 0, 3, 99)) === Vector(2, 3, 0, 6, 99)
    )
    assert(
      ProgramAlarm.execute(Vector(2, 4, 4, 5, 99, 0)) === Vector(2, 4, 4, 5, 99,
        9801)
    )
    assert(
      ProgramAlarm.execute(Vector(1, 1, 1, 4, 99, 5, 6, 0, 99)) === Vector(30,
        1, 1, 4, 2, 5, 6, 0, 99)
    )
  }

  test("longer sequence execution properly calculates first item") {
    assert(
      ProgramAlarm
        .execute(Vector(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50))
        .head === 3500
    )
  }
}
