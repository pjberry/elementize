package com.bitmotif.elementize

import org.scalatest.FunSpec

/**
 * User: pjberry
 * Date: 2/3/13
 * Time: 7:08 PM
 */
class NameDecoration_UT extends FunSpec {

  describe("Name Decoration") {

    it("Should put vertical bars on both sides of the two letter abbreviation") {
      val nameDecoration = new NameDecoration()

      val decorated = nameDecoration.elementize("Anna Ferris")

val expected =
"""   __
  |  |  |
  |An|Na| Ferris
  |  |__|""".stripMargin

      assert(decorated === expected)
    }
  }

}
