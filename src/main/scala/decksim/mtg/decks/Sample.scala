package decksim
package mtg
package decks

import dsl._
import game._
import CardTypes._

object Sample extends DeckHelper {

  object KeyCard extends Spell {
    override val manaCost = ManaCost.white(1)
  }
  object WhiteSpell extends Spell {
    override val manaCost = ManaCost.white(1)
  }
  object BlueSpell extends Spell {
    override val manaCost = ManaCost.blue(1)
  }
  object RedSpell extends Spell {
    override val manaCost = ManaCost.red(1)
  }

  val deck = Deck(
    11 x Island,
    12 x Plain,
    37 x WhiteSpell
  )

  def decentHand(hand: Hand): Boolean = {
    val nbIslands = hand.cards.count(_ == Island)
    nbIslands >= 2 && nbIslands <= 5
  }

  def keyCard(hand: Hand): Boolean = {
    hand.cards.contains(KeyCard)
  }

  def playableSpells(hand: Hand): Boolean = {
    val spells: Set[Spell] = hand.cards.filter(_.isInstanceOf[Spell]).toSet.asInstanceOf[Set[Spell]]
    val lands: Set[Land] = hand.cards.filter(_.isInstanceOf[Land]).toSet.asInstanceOf[Set[Land]]

    spells.exists(spell => spell.canBePlayedFromLands(lands))
  }

}
