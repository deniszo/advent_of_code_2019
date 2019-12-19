package advent_of_code.day_7

import advent_of_code.utils.InputReader

object FindMaximumOutputWithFeedback extends App {
  println(
    InputReader
      .listOfIntVecs("day_7.txt", ',')
      .map {
        case (inputs :: _) => {
          AmplificationCircuit
            .sequenceWithMaxOutputWithFeedbackLoop(inputs)
        }
      }
      .get
      ._2
  )
}
