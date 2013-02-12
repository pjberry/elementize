package com.bitmotif.elementize

/**
 * User: pjberry
 * Date: 2/10/13
 * Time: 10:35 AM
 */
class Substring(val value: String)  {
  def size: Int = value.size
}

object Substring {
  def apply(value: String) = {
    new Substring(value)
  }
}