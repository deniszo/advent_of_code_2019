package advent_of_code.utils

import scala.util.{Try, Success}

class InputReaderTest extends org.scalatest.FunSuite {
  test("getLines for non-existing file returns Failure") {
    assert(InputReader.getLines("foo.txt").isFailure)
  }

  test("getLines for existing file returns Success with correct data") {
    assert(
      InputReader
        .getLines("test_number_by_line.txt")
        .map(_.toList)
        .getOrElse(Nil) === List("148319", "54894", "105685", "136247")
    )
  }

  test(
    "listOfInts for existing file with int values returns Success[List[Int]]"
  ) {
    assert(
      InputReader
        .listOfInts("test_number_by_line.txt")
        === Success(List(148319, 54894, 105685, 136247))
    )
  }

  test(
    "listOfIntVecs for existing file with correct input returns Success[List[Vector[Int]]]"
  ) {
    assert(
      InputReader
        .listOfIntVecs("test_list_of_numbers_by_line.txt", ',')
        === Success(List(Vector(1, 4, 8, 3), Vector(5, 4, 8, 9)))
    )
  }
}
