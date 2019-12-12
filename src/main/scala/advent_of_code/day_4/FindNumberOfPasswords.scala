package advent_of_code.day_4

object FindNumberOfPasswordsWithAtLeastTwoAdjacentDigits extends App {
  println(
    PasswordFinder.findWithBruteforce("240298", "784956")
  )
}

object FindNumberOfPaswordsWithNotMoreThanTwoAdjacentDigits extends App {
  println(
    PasswordFinder.findWithBruteforceNotMoreThanTwoAdjacent("240298", "784956")
  )
}
