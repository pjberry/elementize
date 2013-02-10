package com.bitmotif.elementize

/**
 * User: pjberry
 * Date: 2/10/13
 * Time: 10:35 AM
 */
class Substring(val substring: String, val index: Int)  {
  def size: Int = substring.size
}

object Substring {
  def apply(substring: String, index: Int) = {
    new Substring(substring, index)
  }
}