package com.bitmotif.elementize

/**
 * User: pjberry
 * Date: 2/10/13
 * Time: 10:35 AM
 */
class SubstringIndex(val substring: String, val index: Int)  {
  def substringSize: Int = substring.size
}

object SubstringIndex {
  def apply(substring: String, index: Int) = {
    new SubstringIndex(substring, index)
  }
}