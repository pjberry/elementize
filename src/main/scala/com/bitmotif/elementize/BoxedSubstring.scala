package com.bitmotif.elementize

/**
 * User: pjberry
 * Date: 2/13/13
 * Time: 6:28 AM
 */
class BoxedSubstring(val top: String, val aboveTheSubstring: String, val theSubstring: String, val belowTheSubstring: String) {

  override def toString = {
    top + "\n" + aboveTheSubstring + "\n" + theSubstring + "\n" + belowTheSubstring
  }
}

object BoxedSubstring {

  private def padTo: (String) => (Int => String) = character => ( length => List().padTo(length, character).mkString )
  private def whiteSpaceTo: Int => String = padTo(" ")
  private def underScoreTo: Int => String = padTo("_")

  def apply(parts: TripartiteString) = {

    val top = whiteSpaceTo(parts.prefixSize + 1) + underScoreTo(parts.stringSize)
    val aboveTheSubstring = whiteSpaceTo(parts.prefixSize) + "|" + whiteSpaceTo(parts.stringSize) + "|"
    val theSubstring = parts.beginning + "|" + parts.middle + "|" + parts.end
    val belowTheSubstring = whiteSpaceTo(parts.prefixSize) + "|" + underScoreTo(parts.stringSize) + "|"

    new BoxedSubstring(top, aboveTheSubstring, theSubstring, belowTheSubstring)
  }
}
