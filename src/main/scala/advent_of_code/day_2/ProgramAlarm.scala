package advent_of_code.day_2

import scala.annotation.tailrec
import scala.util.{Try, Success, Failure}

sealed trait Operation

sealed trait Modification {
  def run(currentState: Vector[Int]): Vector[Int]
}

case class Add(a: Int, b: Int, modifiedAt: Int, nextPos: Int) extends Operation
case class Mul(a: Int, b: Int, modifiedAt: Int, nextPos: Int) extends Operation

object Halt extends Operation
object UnknownOperation extends Operation

object OperationAt {
  def apply(opcodeIdx: Int, instructions: Vector[Int]): Operation =
    instructions(opcodeIdx) match {
      case 1 =>
        Add(
          instructions(instructions(opcodeIdx + 1)),
          instructions(instructions(opcodeIdx + 2)),
          instructions(opcodeIdx + 3),
          opcodeIdx + 4
        )
      case 2 =>
        Mul(
          instructions(instructions(opcodeIdx + 1)),
          instructions(instructions(opcodeIdx + 2)),
          instructions(opcodeIdx + 3),
          opcodeIdx + 4
        )
      case 99 => Halt
      case _  => UnknownOperation
    }
}

object ProgramAlarm {
  def execute(instructions: Vector[Int]): Vector[Int] = {

    @tailrec
    def walkRec(currentPos: Int, currentState: Vector[Int]): Vector[Int] = {
      OperationAt(currentPos, currentState) match {
        case Add(a, b, modifiedAt, nextPos) =>
          walkRec(
            nextPos,
            currentState.updated(
              modifiedAt,
              a + b
            )
          )
        case Mul(a, b, modifiedAt, nextPos) =>
          walkRec(
            nextPos,
            currentState.updated(
              modifiedAt,
              a * b
            )
          )
        case _ => currentState
      }
    }

    walkRec(0, instructions)
  }
}
