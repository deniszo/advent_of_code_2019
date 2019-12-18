package advent_of_code.day_7

import advent_of_code.utils.InputReader

object FindMaximumOutput extends App {
  println(
    InputReader
      .listOfIntVecs("day_7.txt", ',')
      .map {
        case (inputs :: _) => {
          AmplificationCircuit
            .sequenceWithMaxOutput(5, inputs)
        }
      }
      .get
      ._2
  )
}
