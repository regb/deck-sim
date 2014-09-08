package decksim
package simulator

import game._

object Simulator {

  def percentageGoodHand(deck: Deck, handSize: Int, condition: (Hand) => Boolean): Double = {

    var maxTrials: Int = 500000

    var currentTrial: Int = 0

    var successCount: Int = 0

    var shuffledDeck: Deck = deck.shuffle

    while(currentTrial < maxTrials) {

      if(condition(shuffledDeck.drawHand(handSize)))
        successCount += 1

      shuffledDeck = shuffledDeck.shuffle
      currentTrial += 1
    }

    successCount.toDouble / maxTrials.toDouble
  }

}
