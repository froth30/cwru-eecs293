package edu.cwru.eecs293.ttf10.uxb

/** <p>
  * Represents a concrete UXB video peripheral device. This just might be better than the [[http://www.ufunk.net/en/gadgets/goamateur/ GoAmateur]]!
  * <p>
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 3  |  Due at beginning of discussion on Wednesday, September 21, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/courses/eecs293_vxl11/Hw3%283%29.pdf Hw3.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
class GoAmateur extends AbstractVideo[GoAmateur.Builder] {

  /**
    * Initializes the GoAmateur from the given builder.
    * @param builder a builder for initializing the GoAmateur
    */
  protected def this(builder: GoAmateur.Builder) {
    this
    productCode = builder.getProductCode
    serialNumber = builder.getSerialNumber
    version = builder.getVersion
    val connectorTypes = builder.getConnectors
    connectors = List.empty
    for (index <- connectorTypes.indices) {
      connectors ::= new Connector(this, index, connectorTypes(index))  //TODO ::= might not work... consider :::
    }
  } //TODO supposed to invoke parent constructor... why can't I??

  /**
    * Signifies the arrival of a message at the given connector in the device.
    * @param message the string message being received
    * @param connector the connector at which the message arrived
    */
  override def recv(message: StringMessage, connector: Connector) {
    println("[Log] >>  " + "GoAmateur does not understand string messages: " + message.getString)
    println("          " + "  -> connector index: " + connector.getIndex)
  }

  /**
    * Signifies the arrival of a message at the given connector in the device.
    * @param message the binary message being received
    * @param connector the connector at which the message arrived
    */
  override def recv(message: BinaryMessage, connector: Connector) {
    println("[Log] >>  " + "GoAmateur is not yet active: " + message.getValue)
  }

}


object GoAmateur {

  class Builder extends AbstractVideo.Builder[Builder] {

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
    } //TODO supposed to invoke parent method... why can't I?

    /**
      * Initializes the GoAmateur with the builder’s version, product code, serial number, and connector list.
      * @return the initialized GoAmateur
      * @throws IllegalStateException if the version number is null, or if one of the connectors is <i>not</i> of type peripheral
      */
    @throws[IllegalStateException]
    def build(): GoAmateur = {
      validate()
      new GoAmateur(this)
    }

    /**
      * Returns this builder.
      * @return this builder
      */
    override protected def getThis: Builder = this

  }


}
