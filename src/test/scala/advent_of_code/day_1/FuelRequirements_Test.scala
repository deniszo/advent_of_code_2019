package advent_of_code.day_1

class FuelRequirementsTest extends org.scalatest.FunSuite {
  test("getFuel returns correct values") {
    assert(FuelRequirements.getFuel(12) === 2)
    assert(FuelRequirements.getFuel(14) === 2)
    assert(FuelRequirements.getFuel(1969) === 654)
    assert(FuelRequirements.getFuel(100756) === 33583)
  }

  test("totalFuelRequired returns correct value") {
    assert(
      FuelRequirements
        .totalFuelRequired(List(12, 14, 1969, 100756)) === 2 + 2 + 654 + 33583
    )
  }
}
