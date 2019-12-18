package advent_of_code.day_7

import advent_of_code.day_5.TESTRunner

object AmplificationCircuit {
  def thrusterOutput(
      phaseSettings: Seq[Int],
      instructions: Vector[Int]
  ): Int = {
    phaseSettings.foldLeft(0) {
      case (prevOutput, phaseSetting) =>
        TESTRunner.execute(List(phaseSetting, prevOutput), instructions)._2.head
    }
  }

  def sequenceWithMaxOutput(
      numberOfAmplifiers: Int,
      instructions: Vector[Int]
  ): (Seq[Int], Int) = {
    (0 until numberOfAmplifiers).permutations.toList
      .map {
        case phaseSettings =>
          (
            phaseSettings.toSeq,
            thrusterOutput(phaseSettings.toSeq, instructions)
          )
      }
      .maxBy(_._2)
  }
}
