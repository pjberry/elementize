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

  private def padTo: Int => String = length => List().padTo(length, " ").mkString
  private def underScoreTo: Int => String = length => List().padTo(length, "_").mkString

  def apply(parts: StringParts) = {

    val top = padTo(parts.prefixSize + 1) + underScoreTo(parts.stringSize)
    val aboveTheSubstring = padTo(parts.prefixSize) + "|" + padTo(parts.stringSize) + "|"
    val theSubstring = parts.prefix + "|" + parts.theString + "|" + parts.suffix
    val belowTheSubstring = padTo(parts.prefixSize) + "|" + underScoreTo(parts.stringSize) + "|"

    new BoxedSubstring(top, aboveTheSubstring, theSubstring, belowTheSubstring)
  }
}
