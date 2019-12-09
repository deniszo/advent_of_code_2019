package advent_of_code.day_1

import advent_of_code.utils.InputReader
import scala.util.Try

object TotalFuelRequiredAmortized extends App {
  println(
    InputReader
      .getLines("day_1.txt")
      .map(_.toList map (_.toInt))
      .map(FuelRequirements.totalFuelRequiredAmortized)
      .get
  )
}
