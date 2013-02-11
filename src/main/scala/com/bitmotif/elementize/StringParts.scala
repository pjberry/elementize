package com.bitmotif.elementize

/**
 * User: pjberry
 * Date: 2/10/13
 * Time: 11:01 AM
 */

class StringParts(val prefix: String, val theString: String, val suffix: String) {
  def stringSize: Int = theString.size
  def prefixSize: Int = prefix.size
}

object StringParts {

  def apply(string: String, substring: Substring) = {
    val characters = string.toList
    val sliceBeforeTheString = characters.take(substring.index).mkString
    val theString = createString(characters, substring)
    val sliceAfterTheString = characters.slice(substring.index + substring.size, characters.size).mkString
    new StringParts(sliceBeforeTheString, theString, sliceAfterTheString)
  }

  private def createString(characters: List[Char], substring: Substring) = {
    val startOfString = substring.index
    val substringPlusSize = substring.index + substring.size
    val elementSlice = characters.slice(startOfString, substringPlusSize)
    elementSlice.head.toUpper + elementSlice.tail.mkString
  }
}
