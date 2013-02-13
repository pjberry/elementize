package com.bitmotif.elementize

import org.scalatest.FunSpec

/**
 * User: pjberry
 * Date: 2/13/13
 * Time: 6:30 AM
 */
class BoxedElementString_UT extends FunSpec {

  describe("BoxedElementString") {

    it("given string parts, the substring should be altered by the input function and boxed") {
      val stringParts = StringParts(OriginalString("abcdefg"), Substring("cd"), Substring => "XX")

      val expected =
              """   __
                |  |  |
                |ab|XX|efg
                |  |__|""".stripMargin

      assert(BoxedElementString(stringParts).toString == expected)
    }
  }

}
