package edu.cwru.eecs293.ttf10.uxb

/**
  * <p> Represents a concrete UXB cannon printer (with two n's!)
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * @since Programming Assignment 3
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Ted Frohlich < ttf10@case.edu >
  */
class CannonPrinter[T <: AbstractPrinter.Builder[T]](private val builder: CannonPrinter.Builder[T])
  extends AbstractPrinter(builder) {
  
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
    validate_recv(message, connector)
    println("[Log] >>  " + "Cannon printer has printed the string: \"" + message.getString + "\"")
    println("          " + "  -> UXB version number: " + version)
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
    validate_recv(message, connector)
    val result: BigInt = message.getValue * serialNumber.getOrElse(1)
    println("[Log] >>  " + "Cannon printer has printed the binary message: " + result)
  }
  
}


object CannonPrinter {
  
  /**
    * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
    *
    * @param version the UXB version that this device supports
    */
  class Builder[T <: AbstractPrinter.Builder[T]](override protected val version: Int)
    extends AbstractPrinter.Builder[Builder[T]](version) {
  
    override protected def getThis = this
  
    /**
      * Initializes the cannon printer with the builder’s version, product code, serial number, and connector list.
      *
      * @return the initialized cannon printer
      * @throws IllegalStateException if the version number is null, or if one of the connectors is <i>not</i> of type peripheral
      */
    @throws[IllegalStateException]
    def build(): CannonPrinter[T] = {
      validate()
      new CannonPrinter(this)
    }
  
  }
  
}
