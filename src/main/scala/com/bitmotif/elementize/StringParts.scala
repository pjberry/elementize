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

  def apply(string: OriginalString, substring: Substring, f: Substring => String = x => x.value) = {
    val sliceBeforeTheString = string.before(substring)
    val theString = f(substring)
    val sliceAfterTheString = string.after(substring)
    new StringParts(sliceBeforeTheString, theString, sliceAfterTheString)
  }
}
