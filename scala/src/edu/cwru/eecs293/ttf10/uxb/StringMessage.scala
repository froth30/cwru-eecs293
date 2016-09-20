package edu.cwru.eecs293.ttf10.uxb

/**
  * Represents a string message carried between devices via UXB cable.
  *
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 3  |  Due at beginning of discussion on Wednesday, September 21, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/courses/eecs293_vxl11/Hw3%283%29.pdf Hw3.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
final class StringMessage extends Message {

  private var string: String = _

  /**
    * Initialize this message with the given string. If the string is null, the message should contain an empty non-null string.
    * @param string the underlying string
    */
  def this(string: String) {
    this
    this.string = if (string != null) string else ""
  }

  /**
    * Returns the underlying string.
    * @return the underlying string
    */
  def getString: String = string

  /**
    * Compares this string message to the specified object.
    * @param anObject the object to compare this <tt>StringMessage</tt> against
    * @return <tt>true</tt> if and only if the argument is not null, is a <tt>StringMessage</tt> object, and if the underlying strings are equal
    */
  override def equals(anObject: Any): Boolean = {
    anObject != null &&
      anObject.isInstanceOf[StringMessage] &&
      anObject.asInstanceOf[StringMessage].string.equals(string)
  }

}
