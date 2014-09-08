package decksim
package dsl

import game._

import scala.language.implicitConversions

trait DeckHelper {


  case class IntWrapper(underlying: Int) {

    def x(card: Card): Seq[Card] = (1 to underlying).map(_ => card)

  }

  implicit def toIntWrapper(x: Int) = IntWrapper(x)


}
