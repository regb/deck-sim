package decksim.game

case class Hand(cards: Vector[Card]) {

  def size: Int = cards.size

}
