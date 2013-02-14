package com.bitmotif.elementize

import org.scalatest.FunSpec

/**
 * User: pjberry
 * Date: 2/13/13
 * Time: 6:30 AM
 */
class BoxedSubstring_UT extends FunSpec {

  describe("Boxed Substring") {

    it("the substring should, given string parts,be altered by the input function and boxed") {
      val stringParts = TripartiteString(OriginalString("abcdefg"), Substring("cd"), Substring => "XX")

      val expected =
              """   __
                |  |  |
                |ab|XX|efg
                |  |__|""".stripMargin

      assert(BoxedSubstring(stringParts).toString == expected)
    }
  }

}
