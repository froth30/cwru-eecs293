package hw1

import scala.io.StdIn

/**
  * Main class for Programming Assignment 1
  *
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see <a href="Hw1.pdf">https://blackboard.case.edu/bbcswebdav/pid-1366059-dt-content-rid-4244627_1/courses/eecs293_vxl11/Hw1.pdf</a>
  *
  * Theodore Frohlich <ttf10@case.edu>
  */
object Main {

  def main(args: Array[String]) {
    System.out.println("\n< Programming Assignment 1 (Java) >")

    System.out.print("\nList A:  ")
    val a: List[String] = readList

    System.out.print("\nList B:  ")
    val b: List[String] = readList

    System.out.print("\nLongest common prefix:  ")
    System.out.println(longestPrefix(a, b,
      (s1: String, s2: String) => s1.compareTo(s2)))
  }

  /**
    * Reads a line of text as a list of comma-separated strings.
    * @return a list of strings, with leading and trailing whitespace removed
    */
  private def readList: List[String] = {
    val list: List[String] = StdIn.readLine().split(",").toList
    list.foreach(s => s.trim)
    list
  }

  /**
    * Finds the longest common prefix in two lists.
    * @param a the first list
    * @param b the second list
    * @param cmp an admissible comparing function
    * @tparam T the type of the list elements to be compared
    * @return the prefix of maximum length that is common to both lists
    */
  private[hw1] def longestPrefix[T](a: List[T],
                                    b: List[T],
                                    cmp: Ordering[_ >: T]
                                   ): List[T] =
  {
    val maxLen: Int = math.min(a.size, b.size)
    val prefix: List[T] = Range(0, maxLen).toList
    for (i <- )
    prefix
  }
}