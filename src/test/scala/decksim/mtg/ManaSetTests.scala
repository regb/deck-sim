package decksim
package mtg

import org.scalatest.FunSuite

class ManaSetTests extends FunSuite {

  val empty = ManaSet.empty

  val oneWhite = ManaSet.white(1)
  val oneBlue = ManaSet.blue(1)
  val oneBlack = ManaSet.black(1)
  val oneRed = ManaSet.red(1)
  val oneGreen = ManaSet.green(1)
  val oneColorless = ManaSet.colorless(1)

  val oneBlueWhite = ManaSet(
    Map(Colors.Blue -> 1, Colors.White -> 1), 0)

  val oneRedBlackColorless = ManaSet(
    Map(Colors.Red -> 1, Colors.Black -> 1), 1)


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

    assert(ManaSet.colored(Colors.White, 1) === oneWhite)
    assert(ManaSet.colored(Colors.Red, 1) === oneRed)
  }

  test("Addition") {
    assert(oneWhite + empty === oneWhite)
    assert(oneBlue + empty === oneBlue)
    assert(oneGreen + empty === oneGreen)

    assert((oneWhite + oneBlue) === oneBlueWhite)
    assert((oneBlue + oneWhite) === oneBlueWhite)
    assert((oneRed + oneBlack + oneColorless) === oneRedBlackColorless)
  }

  test("Total") {
    assert(oneWhite.total === 1)
    assert(oneBlue.total === 1)
    assert(oneBlack.total === 1)
    assert(oneRed.total === 1)
    assert(oneGreen.total === 1)
    assert(oneColorless.total === 1)

    assert(oneBlueWhite.total === 2)
    assert(oneRedBlackColorless.total === 3)
  }

  test("Cover") {
    assert(oneWhite.cover(oneWhite))
    assert(oneWhite.cover(oneColorless))
    assert(!oneWhite.cover(oneBlue))

    assert(oneBlueWhite.cover(oneWhite))
    assert(oneBlueWhite.cover(oneBlue))
    assert(oneBlueWhite.cover(oneColorless))
  }

}
