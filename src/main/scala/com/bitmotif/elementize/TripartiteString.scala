package com.bitmotif.elementize

/**
 * User: pjberry
 * Date: 2/10/13
 * Time: 11:01 AM
 */

class TripartiteString(val beginning: String, val middle: String, val end: String) {
  def stringSize: Int = middle.size
  def prefixSize: Int = beginning.size
}

object TripartiteString {

  def apply(string: OriginalString, substring: Substring, f: Substring => String) = {
    val sliceBeforeTheString = string.before(substring)
    val theString = f(substring)
    val sliceAfterTheString = string.after(substring)
    new TripartiteString(sliceBeforeTheString, theString, sliceAfterTheString)
  }
}
