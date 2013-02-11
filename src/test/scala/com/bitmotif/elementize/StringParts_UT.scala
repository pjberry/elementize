package com.bitmotif.elementize

import org.scalatest.FunSpec

/**
 * User: pjberry
 * Date: 2/10/13
 * Time: 11:01 AM
 */
class StringParts_UT extends FunSpec {

  private val string = "abcdef"
  private val f = (x: List[Char]) => x.reverse.mkString

  it("should modify substring's contents as defined by the function passed in") {
    val substring = Substring("de", 3)

    val stringParts = StringParts(string, substring, f)

    assert(stringParts.prefix === "abc")
    assert(stringParts.theString === "ed")
    assert(stringParts.suffix === "f")
  }

  it("should do nothing to prefix and suffix") {
    val substring = Substring("de", 3)

    val stringParts = StringParts(string, substring, x => "")

    assert(stringParts.prefix === "abc")
    assert(stringParts.suffix === "f")
  }

}
