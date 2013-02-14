package com.bitmotif.elementize

/**
 * User: pjberry
 * Date: 2/13/13
 * Time: 5:58 AM
 */
class EnhancedString(val value: String)  {

  def before(substring: Substring) = {
    val upTo = value.indexOfSlice(substring.value)
    value.slice(0, upTo)
  }

  def after(substring: Substring) = {
    val index = value.indexOfSlice(substring.value)
    if (index < 0) "" else  value.slice(index + substring.size, value.size)
  }

}

object EnhancedString {
  def apply(original: String) = new EnhancedString(original)
}
