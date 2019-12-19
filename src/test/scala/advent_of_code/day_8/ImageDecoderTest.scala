package advent_of_code.day_8

class ImageDecoderTest extends org.scalatest.FunSuite {

  test(
    "Properly calculates number of 3x2 layers for 123456789012"
  ) {
    assert(
      ImageDecoder
        .getLayers(
          3,
          2,
          List(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2)
        )
        .length == 2
    )
  }
}
