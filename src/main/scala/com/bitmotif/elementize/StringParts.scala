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

  def apply(string: OriginalString, substring: Substring, f: String => String) = {
    val sliceBeforeTheString = string.take(substring.index)
    val theString = createString(string, substring, f)
    val sliceAfterTheString = string.slice(substring.index + substring.size, string.size)
    new StringParts(sliceBeforeTheString, theString, sliceAfterTheString)
  }

  private def createString(string: OriginalString, substring: Substring, f: String => String) = {
    val startOfString = substring.index
    val substringPlusSize = substring.index + substring.size
    val sliceOfInterest = string.slice(startOfString, substringPlusSize)
    f(sliceOfInterest)
  }

}

class OriginalString(val original: String)  {
  def take(index: Int) = original.take(index)
  def slice(from: Int, until: Int) = original.slice(from, until)
  def size = original.size
}

object OriginalString {
  def apply(original: String) = new OriginalString(original)
}