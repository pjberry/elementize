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

  def apply(string: OriginalString, substring: Substring, f: Substring => String) = {
    val sliceBeforeTheString = string.before(substring)
    val theString = f(substring)
    val sliceAfterTheString = string.after(substring)
    new StringParts(sliceBeforeTheString, theString, sliceAfterTheString)
  }
}

class OriginalString(val original: String)  {

  def before(substring: Substring) = {
    val upTo = original.indexOfSlice(substring.value)
    original.slice(0, upTo)
  }

  def after(substring: Substring) = {
    val startIndex = original.indexOfSlice(substring.value) + substring.size
    original.slice(startIndex, original.size)
  }

  def take(index: Int) = original.take(index)

  def slice(from: Int, until: Int) = original.slice(from, until)

  def size = original.size
}

object OriginalString {
  def apply(original: String) = new OriginalString(original)
}