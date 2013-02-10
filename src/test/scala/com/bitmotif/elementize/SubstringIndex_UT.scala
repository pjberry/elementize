package com.bitmotif.elementize

import org.scalatest.FunSpec

/**
 * User: pjberry
 * Date: 2/10/13
 * Time: 10:44 AM
 */
class SubstringIndex_UT extends FunSpec {

  it("should have the values used in construction") {
    val substringIndex = SubstringIndex("a string", 2)

    assert(substringIndex.substring === "a string")
    assert(substringIndex.index === 2)
  }


}
