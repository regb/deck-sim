package decksim.game

class Deck(cards: Vector[Card]) {

  def shuffle: Deck = {
    Deck(scala.util.Random.shuffle(cards))
  }


  /*
   * Draw cards in deck, in current order
   */
  def drawHand(size: Int): Hand = {
    Hand(cards.take(size))
  }

}

object Deck {

  def apply(cards: Seq[Card]*): Deck = new Deck(cards.flatten.toVector)

}
