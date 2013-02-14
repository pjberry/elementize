package com.bitmotif.elementize

import org.scalatest.FunSpec

/**
 * User: pjberry
 * Date: 2/10/13
 * Time: 10:44 AM
 */
class Substring_UT extends FunSpec {

  private val aString: String = "a string"

  it("should have the values used in construction") {
    val substring = Substring(aString)

    assert(substring.value === aString)
  }

  it("should give the size of the slice")  {
    val substring = Substring(aString)

    assert(substring.size === aString.size)
  }

  it("should transform based on the the function given")  {
    val substring = Substring(aString, (x: String) => x.reverse)

    val transformedSubstring = substring.transform
    assert(transformedSubstring === "gnirts a")
  }

}
