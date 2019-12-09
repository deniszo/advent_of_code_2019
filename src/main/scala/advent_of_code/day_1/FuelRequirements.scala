package advent_of_code.day_1

import scala.annotation.tailrec
import scala.math.round

object FuelRequirements {
  def getFuel(mass: Int): Long = {
    round(mass / 3) - 2
  }

  def sumOfProcessedMasses(fn: Int => Long)(masses: List[Int]): Long =
    masses.map(fn).sum

  val totalFuelRequired = sumOfProcessedMasses(getFuel) _

  def getFuelAmortized(mass: Int): Long = {

    @tailrec
    def collectFuel(sum: Long, mass: Int): Long = {
      val requiredFuel = getFuel(mass)
      if (requiredFuel > 0) collectFuel(sum + requiredFuel, requiredFuel.toInt)
      else sum
    }

    collectFuel(0, mass)
  }

  val totalFuelRequiredAmortized = sumOfProcessedMasses(getFuelAmortized) _
}
