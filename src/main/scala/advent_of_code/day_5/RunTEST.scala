package advent_of_code.day_5

import advent_of_code.utils.InputReader

object RunTEST extends App {
  println(
    InputReader
      .listOfIntVecs("day_5.txt", ',')
      .map {
        case (inputs :: _) => {
          TESTRunner.execute(List(1), inputs)
        }
      }
      .get
      ._2
  )
}
