package edu.cwru.eecs293.ttf10.uxb

/**
  * Represents a binary message carried between devices via UXB cable.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * <br> [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4287477_1/xid-4287477_1 Hw2.pdf]]
  * @since Programming Assignment 2
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Ted Frohlich < ttf10@case.edu >
  */
final class BinaryMessage extends Message {
  
  private var value: BigInt = 0
  
  /**
    * Initialize this message with a copy of the given value. If the value is null, the message should contain zero.
    *
    * @param value the underlying value
    */
  def this(value: BigInt) {
    this
    if (value != null) this.value = value
  }
  
  def getValue: BigInt = value
  
  /** Signifies that this <tt>BinaryMessage</tt> has reached the given device coming from the given connector.
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
  
  override def equals(anObject: Any): Boolean = {
    anObject != null &&
      anObject.isInstanceOf[BinaryMessage] &&
      anObject.asInstanceOf[BinaryMessage].value == value
  }
  
}
