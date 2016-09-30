package edu.cwru.eecs293.ttf10.uxb

/**
  * <p> Represents a concrete UXB video peripheral device.
  * <p> This just might be better than the [[http://www.ufunk.net/en/gadgets/goamateur/ GoAmateur]]!
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * @since Programming Assignment 3
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Theodore Frohlich &lt;ttf10@case.edu&gt;
  */
class GoAmateur[T <: AbstractVideo.Builder[T]](private val builder: GoAmateur.Builder[T])
  extends AbstractVideo[GoAmateur.Builder[T]](builder) {
  
  /**
    * Signifies the arrival of a message at the given connector in the device.
    *
    * @param message   the string message being received
    * @param connector the connector at which the message arrived
    * @throws NullPointerException     if either argument is null
    * @throws IllegalArgumentException if the connector does not belong to this device
    */
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  override def recv(message: StringMessage, connector: Connector) {
    super.recv(message, connector) // TODO: recv$ (validate)
    println("[Log] >>  " + "GoAmateur does not understand string messages: \"" + message.getString + "\"")
    println("          " + "  -> connector index: " + connector.getIndex)
  }
  
  /**
    * Signifies the arrival of a message at the given connector in the device.
    *
    * @param message   the binary message being received
    * @param connector the connector at which the message arrived
    * @throws NullPointerException     if either argument is null
    * @throws IllegalArgumentException if the connector does not belong to this device
    */
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  override def recv(message: BinaryMessage, connector: Connector) {
    super.recv(message, connector)
    println("[Log] >>  " + "GoAmateur is not yet active: " + message.getValue)
  }
  
}


object GoAmateur {
  
  /**
    * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
    *
    * @param version the UXB version that this device supports
    */
  class Builder[T <: AbstractVideo.Builder[T]](override protected val version: Int)
    extends AbstractVideo.Builder[Builder[T]](version) {
  
    override protected def getThis = this
  
    /**
      * Initializes the GoAmateur with the builder’s version, product code, serial number, and connector list.
      *
      * @return the initialized GoAmateur
      * @throws IllegalStateException if the version number is null, or if one of the connectors is <i>not</i> of type peripheral
      */
    @throws[IllegalStateException]
    def build(): GoAmateur[T] = {
      validate()
      new GoAmateur[T](this)
    }
  
  }
  
}
