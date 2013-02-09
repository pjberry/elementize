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
      case Some(e)  => assembleElementizedName(name, e)
      case None     => name
    }
  }

  def assembleElementizedName(name: String, e: ElementAbbreviationIndex): String = {
    val characters = name.toList
    val sliceBeforeTheElementAbbreviation = characters.take(e.index).mkString
    val theElementAbbreviation = createElementAbbreviation(characters, e)
    val sliceAfterTheElementAbbreviation = characters.slice(e.index + e.elementAbbreviation.size, characters.size).mkString
    val parts = new StringParts(sliceBeforeTheElementAbbreviation, theElementAbbreviation, sliceAfterTheElementAbbreviation)
    boxTheElement(parts, e)
  }

  private def boxTheElement(parts: StringParts, e: ElementAbbreviationIndex): String = {
    val elementized = parts.sliceBeforeTheElementAbbreviation + "|" + parts.theElementAbbreviation + "|" + parts.sliceAfterTheElementAbbreviation
    val boxStrings = BoxStrings(e)
    boxStrings.top + "\n" + boxStrings.sides + "\n" + elementized + "\n" + boxStrings.bottom
  }

  private def createElementAbbreviation(characters: List[Char], elementAbbreviationIndex: ElementAbbreviationIndex) = {
    val startOfElementAbbreviation = elementAbbreviationIndex.index
    val endOfElementAbbreviationPlusOne = elementAbbreviationIndex.index + elementAbbreviationIndex.elementAbbreviation.size
    val elementSlice = characters.slice(startOfElementAbbreviation, endOfElementAbbreviationPlusOne)
    elementSlice.head.toUpper + elementSlice.tail.mkString
  }

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

}

//numberOfLettersInAbbreviation
class ElementAbbreviationIndex(val elementAbbreviation: String, val index: Int)

class StringParts(val sliceBeforeTheElementAbbreviation: String, val theElementAbbreviation: String, val sliceAfterTheElementAbbreviation: String)

class BoxStrings(val top: String, val sides: String, val bottom: String)

object BoxStrings {

  def apply(e: ElementAbbreviationIndex) = {
    val top = List().padTo(e.index + 1, " ").mkString + ("_" * e.elementAbbreviation.size)
    val sides = List().padTo(e.index , " ").mkString + "|" + (" " * e.elementAbbreviation.size) + "|"
    val bottom = List().padTo(e.index , " ").mkString + "|"+ ("_" * e.elementAbbreviation.size) + "|"
    new BoxStrings(top, sides, bottom)
  }
}