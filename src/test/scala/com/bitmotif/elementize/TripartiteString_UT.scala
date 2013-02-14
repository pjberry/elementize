package com.bitmotif.elementize

import org.scalatest.FunSpec

/**
 * User: pjberry
 * Date: 2/10/13
 * Time: 11:01 AM
 */
class TripartiteString_UT extends FunSpec {

  private val original = new EnhancedString("abcdef")
  private val substring = new Substring("de")
  private val f = (x: Substring) => x.value.reverse

  describe("String Parts") {
      it("should modify slice's contents as defined by the function passed in") {
        val stringParts = TripartiteString(original, substring, f)

        assert(stringParts.beginning === "abc")
        assert(stringParts.middle === "ed")
        assert(stringParts.end === "f")
      }

      it("should do nothing to prefix and suffix") {
        val stringParts = TripartiteString(original, substring, x => "")

        assert(stringParts.beginning === "abc")
        assert(stringParts.end === "f")
      }
    }
}
