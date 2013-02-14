package com.bitmotif.elementize

/**
 * User: pjberry
 * Date: 2/10/13
 * Time: 10:35 AM
 */
class Substring(val value: String, f: String => String = x => x)  {
  def size: Int = value.size
  def transform = f(value)
}

object Substring {
  def apply(value: String) = {
    new Substring(value)
  }

  def apply(value: String, f: String => String) = {
    new Substring(value, f)
  }
}