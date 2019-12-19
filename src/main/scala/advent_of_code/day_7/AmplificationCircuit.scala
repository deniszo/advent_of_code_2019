package advent_of_code.day_7

import advent_of_code.day_5.{TESTRunner, ProgramState}
import scala.annotation.tailrec

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

  def thrusterOutputWithFeedbackLoop(
      phaseSettings: Seq[Int],
      instructions: Vector[Int]
  ): Int = {

    @tailrec
    def loop(
        lastOutput: Int,
        states: List[ProgramState]
    ): Int = {
      val (allHalted, newStates, lo) =
        states.foldLeft((true, List.empty[ProgramState], lastOutput)) {
          case ((allHalted, newStates, inputFromPrevious), state) => {
            val newState = TESTRunner.runTillNextOutput(
              state.copy(inputs = state.inputs :+ inputFromPrevious)
            )

            (
              allHalted && newState.halted,
              newStates :+ newState,
              newState.outputs.last
            )
          }
        }

      if (allHalted) lo
      else loop(lo, newStates)
    }

    loop(
      0,
      phaseSettings.map { phase =>
        ProgramState(instructions, List(phase))
      }.toList
    )
  }

  def sequenceWithMaxOutputWithFeedbackLoop(
      instructions: Vector[Int]
  ): (Seq[Int], Int) = {
    (5 to 9).permutations.toList
      .map {
        case phaseSettings =>
          (
            phaseSettings.toSeq,
            thrusterOutputWithFeedbackLoop(phaseSettings.toSeq, instructions)
          )
      }
      .maxBy(_._2)
  }
}
