package advent_of_code.day_2

import advent_of_code.utils.InputReader

object FindNounAndVerb extends App {
  println(
    InputReader
      .listOfIntVecs("day_2.txt", ',')
      .map {
        case (inputs :: _) =>
          for {
            noun <- 0 to 99
            verb <- 0 to 99
            val restored = ProgramAlarm.setNounAndVerb(noun, verb, inputs)
            val result = ProgramAlarm.execute(restored)
            if (result.head == 19690720)
          } yield 100 * noun + verb
      }
      .get
  )
}
