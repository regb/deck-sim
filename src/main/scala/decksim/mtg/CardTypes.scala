package decksim
package mtg

import game._

object CardTypes {

  abstract class Land extends Card {
    def canProduce: Set[ManaSet]
  }

  abstract class Spell extends Card {

    val manaCost: ManaCost

    def convertedManaCost: Int = manaCost.convertedManaCost

    def colors: Set[Colors.Value] = manaCost.colors

    def isFree: Boolean = convertedManaCost == 0

    def isWhite: Boolean = colors.contains(Colors.White)
    def isBlue: Boolean = colors.contains(Colors.Blue)
    def isBlack: Boolean = colors.contains(Colors.Black)
    def isRed: Boolean = colors.contains(Colors.Red)
    def isGreen: Boolean = colors.contains(Colors.Green)


    def canBePlayedFromLands(lands: Set[Land]): Boolean = {
      val availableMana: ManaSet =
        lands.foldLeft(ManaSet.empty)((acc, land) => acc + land.canProduce.head)

      manaCost.canBePlayedWith(availableMana)
    }

  }

  trait Creature extends Spell
  trait Enchantment extends Spell
  trait Artifact extends Spell
  trait Instant extends Spell
  trait Sorcery extends Spell
  trait Planeswalker extends Spell


  trait OneColorLand extends Land {
    val producedColor: Colors.Value

    override def canProduce: Set[ManaSet] = Set(ManaSet.colored(producedColor, 1))
  }

  trait BasicLand extends OneColorLand

  object Plain extends BasicLand {
    override val producedColor: Colors.Value = Colors.White
  }
  object Island extends BasicLand {
    override val producedColor: Colors.Value = Colors.Blue
  }
  object Swamp extends BasicLand {
    override val producedColor: Colors.Value = Colors.Black
  }
  object Mountain extends BasicLand {
    override val producedColor: Colors.Value = Colors.Red
  }
  object Forest extends BasicLand {
    override val producedColor: Colors.Value = Colors.Green
  }

  trait RainbowLand extends Land {
    override val canProduce =
      Set(ManaSet.white(1), ManaSet.blue(1), ManaSet.black(1),
          ManaSet.red(1), ManaSet.green(1))
  }

}
