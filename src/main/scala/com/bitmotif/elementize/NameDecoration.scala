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

  private val MAX_ABBREVIATION_SIZE: Int = 2

  def boxElementAbbreviation(name: String) =
    findElementAbbreviationIndex(name, MAX_ABBREVIATION_SIZE) match {
      case Some(e)  => BoxedElementString( StringParts(name, e) )
      case None     => name
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

class ElementAbbreviationIndex(val elementAbbreviation: String, val index: Int)  {
  def numberOfLettersInAbbreviation: Int = elementAbbreviation.size
}

class StringParts(val sliceBeforeTheElementAbbreviation: String, val theElementAbbreviation: String, val sliceAfterTheElementAbbreviation: String) {
  def numberOfLettersInAbbreviation: Int = theElementAbbreviation.size
  def numberOfCharactersBeforeAbbreviation: Int = sliceBeforeTheElementAbbreviation.size
}

object StringParts {

  def apply(string: String, elementAbbreviationIndex: ElementAbbreviationIndex) = {
    val characters = string.toList
    val sliceBeforeTheElementAbbreviation = characters.take(elementAbbreviationIndex.index).mkString
    val theElementAbbreviation = createElementAbbreviation(characters, elementAbbreviationIndex)
    val sliceAfterTheElementAbbreviation = characters.slice(elementAbbreviationIndex.index + elementAbbreviationIndex.numberOfLettersInAbbreviation, characters.size).mkString
    new StringParts(sliceBeforeTheElementAbbreviation, theElementAbbreviation, sliceAfterTheElementAbbreviation)
  }

  private def createElementAbbreviation(characters: List[Char], elementAbbreviationIndex: ElementAbbreviationIndex) = {
    val startOfElementAbbreviation = elementAbbreviationIndex.index
    val endOfElementAbbreviationPlusOne = elementAbbreviationIndex.index + elementAbbreviationIndex.numberOfLettersInAbbreviation
    val elementSlice = characters.slice(startOfElementAbbreviation, endOfElementAbbreviationPlusOne)
    elementSlice.head.toUpper + elementSlice.tail.mkString
  }
}

class BoxedElementString(val top: String, val aboveTheElement: String, val theElement: String, val belowTheElement: String)

object BoxedElementString {

  def apply(parts: StringParts) = {
    val paddingToElementStart: String = List().padTo(parts.numberOfCharactersBeforeAbbreviation, " ").mkString
    val paddingOfElementAbbreviationSize: String = " " * parts.numberOfLettersInAbbreviation
    val horizontalLine = "_" * parts.numberOfLettersInAbbreviation

    val top = List().padTo(parts.numberOfCharactersBeforeAbbreviation + 1, " ").mkString + horizontalLine
    val aboveTheElement = paddingToElementStart + "|" + paddingOfElementAbbreviationSize + "|"
    val theElement = parts.sliceBeforeTheElementAbbreviation + "|" + parts.theElementAbbreviation + "|" + parts.sliceAfterTheElementAbbreviation
    val belowTheElement = paddingToElementStart + "|"+ ("_" * parts.numberOfLettersInAbbreviation) + "|"

    top + "\n" + aboveTheElement + "\n" + theElement + "\n" + belowTheElement
  }
}