package advent_of_code.day_8

object ImageDecoder {
  def getLayers(
      layerWidth: Int,
      layerHeight: Int,
      packet: List[Int]
  ): List[List[Int]] =
    packet
      .sliding(
        layerWidth * layerHeight,
        packet.length / (layerWidth * layerHeight)
      )
      .toList

  def digitsPerLayer(layer: List[Int]): Map[Int, Int] =
    layer.foldLeft(Map.empty[Int, Int]) {
      case (m, digit) => m.updated(digit, m.getOrElse(digit, 0) + 1)
    }
}
