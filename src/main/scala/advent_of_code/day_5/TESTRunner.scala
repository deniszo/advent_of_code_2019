package advent_of_code.day_5

import scala.annotation.tailrec
import scala.util.{Try, Success, Failure}

object Instruction {
  def apply(unnormalizedInstruction: Int): Instruction = {
    val revInstrItems =
      unnormalizedInstruction
        .toString()
        .reverse
        .split("")
        .padTo(5, "0")
        .reverse
        .toList
    (
      revInstrItems.take(3).map(_.toInt),
      revInstrItems.drop(3).mkString("").toInt
    ) match {
      case (List(a, b, c), opcode) => Instruction(a, b, c, opcode)
    }
  }
}

sealed trait Operation

case class Add(a: Int, b: Int, modifiedAt: Int, nextPos: Int) extends Operation
case class Mul(a: Int, b: Int, modifiedAt: Int, nextPos: Int) extends Operation
case class WriteInput(modifiedAt: Int, nextPos: Int) extends Operation
case class PrintOutput(outputAt: Int, nextPos: Int) extends Operation

case class JumpIfTrue(cond: Int, goTo: Int, nextPos: Int) extends Operation

case class JumpIfFalse(cond: Int, goTo: Int, nextPos: Int) extends Operation

case class LessThan(a: Int, b: Int, modifiedAt: Int, nextPos: Int)
    extends Operation

case class Equals(a: Int, b: Int, modifiedAt: Int, nextPos: Int)
    extends Operation

object Halt extends Operation
object UnknownOperation extends Operation

case class Instruction(aMode: Int, bMode: Int, cMode: Int, opcode: Int)
    extends Operation

object OperationAt {
  def apply(opcodeIdx: Int, instructions: Vector[Int]): Operation = {
    def findOperand(position: Int, mode: Int = 1): Int =
      mode match {
        case 0 => instructions(instructions(position))
        case 1 => instructions(position)
      }

    val Instruction(a, b, c, opcode) = Instruction(instructions(opcodeIdx))

    opcode match {
      case 1 =>
        Add(
          findOperand(opcodeIdx + 1, c),
          findOperand(opcodeIdx + 2, b),
          findOperand(opcodeIdx + 3),
          opcodeIdx + 4
        )

      case 2 =>
        Mul(
          findOperand(opcodeIdx + 1, c),
          findOperand(opcodeIdx + 2, b),
          findOperand(opcodeIdx + 3),
          opcodeIdx + 4
        )
      case 3 => WriteInput(instructions(opcodeIdx + 1), opcodeIdx + 2)
      case 4 => PrintOutput(instructions(opcodeIdx + 1), opcodeIdx + 2)
      case 5 =>
        JumpIfTrue(
          findOperand(opcodeIdx + 1, c),
          findOperand(opcodeIdx + 2, b),
          opcodeIdx + 3
        )
      case 6 =>
        JumpIfFalse(
          findOperand(opcodeIdx + 1, c),
          findOperand(opcodeIdx + 2, b),
          opcodeIdx + 3
        )

      case 7 =>
        LessThan(
          findOperand(opcodeIdx + 1, c),
          findOperand(opcodeIdx + 2, b),
          findOperand(opcodeIdx + 3),
          opcodeIdx + 4
        )

      case 8 =>
        Equals(
          findOperand(opcodeIdx + 1, c),
          findOperand(opcodeIdx + 2, b),
          findOperand(opcodeIdx + 3),
          opcodeIdx + 4
        )
      case 99 => Halt
      case _  => UnknownOperation
    }
  }
}

object TESTRunner {
  def setNounAndVerb(
      noun: Int,
      verb: Int,
      instructions: Vector[Int]
  ): Vector[Int] = {
    val withNoun = instructions.updated(1, noun)
    withNoun.updated(2, verb)
  }

  def execute(
      input: Int,
      instructions: Vector[Int]
  ): (Vector[Int], Vector[Int]) = {

    @tailrec
    def walkRec(
        currentPos: Int,
        currentState: (Vector[Int], Vector[Int])
    ): (Vector[Int], Vector[Int]) = {
      val (state, outputs) = currentState
      OperationAt(currentPos, state) match {
        case Add(a, b, modifiedAt, nextPos) =>
          walkRec(
            if (modifiedAt == currentPos) currentPos else nextPos,
            (
              state.updated(
                modifiedAt,
                a + b
              ),
              outputs
            )
          )

        case Mul(a, b, modifiedAt, nextPos) =>
          walkRec(
            if (modifiedAt == currentPos) currentPos else nextPos,
            (
              state.updated(
                modifiedAt,
                a * b
              ),
              outputs
            )
          )

        case JumpIfTrue(cond, goTo, nextPos) => {
          println(s"JumpIfTrue's cond is $cond")
          if (cond == 0) walkRec(nextPos, currentState)
          else
            walkRec(
              goTo,
              currentState
            )
        }

        case JumpIfFalse(cond, goTo, nextPos) =>
          if (cond != 0) walkRec(nextPos, currentState)
          else
            walkRec(
              goTo,
              currentState
            )

        case LessThan(a, b, modifiedAt, nextPos) =>
          walkRec(
            if (modifiedAt == currentPos) currentPos else nextPos,
            (
              state.updated(
                modifiedAt,
                if (a < b) 1 else 0
              ),
              outputs
            )
          )

        case Equals(a, b, modifiedAt, nextPos) =>
          walkRec(
            if (modifiedAt == currentPos) currentPos else nextPos,
            (
              state.updated(
                modifiedAt,
                if (a == b) 1 else 0
              ),
              outputs
            )
          )

        case WriteInput(writeAt, nextPos) =>
          walkRec(
            if (writeAt == currentPos) currentPos else nextPos,
            (
              state.updated(
                writeAt,
                input
              ),
              outputs
            )
          )

        case PrintOutput(outputAt, nextPos) =>
          walkRec(
            nextPos,
            (
              state,
              outputs.appended(
                state(outputAt)
              )
            )
          )
        case _ => (state, outputs)
      }
    }

    walkRec(0, (instructions, Vector.empty))
  }
}
