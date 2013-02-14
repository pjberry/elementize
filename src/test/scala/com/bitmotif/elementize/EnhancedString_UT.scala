package com.bitmotif.elementize

import org.scalatest.FunSpec

/**
 * User: pjberry
 * Date: 2/13/13
 * Time: 5:59 AM
 */
class EnhancedString_UT extends FunSpec {

  private val string = "abcdefghi"
  private val substring = Substring("fgh")

  describe("the contents before a substring") {

    it("should return a string up to (but not including) the substring") {
      val originalString = EnhancedString(string)

      assert(originalString.before(substring) === "abcde")
    }

    it("should handle when the substring is not found in the string") {
      val originalString = EnhancedString(string)

      assert(originalString.before( Substring("XXX") ) === "")
    }
  }

  describe("the contents after a substring") {

    it("should return a string aftter the substring") {
      val originalString = EnhancedString(string)

      assert(originalString.after(substring) === "i")
    }

    it("should handle when the substring is not found in the string") {
      val originalString = EnhancedString(string)

      assert(originalString.after( Substring("XXX") ) === "")
    }
  }

}
