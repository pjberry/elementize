package com.bitmotif.elementize

/**
 * User: pjberry
 * Date: 2/13/13
 * Time: 6:28 AM
 */
class BoxedElementString(val top: String, val aboveTheElement: String, val theElement: String, val belowTheElement: String) {

  override def toString = {
    top + "\n" + aboveTheElement + "\n" + theElement + "\n" + belowTheElement
  }
}

object BoxedElementString {

  def apply(parts: StringParts) = {
    val paddingToElementStart: String = List().padTo(parts.prefixSize, " ").mkString
    val paddingOfElementAbbreviationSize: String = " " * parts.stringSize
    val horizontalLine = "_" * parts.stringSize

    val top = List().padTo(parts.prefixSize + 1, " ").mkString + horizontalLine
    val aboveTheElement = paddingToElementStart + "|" + paddingOfElementAbbreviationSize + "|"
    val theElement = parts.prefix + "|" + parts.theString + "|" + parts.suffix
    val belowTheElement = paddingToElementStart + "|"+ ("_" * parts.stringSize) + "|"

    new BoxedElementString(top, aboveTheElement, theElement, belowTheElement)
  }
}
