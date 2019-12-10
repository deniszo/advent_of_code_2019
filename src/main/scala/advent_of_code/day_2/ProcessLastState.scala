package advent_of_code.day_2

import advent_of_code.utils.InputReader

object ProcessLastState extends App {
  println(
    InputReader
      .listOfIntVecs("day_2.txt", ',')
      .map { case (inputs :: _) => ProgramAlarm.execute(inputs) }
      .get
  )
}
