package com.bitmotif.elementize

import org.scalatest.FunSpec

/**
 * User: pjberry
 * Date: 2/10/13
 * Time: 11:01 AM
 */
class StringParts_UT extends FunSpec {

  it("should ") {
    val string = "abcdef"
    val substring = Substring("de", 3)

    val stringParts = StringParts(string, substring)

    assert(stringParts.prefix === "abc")
    assert(stringParts.theString === "De")
    assert(stringParts.suffix === "f")
  }

}
