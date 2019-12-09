package advent_of_code.day_1

import scala.math.round

object FuelRequirements {
  def getFuel(mass: Int): Long = {
    round(mass / 3) - 2
  }

  def totalFuelRequired(masses: List[Int]): Long = masses.map(getFuel).sum
}
