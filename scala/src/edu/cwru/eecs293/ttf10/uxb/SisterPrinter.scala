package edu.cwru.eecs293.ttf10.uxb

/** <p>
  * Represents a concrete UXB printer.
  * <p>
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 3  |  Due at beginning of discussion on Wednesday, September 21, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/courses/eecs293_vxl11/Hw3%283%29.pdf Hw3.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
class SisterPrinter extends AbstractPrinter[SisterPrinter.Builder] {

  /**
    * Initializes the sister printer from the given builder.
    * @param builder a builder for initializing the sister printer
    */
  protected def this(builder: SisterPrinter.Builder) {
    this
    productCode = builder.getProductCode
    serialNumber = builder.getSerialNumber
    version = builder.getVersion
    val connectorTypes = builder.getConnectors
    connectors = List.empty
    for (index <- connectorTypes.indices) {
      connectors ::= new Connector(this, index, connectorTypes(index))
    }
  }  // TODO: supposed to invoke parent constructor... why can't I??

  /**
    * Signifies the arrival of a message at the given connector in the device.
    * @param message the string message being received
    * @param connector the connector at which the message arrived
    * @throws NullPointerException if either argument is null
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
    * @param message the binary message being received
    * @param connector the connector at which the message arrived
    * @throws NullPointerException if either argument is null
    * @throws IllegalArgumentException if the connector does not belong to this device
    */
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  override def recv(message: BinaryMessage, connector: Connector) {
    super.recv(message, connector)
    val result: BigInt = message.getValue +
      (if (productCode.nonEmpty) productCode.get else 0)
    println("[Log] >>  " + "Sister printer has printed the binary message: " + result)
  }

}


object SisterPrinter {

  class Builder extends AbstractPrinter.Builder[Builder] {

    /**
      * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
      * @param version the UXB version that this device supports
      */
    def this(version: Int) {
      this
      this.version = version
      productCode(null.asInstanceOf[Int])
      serialNumber(null.asInstanceOf[BigInt])
      connectors(null)
    }  //TODO supposed to invoke parent method... why can't I?

    /**
      * Initializes the sister printer with the builder’s version, product code, serial number, and connector list.
      * @return the initialized sister printer
      * @throws IllegalStateException if the version number is null, or if one of the connectors is <i>not</i> of type peripheral
      */
    @throws[IllegalStateException]
    def build(): SisterPrinter = {
      validate()
      new SisterPrinter(this)
    }

    /**
      * Returns this builder.
      * @return this builder
      */
    override protected def getThis: Builder = this

  }


}
