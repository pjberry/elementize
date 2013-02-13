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

  def apply(parts: StringParts) = {
    val paddingToStart: String = List().padTo(parts.prefixSize, " ").mkString
    val paddingOfSubstringSize: String = " " * parts.stringSize
    val horizontalLine = "_" * parts.stringSize

    val top = List().padTo(parts.prefixSize + 1, " ").mkString + horizontalLine
    val aboveTheSubstring = paddingToStart + "|" + paddingOfSubstringSize + "|"
    val theSubstring = parts.prefix + "|" + parts.theString + "|" + parts.suffix
    val belowTheSubstring = paddingToStart + "|"+ ("_" * parts.stringSize) + "|"

    new BoxedSubstring(top, aboveTheSubstring, theSubstring, belowTheSubstring)
  }
}
