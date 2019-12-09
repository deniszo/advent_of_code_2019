package advent_of_code.utils

import scala.util.{Try, Success}

class InputReaderTest extends org.scalatest.FunSuite {
  test("getLines for non-existing file returns Failure") {
    assert(InputReader.getLines("foo.txt").isFailure)
  }

  test("getLines for existing file returns Success with correct data") {
    assert(
      InputReader
        .getLines("test_input.txt")
        .map(_.toList)
        .getOrElse(Nil) === List("148319", "54894", "105685", "136247")
    )
  }

  test(
    "listOfInts for existing file with int values returns Success[List[Int]]"
  ) {
    assert(
      InputReader
        .listOfInts("test_input.txt")
        === Success(List(148319, 54894, 105685, 136247))
    )
  }
}
