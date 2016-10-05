package edu.cwru.eecs293.ttf10.uxb

/**
  * <p> Represents a string message carried between devices via UXB cable.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * @since Programming Assignment 3
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Ted Frohlich < ttf10@case.edu >
  */
final class StringMessage extends Message {
  
  private var string: String = ""
  
  /**
    * Initialize this message with the given string. If the string is null, the message should contain an empty non-null string.
    *
    * @param string the underlying string
    */
  def this(string: String) {
    this
    if (string != null) this.string = string
  }
  
  /**
    * Returns the underlying string.
    *
    * @return the underlying string
    */
  def getString: String = string
  
  /** Signifies that this <tt>StringMessage</tt> has reached the given device coming from the given connector.
    *
    * @param device    the device receiving this message
    * @param connector the connector at which this message arrived
    * @throws NullPointerException     if either argument is null
    * @throws IllegalArgumentException if the connector does not belong to the device
    */
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  @deprecated("Method chain Connector.recv >> Message.reach >> Device.recv no longer requires middleman", "hw4")
  override def reach(device: Device, connector: Connector) {
    device.recv(this, connector)
  }
  
  /**
    * Compares this string message to the specified object.
    *
    * @param anObject the object to compare this <tt>StringMessage</tt> against
    * @return <tt>true</tt> if and only if the argument is not null, is a <tt>StringMessage</tt> object, and if the underlying strings are equal
    */
  override def equals(anObject: Any): Boolean = {
    anObject != null &&
      anObject.isInstanceOf[StringMessage] &&
      anObject.asInstanceOf[StringMessage].string == string
  }
  
  // METHODS DELEGATED TO THE UNDERLYING STRING...
  
  def length: Int = string.length()
  
  def charAt(index: Int): Char = string.charAt(index)
  
  def contains(s: CharSequence): Boolean = string.contains(s)
  
  def endsWith(suffix: String): Boolean = string.endsWith(suffix)
  
  def startsWith(prefix: String): Boolean = string.startsWith(prefix)
  
  def indexOf(ch: Int): Int = string.indexOf(ch)
  
  def indexOf(ch: Int, fromIndex: Int): Int = string.indexOf(ch, fromIndex)
  
  def indexOf(str: String): Int = string.indexOf(str)
  
  def indexOf(str: String, fromIndex: Int): Int = string.indexOf(str, fromIndex)
  
  def lastIndexOf(ch: Int): Int = string.lastIndexOf(ch)
  
  def lastIndexOf(ch: Int, fromIndex: Int): Int = string.lastIndexOf(ch, fromIndex)
  
  def lastIndexOf(str: String): Int = string.lastIndexOf(str)
  
  def lastIndexOf(str: String, fromIndex: Int): Int = string.lastIndexOf(str, fromIndex)
  
  def isEmpty: Boolean = string.isEmpty
  
  override def hashCode: Int = string.hashCode()
  
}
