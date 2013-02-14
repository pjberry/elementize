package com.bitmotif.elementize

import org.scalatest.FunSpec

/**
 * User: pjberry
 * Date: 2/10/13
 * Time: 11:01 AM
 */
class StringParts_UT extends FunSpec {

  private val original = new OriginalString("abcdef")
  private val substring = new Substring("de")
  private val f = (x: Substring) => x.value.reverse

  describe("String Parts") {
    it("should have the same components as those passed in") {
      val stringParts = StringParts(original, substring)

      assert(stringParts.prefix === "abc")
      assert(stringParts.theString === "de")
      assert(stringParts.suffix === "f")
    }
  }

  describe("String Parts Old") {
      it("should modify slice's contents as defined by the function passed in") {
        val stringParts = StringParts(original, substring, f)

        assert(stringParts.prefix === "abc")
        assert(stringParts.theString === "ed")
        assert(stringParts.suffix === "f")
      }

      it("should do nothing to prefix and suffix") {
        val stringParts = StringParts(original, substring, x => "")

        assert(stringParts.prefix === "abc")
        assert(stringParts.suffix === "f")
      }
    }
}
