package advent_of_code.day_6

class OrbitMapperTest extends org.scalatest.FunSuite {

  test(
    "Returns correct number of orbits for test input"
  ) {

    val planets = OrbitMapper
      .parseInput(
        List(
          "E)F",
          "B)G",
          "G)H",
          "D)I",
          "COM)B",
          "B)C",
          "C)D",
          "D)E",
          "E)J",
          "J)K",
          "K)L"
        )
      )
    assert(
      OrbitMapper.totalOrbits(planets) == 42
    )
  }

  test(
    "Returns correct number of transfers for test input"
  ) {

    val planets = OrbitMapper
      .parseInput(
        List(
          "COM)B",
          "B)C",
          "C)D",
          "D)E",
          "E)F",
          "B)G",
          "G)H",
          "D)I",
          "E)J",
          "J)K",
          "K)L",
          "K)YOU",
          "I)SAN"
        )
      )
    assert(
      OrbitMapper.numberOfTransfers("YOU", "SAN", planets) == 4
    )
  }
}
