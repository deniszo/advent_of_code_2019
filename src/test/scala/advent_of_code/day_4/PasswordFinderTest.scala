package advent_of_code.day_4

class PasswordFinderTest extends org.scalatest.FunSuite {

  test(
    "Finds correct amount of passwords matching criterias between 102-300"
  ) {
    assert(
      PasswordFinder.findWithBruteforce(
        "102",
        "300"
      ) == 32
    )
  }

  test("112233 meets the criteria with not more than 2 adjacent numbers") {
    assert(
      PasswordFinder
        .hasNotMoreThanTwoMatchingAdjacentDigits(1, 1, List(1, 2, 2, 3, 3))
    )
  }

  test("111122 meets the criteria with not more than 2 adjacent numbers") {
    assert(
      PasswordFinder
        .hasNotMoreThanTwoMatchingAdjacentDigits(1, 1, List(1, 1, 1, 2, 2))
    )
  }

  test("223333 meets the criteria with not more than 2 adjacent numbers") {
    assert(
      PasswordFinder
        .hasNotMoreThanTwoMatchingAdjacentDigits(1, 2, List(2, 3, 3, 3, 3))
    )
  }
}
