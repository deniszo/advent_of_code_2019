package advent_of_code.day_6

import scala.annotation.tailrec

case class ParsedNode(
    parent: Option[String] = None,
    children: List[String] = Nil
)

object OrbitMapper {
  def parseInput(
      rawOrbits: List[String]
  ): Map[String, ParsedNode] = {
    rawOrbits.map(_.split("\\)")).foldLeft(Map.empty[String, ParsedNode]) {
      case (m, Array(parent, child)) => {
        val currentParent = m.getOrElse(parent, ParsedNode())
        val withUpdatedParent =
          m.updated(
            parent,
            currentParent
              .copy(children = currentParent.children :+ child)
          )
        withUpdatedParent.updated(child, ParsedNode(Some(parent)))
      }
    }
  }

  def ancestors(key: String, m: Map[String, ParsedNode]): List[String] = {
    @tailrec
    def collectAncestors(
        prevAncestors: List[String],
        key: String
    ): List[String] =
      m.get(key) match {
        case Some(ParsedNode(Some(parent), _)) =>
          collectAncestors(parent +: prevAncestors, parent)
        case _ => prevAncestors
      }

    collectAncestors(Nil, key)
  }

  def totalOrbits(planets: Map[String, ParsedNode]): Int =
    planets.foldLeft(0) {
      case (sum, (key, _)) => sum + ancestors(key, planets).length
    }

  def numberOfTransfers(
      dest: String,
      target: String,
      planets: Map[String, ParsedNode]
  ): Int = {
    val destAncestors = ancestors(dest, planets)
    val targAncestors = ancestors(target, planets)

    val samePath = destAncestors.intersect(targAncestors)

    (destAncestors.length - samePath.length) + (targAncestors.length - samePath.length)
  }
}
