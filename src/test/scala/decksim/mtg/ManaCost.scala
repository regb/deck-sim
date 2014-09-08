package decksim
package mtg

import org.scalatest.FunSuite

class ManaCostTests extends FunSuite {

  val oneWhite = ManaCost.white(1)
  val oneBlue = ManaCost.blue(1)
  val oneBlack = ManaCost.black(1)
  val oneRed = ManaCost.red(1)
  val oneGreen = ManaCost.green(1)
  val oneColorless = ManaCost.colorless(1)

  val oneBlueWhite = ManaCost(ManaSet(
    Map(Colors.Blue -> 1, Colors.White -> 1), 0))

  val oneRedBlackColorless = ManaCost(ManaSet(
    Map(Colors.Red -> 1, Colors.Black -> 1), 1))


  test("Builders") {
    assert(oneWhite.getColored(Colors.White) === 1)
    assert(oneWhite.getColored(Colors.Blue) === 0)
    assert(oneWhite.getColored(Colors.Black) === 0)
    assert(oneWhite.getColored(Colors.Red) === 0)
    assert(oneWhite.getColored(Colors.Green) === 0)
    assert(oneWhite.colorless === 0)

    assert(oneBlue.getColored(Colors.White) === 0)
    assert(oneBlue.getColored(Colors.Blue) === 1)
    assert(oneBlue.getColored(Colors.Black) === 0)
    assert(oneBlue.getColored(Colors.Red) === 0)
    assert(oneBlue.getColored(Colors.Green) === 0)
    assert(oneBlue.colorless === 0)

    assert(oneBlack.getColored(Colors.White) === 0)
    assert(oneBlack.getColored(Colors.Blue) === 0)
    assert(oneBlack.getColored(Colors.Black) === 1)
    assert(oneBlack.getColored(Colors.Red) === 0)
    assert(oneBlack.getColored(Colors.Green) === 0)
    assert(oneBlack.colorless === 0)

    assert(oneRed.getColored(Colors.White) === 0)
    assert(oneRed.getColored(Colors.Blue) === 0)
    assert(oneRed.getColored(Colors.Black) === 0)
    assert(oneRed.getColored(Colors.Red) === 1)
    assert(oneRed.getColored(Colors.Green) === 0)
    assert(oneRed.colorless === 0)

    assert(oneGreen.getColored(Colors.White) === 0)
    assert(oneGreen.getColored(Colors.Blue) === 0)
    assert(oneGreen.getColored(Colors.Black) === 0)
    assert(oneGreen.getColored(Colors.Red) === 0)
    assert(oneGreen.getColored(Colors.Green) === 1)
    assert(oneGreen.colorless === 0)

    assert(oneColorless.getColored(Colors.White) === 0)
    assert(oneColorless.getColored(Colors.Blue) === 0)
    assert(oneColorless.getColored(Colors.Black) === 0)
    assert(oneColorless.getColored(Colors.Red) === 0)
    assert(oneColorless.getColored(Colors.Green) === 0)
    assert(oneColorless.colorless === 1)

    assert(ManaCost.colored(Colors.White, 1) === oneWhite)
    assert(ManaCost.colored(Colors.Red, 1) === oneRed)
  }

  test("Converted mana cost") {
    assert(oneWhite.convertedManaCost === 1)
    assert(oneBlue.convertedManaCost === 1)
    assert(oneBlack.convertedManaCost === 1)
    assert(oneRed.convertedManaCost === 1)
    assert(oneGreen.convertedManaCost === 1)
    assert(oneColorless.convertedManaCost === 1)

    assert(oneBlueWhite.convertedManaCost === 2)
    assert(oneRedBlackColorless.convertedManaCost === 3)
  }

  test("Can be played with mana") {
    assert(oneWhite.canBePlayedWith(oneWhite.manaSet))
    assert(oneColorless.canBePlayedWith(oneWhite.manaSet))
    assert(!oneWhite.canBePlayedWith(oneBlue.manaSet))

    assert(oneWhite.canBePlayedWith(oneBlueWhite.manaSet))
    assert(oneBlue.canBePlayedWith(oneBlueWhite.manaSet))
    assert(oneColorless.canBePlayedWith(oneBlueWhite.manaSet))
  }

}
