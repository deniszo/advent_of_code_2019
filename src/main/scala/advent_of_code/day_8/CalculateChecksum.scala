package advent_of_code.day_8

import advent_of_code.utils.InputReader

object CalculateCheckSum extends App {
  println(
    InputReader
      .getLines("day_8.txt")
      .map { iter =>
        val withMinZeroes = ImageDecoder
          .getLayers(25, 6, iter.toList.head.split("").map(_.toInt).toList)
          .map(ImageDecoder.digitsPerLayer(_))
          .minBy { numDigits =>
            numDigits.getOrElse(0, Int.MaxValue)
          }

        withMinZeroes.getOrElse(1, 0) * withMinZeroes.getOrElse(2, 0)
      }
      .get
  )
}
