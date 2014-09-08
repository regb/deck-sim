package decksim
package mtg
package decks

import dsl._
import game._
import CardTypes._


object Dredge extends DeckHelper {

  trait Dredger extends Spell
  trait Discarder extends Spell

  object CardPool {
    
    object CarefulStudy extends Spell with Discarder {
      override val manaCost = ManaCost.blue(1)
    }
    object FaithlessLooting extends Spell with Discarder {
      override val manaCost = ManaCost.red(1)
    }
    object Breakthrough extends Spell {
      override val manaCost = ManaCost.blue(1)
    }
    object PutridImp extends Spell with Discarder {
      override val manaCost = ManaCost.black(1)
    }

    object GolgariGraveTroll extends Creature with Dredger {
      override val manaCost = ManaCost.green(1) + ManaCost.colorless(5)
    }
    object GolariThug extends Creature with Dredger {
      override val manaCost = ManaCost.black(1) + ManaCost.colorless(1)
    }
    object StinkweedImp extends Creature with Dredger {
      override val manaCost = ManaCost.black(1) + ManaCost.colorless(2)
    }

    object Blank extends Spell {
      override val manaCost = ManaCost.free
    }

    object CityOfBrass extends RainbowLand
    object GemstoneMine extends RainbowLand
    object ManaConfluence extends RainbowLand

    object CephalidColliseum extends OneColorLand {
      override val producedColor = Colors.Blue
    }

  }

  import CardPool._

  val deck = Deck(
    4 x CarefulStudy,
    4 x FaithlessLooting,
    4 x PutridImp,
    4 x Breakthrough,

    4 x GolgariGraveTroll,
    4 x GolariThug,
    4 x StinkweedImp,

    4 x CityOfBrass,
    2 x GemstoneMine,
    4 x ManaConfluence,

    3 x CephalidColliseum,

    19 x Blank
  )


  def goodHand(hand: Hand): Boolean = {
    val discarders = hand.cards.filter(_.isInstanceOf[Discarder]).toSet.asInstanceOf[Set[Discarder]]
    val dredgers = hand.cards.filter(_.isInstanceOf[Dredger]).toSet.asInstanceOf[Set[Dredger]]
    val lands = hand.cards.filter(_.isInstanceOf[Land]).toSet.asInstanceOf[Set[Land]]

    discarders.size > 0 && dredgers.size >= 0 && lands.size >= 0 &&
    discarders.exists(_.canBePlayedFromLands(lands))
  }

}
