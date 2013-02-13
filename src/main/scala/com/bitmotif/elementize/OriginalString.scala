package com.bitmotif.elementize

/**
 * User: pjberry
 * Date: 2/13/13
 * Time: 5:58 AM
 */
class OriginalString(val original: String)  {

  def before(substring: Substring) = {
    val upTo = original.indexOfSlice(substring.value)
    original.slice(0, upTo)
  }

  def after(substring: Substring) = {
    val index = original.indexOfSlice(substring.value)
    if (index < 0) "" else  original.slice(index + substring.size, original.size)
  }

}

object OriginalString {
  def apply(original: String) = new OriginalString(original)
}
