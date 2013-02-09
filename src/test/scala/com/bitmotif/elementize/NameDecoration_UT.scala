package com.bitmotif.elementize

import org.scalatest.FunSpec

/**
 * User: pjberry
 * Date: 2/3/13
 * Time: 7:08 PM
 */
class NameDecoration_UT extends FunSpec {

  describe("Name Decoration") {

    it("should box the two letter abbreviation") {
      val nameDecoration = new NameDecoration()

      val decorated = nameDecoration.boxElementAbbreviation("Anna Ferris")

      val expected =
        """   __
          |  |  |
          |An|Na| Ferris
          |  |__|""".stripMargin

      assert(decorated === expected)
    }

    it("should box the single letter abbreviation if there is no two letter abbreviation") {
      val nameDecoration = new NameDecoration()

      val decorated = nameDecoration.boxElementAbbreviation("Jim Lee")

      val expected =
        """  _
          | | |
          |J|I|m Lee
          | |_|""".stripMargin

      assert(decorated === expected)
    }

    it("should return the name modified if we can't find an element in the name") {
      val nameDecoration = new NameDecoration()

      val decorated = nameDecoration.boxElementAbbreviation("Mel Lee")

      val expected = "Mel Lee"

      assert(decorated === expected)
    }
  }

}
