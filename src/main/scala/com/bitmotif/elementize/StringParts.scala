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

  def apply(string: String, indexInName: Int, slice: String, f: List[Char] => String) = {
    val substring = Substring(slice, indexInName)

    val characters = string.toList
    val sliceBeforeTheString = characters.take(indexInName).mkString
    val theString = createString(characters, substring, f)
    val sliceAfterTheString = characters.slice(substring.index + slice.size, characters.size).mkString
    new StringParts(sliceBeforeTheString, theString, sliceAfterTheString)
  }

  private def createString(characters: List[Char], substring: Substring, f: List[Char] => String) = {
    val startOfString = substring.index
    val substringPlusSize = substring.index + substring.size
    val sliceOfInterest = characters.slice(startOfString, substringPlusSize)
    f(sliceOfInterest)
  }
}
