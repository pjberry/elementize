package com.bitmotif.elementize

/**
 * User: pjberry
 * Date: 2/3/13
 * Time: 7:05 PM
 */
class NameDecoration {

  val elementAbbreviations = List("H","He","Li","Be","B","C","N","O","F","Ne","Na","Mg","Al","Si","P","S","Cl","Ar","K","Ca","Sc","Ti","V","Cr","Mn","Fe","Co","Ni","Cu","Zn","Ga","Ge","As","Se","Br","Kr","Rb","Sr","Y","Zr","Nb","Mo","Tc","Ru","Rh","Pd","Ag","Cd","In","Sn","Sb","Te","I","Xe","Cs","Ba","La","Ce","Pr","Nd","Pm","Sm","Eu","Gd","Tb","Dy","Ho","Er","Tm","Yb","Lu","Hf","Ta","W","Re","Os","Ir","Pt","Au","Hg","Tl","Pb","Bi","Po","At","Rn","Fr","Ra","Ac","Th","Pa","U","Np","Pu","Am","Cm","Bk","Cf","Es","Fm","Md","No","Lr","Rf","Db","Sg","Bh","Hs","Mt","Ds","Rg","Cn","Uut","Fl","Uup","Lv","Uus","Uuo")

  val lowerCaseElementAbbreviations = elementAbbreviations.map(_.toLowerCase)

  def elementize(name: String) = {
    val elementized = elementized_private(name)
    val elementStart = elementized.indexOf("|")
    val top = List().padTo(elementStart + 1, " ").mkString + "__"
    val sides = List().padTo(elementStart , " ").mkString + "|  |"
    val bottom = List().padTo(elementStart , " ").mkString + "|__|"
    top + "\n" + sides + "\n" + elementized + "\n" + bottom
  }

  private def elementized_private(name: String) = {
    val charList = name.toList
    val chunkedName = charList.sliding(2).toList

    val indexInChunkedName = chunkedName.indexWhere(chunk => lowerCaseElementAbbreviations.contains(chunk.mkString.toLowerCase))

    val elementChunk = chunkedName(indexInChunkedName)
    val indexInName = charList.indexOfSlice(elementChunk)

    // before the chunk
    val beforeElement = charList.take(indexInName)

    // the chunk
    val rawElement = charList.slice(indexInName, indexInName + 2)
    val element = List(rawElement(0).toString.toUpperCase, rawElement(1))
    val charArray = "|".toCharArray
    val pipe = charArray(0)
    val decorated =  (pipe :: element) :+ pipe

    // after the chunk
    val afterTheElement = charList.slice(indexInName +  2, charList.size)

    // elementize
    (beforeElement ::: decorated ::: afterTheElement).mkString
  }
}
