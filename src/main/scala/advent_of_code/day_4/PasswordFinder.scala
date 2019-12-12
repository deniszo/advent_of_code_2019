package advent_of_code.day_4

import scala.annotation.tailrec

object PasswordFinder {
  @tailrec
  private def digitsDoNotDecrease(head: Int, tail: List[Int]): Boolean =
    if (tail.isEmpty) true
    else if (tail.head < head) false
    else digitsDoNotDecrease(tail.head, tail.tail)

  @tailrec
  private def hasTwoMatchingAdjacentDigits(
      head: Int,
      tail: List[Int]
  ): Boolean =
    if (tail.isEmpty) false
    else head == tail.head || hasTwoMatchingAdjacentDigits(tail.head, tail.tail)

  def findWithBruteforce(lower: String, upper: String): Int = {
    val start = lower.toInt
    val end = upper.toInt

    (for {
      numInRange <- start to end
      val digits = numInRange.toString.split("").map(_.toInt).toList
      if (hasTwoMatchingAdjacentDigits(digits.head, digits.tail) && digitsDoNotDecrease(
        digits.head,
        digits.tail
      ))
    } yield digits).length
  }

  @tailrec
  def hasNotMoreThanTwoMatchingAdjacentDigits(
      segLen: Int,
      prev: Int,
      rest: List[Int]
  ): Boolean = {
    if (rest.isEmpty) segLen == 2
    else if (prev == rest.head)
      hasNotMoreThanTwoMatchingAdjacentDigits(
        segLen + 1,
        rest.head,
        rest.tail
      )
    else
      segLen == 2 || hasNotMoreThanTwoMatchingAdjacentDigits(
        1,
        rest.head,
        rest.tail
      )
  }

  def findWithBruteforceNotMoreThanTwoAdjacent(
      lower: String,
      upper: String
  ): Int = {
    val start = lower.toInt
    val end = upper.toInt

    (for {
      numInRange <- start to end
      val digits = numInRange.toString.split("").map(_.toInt).toList
      if (hasNotMoreThanTwoMatchingAdjacentDigits(1, digits.head, digits.tail) && digitsDoNotDecrease(
        digits.head,
        digits.tail
      ))
    } yield digits).length
  }
}
