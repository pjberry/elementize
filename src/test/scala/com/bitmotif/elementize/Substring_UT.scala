package com.bitmotif.elementize

import org.scalatest.FunSpec

/**
 * User: pjberry
 * Date: 2/10/13
 * Time: 10:44 AM
 */
class Substring_UT extends FunSpec {

  private val aString: String = "a string"
  private val index = 2

  it("should have the values used in construction") {
    val substring = Substring(aString, index)

    assert(substring.substring === aString)
    assert(substring.index === index)
  }

  it("should give the size of the slice")  {
    val substring = Substring(aString, index)

    assert(substring.size === aString.size )
  }

}
