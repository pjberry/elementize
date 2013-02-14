package com.bitmotif.elementize

import annotation.tailrec

/**
 * User: pjberry
 * Date: 2/3/13
 * Time: 7:05 PM
 */

/*
variable names across the board
*/
class NameDecoration {

  private val MAX_ABBREVIATION_SIZE: Int = 2

  private val capitalizeString: Substring => String = x => x.value.head.toUpper + x.value.tail.mkString

  def boxElementAbbreviation(name: String) =
    findElementAbbreviationIndex(name, MAX_ABBREVIATION_SIZE) match {
      case Some(stringParts) => BoxedSubstring( stringParts ).toString
      case None              => name
    }

  @tailrec
  private def findElementAbbreviationIndex(name: String, numberOfLettersInAbbreviation: Int): Option[TripartiteString] = {

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
      Some( TripartiteString(EnhancedString(name), Substring(elementChunk.mkString), capitalizeString) )
    }
  }

}
