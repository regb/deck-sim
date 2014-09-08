package decksim
package mtg

case class ManaSet(coloredMana: Map[Colors.Value, Int], colorless: Int) {

  def totalColored: Int = coloredMana.foldLeft(0)((acc, p) => acc + p._2)
  def total: Int = totalColored + colorless

  def getColored(color: Colors.Value): Int = coloredMana.getOrElse(color, 0)

  def cover(other: ManaSet): Boolean = {
    var spareMana = 0
    val keys: Set[Colors.Value] = (coloredMana.keys ++ other.coloredMana.keys).toSet
    val canPayColored = keys.forall(color => {
      val toPay = other.getColored(color)
      val available = coloredMana.getOrElse(color, 0)
      if(available >= toPay) {
        spareMana += (available - toPay)
        true
      } else false
    })
    canPayColored && spareMana + colorless >= other.colorless
  }

  def +(other: ManaSet): ManaSet = {
    val keys: Set[Colors.Value] = (coloredMana.keys ++ other.coloredMana.keys).toSet
    ManaSet(
      keys.map(color => 
        (color, coloredMana.getOrElse(color, 0) + other.coloredMana.getOrElse(color, 0))
      ).toMap,
      colorless + other.colorless
    )
  }
}

object ManaSet {

  def empty: ManaSet = ManaSet(Map(), 0)

  def white(quantity: Int) = ManaSet(Map(Colors.White -> quantity), 0)
  def blue(quantity: Int) = ManaSet(Map(Colors.Blue -> quantity), 0)
  def black(quantity: Int) = ManaSet(Map(Colors.Black -> quantity), 0)
  def red(quantity: Int) = ManaSet(Map(Colors.Red -> quantity), 0)
  def green(quantity: Int) = ManaSet(Map(Colors.Green -> quantity), 0)

  def colored(color: Colors.Value, quantity: Int) = ManaSet(Map(color -> quantity), 0)

  def colorless(quantity: Int) = ManaSet(Map(), quantity)
}

case class ManaCost(manaSet: ManaSet) {

  def getColored(color: Colors.Value): Int = manaSet.getColored(color)

  def colorless: Int = manaSet.colorless

  def colors: Set[Colors.Value] = manaSet.coloredMana.keys.toSet

  def convertedManaCost: Int = manaSet.total

  def canBePlayedWith(availableMana: ManaSet): Boolean = availableMana.cover(manaSet)

  def +(other: ManaCost) = ManaCost(manaSet + other.manaSet)

}

object ManaCost {

  def free: ManaCost = ManaCost(ManaSet.empty)

  def white(quantity: Int) = ManaCost(ManaSet.white(quantity))
  def blue(quantity: Int) = ManaCost(ManaSet.blue(quantity))
  def black(quantity: Int) = ManaCost(ManaSet.black(quantity))
  def red(quantity: Int) = ManaCost(ManaSet.red(quantity))
  def green(quantity: Int) = ManaCost(ManaSet.green(quantity))

  def colored(color: Colors.Value, quantity: Int) = ManaCost(ManaSet.colored(color, quantity))

  def colorless(quantity: Int) = ManaCost(ManaSet.colorless(quantity))

}
