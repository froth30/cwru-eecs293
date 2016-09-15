package edu.cwru.eecs293.ttf10.uxb

/**
  * Represents a binary message carried between devices via UXB cable.
  *
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see <a href="Hw2.pdf">https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf</a>
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
final class BinaryMessage extends Message {

  private var value = _

  def this(value: BigInt) {
    this
    this.value = value
  }

  def getValue: BigInt = value

  override def equals(anObject: Any): Boolean = {
    anObject != null &&
      anObject.isInstanceOf[BinaryMessage] &&
      eq(anObject.asInstanceOf[BinaryMessage].value, value)
  }

}
