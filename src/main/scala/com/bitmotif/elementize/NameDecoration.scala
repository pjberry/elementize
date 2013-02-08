package com.bitmotif.elementize

/**
 * User: pjberry
 * Date: 2/3/13
 * Time: 7:05 PM
 */

/*
@tailrec vs regular recursion ... nested if
using object to construct
clean up boxStrings
*/
class NameDecoration {

  val elementAbbreviations = List("H","He","Li","Be","B","C","N","O","F","Ne","Na","Mg","Al","Si","P","S","Cl","Ar","K","Ca","Sc","Ti","V","Cr","Mn","Fe","Co","Ni","Cu","Zn","Ga","Ge","As","Se","Br","Kr","Rb","Sr","Y","Zr","Nb","Mo","Tc","Ru","Rh","Pd","Ag","Cd","In","Sn","Sb","Te","I","Xe","Cs","Ba","La","Ce","Pr","Nd","Pm","Sm","Eu","Gd","Tb","Dy","Ho","Er","Tm","Yb","Lu","Hf","Ta","W","Re","Os","Ir","Pt","Au","Hg","Tl","Pb","Bi","Po","At","Rn","Fr","Ra","Ac","Th","Pa","U","Np","Pu","Am","Cm","Bk","Cf","Es","Fm","Md","No","Lr","Rf","Db","Sg","Bh","Hs","Mt","Ds","Rg","Cn","Uut","Fl","Uup","Lv","Uus","Uuo")

  val lowerCaseElementAbbreviations = elementAbbreviations.map(_.toLowerCase)

  def elementize(name: String) = {
    val elementIndex = findElementIndex(name, 2)

    elementIndex match {
      case Some(e) =>
        val boxStrings = BoxStrings(e.index, e.elementAbbreviation.size)

        val charList = name.toList
        val beforeElement = charList.take(e.index).mkString
        val rawElement = charList.slice(e.index, e.index + e.elementAbbreviation.size)
        val afterElement = charList.slice(e.index +  e.elementAbbreviation.size, charList.size).mkString
        val elementized = beforeElement + "|" + rawElement.head.toUpper + rawElement.tail.mkString + "|" + afterElement

        boxStrings.top + "\n" + boxStrings.sides + "\n" + elementized + "\n" + boxStrings.bottom
      case None =>
        name
    }
  }

  class ElementIndex(val elementAbbreviation: String, val index: Int)

  def findElementIndex(name: String, numberOfCharsInAbbreviation: Int): Option[ElementIndex] = {

    if (numberOfCharsInAbbreviation == 0) {
      None
    }
    else {
      val charList = name.toList
      val chunkedName = charList.sliding(numberOfCharsInAbbreviation).toList
      val index = chunkedName.indexWhere(chunk => lowerCaseElementAbbreviations.contains(chunk.mkString.toLowerCase))

      if (index == -1) {
        findElementIndex(name, numberOfCharsInAbbreviation - 1)
      }
      else {
        val elementChunk = chunkedName(index)
        val indexInName = charList.indexOfSlice(elementChunk)
        Some( new ElementIndex(elementChunk.mkString, indexInName) )
      }
    }

  }

  class BoxStrings(val top: String, val sides: String, val bottom: String)

  object BoxStrings {

    def apply(elementStartPosition: Int, numberOfCharactersInAbbreviation: Int) = {
      val top = List().padTo(elementStartPosition + 1, " ").mkString + ("_" * numberOfCharactersInAbbreviation)
      val sides = List().padTo(elementStartPosition , " ").mkString + "|" + (" " * numberOfCharactersInAbbreviation) + "|"
      val bottom = List().padTo(elementStartPosition , " ").mkString + "|"+ ("_" * numberOfCharactersInAbbreviation) + "|"
      new BoxStrings(top, sides, bottom)
    }
  }
}
