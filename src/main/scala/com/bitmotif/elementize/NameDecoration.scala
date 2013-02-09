package com.bitmotif.elementize

import annotation.tailrec

/**
 * User: pjberry
 * Date: 2/3/13
 * Time: 7:05 PM
 */

/*

using object to construct
clean up boxStrings
variable names across the board
*/
class NameDecoration {

  val elementAbbreviations = List("H","He","Li","Be","B","C","N","O","F","Ne","Na","Mg","Al","Si","P","S","Cl","Ar","K","Ca","Sc","Ti","V","Cr","Mn","Fe","Co","Ni","Cu","Zn","Ga","Ge","As","Se","Br","Kr","Rb","Sr","Y","Zr","Nb","Mo","Tc","Ru","Rh","Pd","Ag","Cd","In","Sn","Sb","Te","I","Xe","Cs","Ba","La","Ce","Pr","Nd","Pm","Sm","Eu","Gd","Tb","Dy","Ho","Er","Tm","Yb","Lu","Hf","Ta","W","Re","Os","Ir","Pt","Au","Hg","Tl","Pb","Bi","Po","At","Rn","Fr","Ra","Ac","Th","Pa","U","Np","Pu","Am","Cm","Bk","Cf","Es","Fm","Md","No","Lr","Rf","Db","Sg","Bh","Hs","Mt","Ds","Rg","Cn","Uut","Fl","Uup","Lv","Uus","Uuo")

  val lowerCaseElementAbbreviations = elementAbbreviations.map(_.toLowerCase)

  def boxElementAbbreviation(name: String) = {
    val elementAbbreviationIndex = findElementAbbreviationIndex(name, 2)

    elementAbbreviationIndex match {
      case Some(e) =>
        val boxStrings = BoxStrings(e.index, e.elementAbbreviation.size)

        val charList = name.toList
        val sliceBeforeTheElementAbbreviation = charList.take(e.index).mkString
        val theElementAbbreviation = charList.slice(e.index, e.index + e.elementAbbreviation.size)
        val sliceAfterTheElementAbbreviation = charList.slice(e.index +  e.elementAbbreviation.size, charList.size).mkString

        val elementized = sliceBeforeTheElementAbbreviation + "|" + theElementAbbreviation.head.toUpper + theElementAbbreviation.tail.mkString + "|" + sliceAfterTheElementAbbreviation

        boxStrings.top + "\n" + boxStrings.sides + "\n" + elementized + "\n" + boxStrings.bottom
      case None =>
        name
    }
  }

  class ElementAbbreviationIndex(val elementAbbreviation: String, val index: Int)

  @tailrec
  private def findElementAbbreviationIndex(name: String, numberOfLettersInAbbreviation: Int): Option[ElementAbbreviationIndex] = {

    val charList = name.toList
    val chunkedName = charList.sliding(numberOfLettersInAbbreviation).toList
    val index = chunkedName.indexWhere(chunk => lowerCaseElementAbbreviations.contains(chunk.mkString.toLowerCase))

    val nextCharSize = numberOfLettersInAbbreviation - 1
    if (nextCharSize == 0 && index == -1) {
      None
    }
    else if (index == -1) {
      findElementAbbreviationIndex(name, nextCharSize)
    }
    else {
      val elementChunk = chunkedName(index)
      val indexInName = charList.indexOfSlice(elementChunk)
      Some( new ElementAbbreviationIndex(elementChunk.mkString, indexInName) )
    }

  }

  class BoxStrings(val top: String, val sides: String, val bottom: String)

  object BoxStrings {

    def apply(elementStartPosition: Int, numberOfLettersInAbbreviation: Int) = {
      val top = List().padTo(elementStartPosition + 1, " ").mkString + ("_" * numberOfLettersInAbbreviation)
      val sides = List().padTo(elementStartPosition , " ").mkString + "|" + (" " * numberOfLettersInAbbreviation) + "|"
      val bottom = List().padTo(elementStartPosition , " ").mkString + "|"+ ("_" * numberOfLettersInAbbreviation) + "|"
      new BoxStrings(top, sides, bottom)
    }
  }
}
