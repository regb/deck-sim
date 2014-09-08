package decksim
package mtg

import CardTypes._

import org.scalatest.FunSuite

class CardTypesTests extends FunSuite {

  object WhiteSpell extends Spell {
    override val manaCost: ManaCost = ManaCost.white(1)
  }

  test("Can spell be played from lands") {
    assert(WhiteSpell.canBePlayedFromLands(Set(Plain)))
    assert(WhiteSpell.canBePlayedFromLands(Set(Mountain, Island, Plain)))
    assert(!WhiteSpell.canBePlayedFromLands(Set(Mountain)))
    assert(!WhiteSpell.canBePlayedFromLands(Set(Island, Mountain)))
  }

}
