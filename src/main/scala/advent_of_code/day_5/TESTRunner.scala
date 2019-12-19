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

case class ProgramState(
    instructions: Vector[Int],
    inputs: List[Int],
    outputs: Vector[Int] = Vector.empty,
    currentPos: Int = 0,
    halted: Boolean = false
)

object TESTRunner {
  def setNounAndVerb(
      noun: Int,
      verb: Int,
      instructions: Vector[Int]
  ): Vector[Int] = {
    val withNoun = instructions.updated(1, noun)
    withNoun.updated(2, verb)
  }

  def executeStep(state: ProgramState): ProgramState = {
    val ProgramState(instructions, inputs, outputs, currentPos, _) = state

    OperationAt(currentPos, instructions) match {
      case Add(a, b, modifiedAt, nextPos) =>
        state.copy(
          instructions = instructions.updated(
            modifiedAt,
            a + b
          ),
          currentPos = if (modifiedAt == currentPos) currentPos else nextPos
        )

      case Mul(a, b, modifiedAt, nextPos) =>
        state.copy(
          instructions = instructions.updated(
            modifiedAt,
            a * b
          ),
          currentPos = if (modifiedAt == currentPos) currentPos else nextPos
        )

      case JumpIfTrue(cond, goTo, nextPos) => {
        if (cond == 0) state.copy(currentPos = nextPos)
        else state.copy(currentPos = goTo)
      }

      case JumpIfFalse(cond, goTo, nextPos) =>
        if (cond != 0) state.copy(currentPos = nextPos)
        else state.copy(currentPos = goTo)

      case LessThan(a, b, modifiedAt, nextPos) =>
        state.copy(
          instructions = instructions.updated(
            modifiedAt,
            if (a < b) 1 else 0
          ),
          currentPos = if (modifiedAt == currentPos) currentPos else nextPos
        )

      case Equals(a, b, modifiedAt, nextPos) =>
        state.copy(
          instructions = instructions.updated(
            modifiedAt,
            if (a == b) 1 else 0
          ),
          currentPos = if (modifiedAt == currentPos) currentPos else nextPos
        )

      case WriteInput(writeAt, nextPos) => {
        state.copy(
          instructions = instructions.updated(
            writeAt,
            inputs.head
          ),
          inputs = inputs.tail,
          currentPos = if (writeAt == currentPos) currentPos else nextPos
        )
      }

      case PrintOutput(outputAt, nextPos) =>
        state.copy(
          outputs = outputs.appended(
            instructions(outputAt)
          ),
          currentPos = nextPos
        )

      case Halt => state.copy(halted = true)
    }
  }

  def runTillNextOutput(initialState: ProgramState): ProgramState = {

    @tailrec
    def loop(newState: ProgramState): ProgramState = {
      if (newState.halted || newState.outputs.length > initialState.outputs.length)
        newState
      else loop(executeStep(newState))
    }

    loop(initialState)
  }

  def execute(
      inputs: List[Int],
      instructions: Vector[Int]
  ): (Vector[Int], Vector[Int]) = {

    @tailrec
    def walkRec(
        state: ProgramState
    ): ProgramState = {
      if (state.halted) state
      else walkRec(executeStep(state))
    }

    val finalState = walkRec(ProgramState(instructions, inputs))

    (finalState.instructions, finalState.outputs)
  }
}
