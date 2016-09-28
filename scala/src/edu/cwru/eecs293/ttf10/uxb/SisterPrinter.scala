package edu.cwru.eecs293.ttf10.uxb

/**
  * <p> Represents a concrete UXB printer.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * @since Programming Assignment 3
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Theodore Frohlich &lt;ttf10@case.edu&gt;
  */
class SisterPrinter protected
(override protected val productCode: Option[Int],
 override protected val serialNumber: Option[BigInt],
 override protected val version: Int,
 override protected val connectors: List[Connector]
) extends AbstractPrinter[SisterPrinter.Builder](productCode, serialNumber, version, connectors) {

  /**
    * Initializes the sister printer from the given builder.
    *
    * @param builder a builder for initializing the sister printer
    */
  protected def apply(builder: SisterPrinter.Builder): SisterPrinter = this(builder)

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
    super.recv(message, connector)
    println("[Log] >>  " + "Sister printer has printed the string: \"" + message.getString + "\"")
    println("          " + "  -> printer serial number: " + serialNumber.get)
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
    val result: BigInt = message.getValue +
      (if (productCode.isDefined) productCode.get else 0) // TODO: simplify by using Option.getOrElse
    println("[Log] >>  " + "Sister printer has printed the binary message: " + result)
  }

}


object SisterPrinter {
  
  private def apply(builder: Builder): SisterPrinter = this(builder)

  class Builder extends AbstractPrinter.Builder[Builder] {

    /**
      * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
      *
      * @param version the UXB version that this device supports
      */
    override def apply(version: Int): Builder = this(version)

    /**
      * Initializes the sister printer with the builder’s version, product code, serial number, and connector list.
      *
      * @return the initialized sister printer
      * @throws IllegalStateException if the version number is null, or if one of the connectors is <i>not</i> of type peripheral
      */
    @throws[IllegalStateException]
    def build(): SisterPrinter = {
      validate()
      SisterPrinter(this)
    }

  }

}
