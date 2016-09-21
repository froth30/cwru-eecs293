package edu.cwru.eecs293.ttf10.uxb

/** <p>
  * Represents a binary message carried between devices via UXB cable.
  * <p>
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf Hw2.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
final class BinaryMessage extends Message {

  private var value: BigInt = _

  /**
    * Initialize this message with a copy of the given value. If the value is null, the message should contain zero.
    * @param value the underlying value
    */
  def this(value: BigInt) {
    this
    this.value = if (value != null) value else 0
  }

  def getValue: BigInt = value

  /** Signifies that this <tt>BinaryMessage</tt> has reached the given device coming from the given connector.
    * @param device the device receiving this message
    * @param connector the connector at which this message arrived
    * @throws NullPointerException if either argument is null
    * @throws IllegalArgumentException if the connector does not belong to the device
    */
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  override def reach(device: Device, connector: Connector) {
    device.recv(this, connector)
  }

  override def equals(anObject: Any): Boolean = {
    anObject != null &&
      anObject.isInstanceOf[BinaryMessage] &&
      anObject.asInstanceOf[BinaryMessage].value == value
  }

}
