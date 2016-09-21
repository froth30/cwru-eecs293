package edu.cwru.eecs293.ttf10.uxb

/** <p>
  * Represents a UXB hub, which can have multiple computer-side and peripheral-side connectors.
  * <p>
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf Hw2.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
class Hub extends AbstractDevice[Hub.Builder] {

  /**
    * Initializes the hub from the given builder.
    * @param builder a builder for initializing the hub
    */
  private[Hub] def this(builder: Hub.Builder) {
    this
    productCode = builder.getProductCode
    serialNumber = builder.getSerialNumber
    version = builder.getVersion
    val connectorTypes = builder.getConnectors
    connectors = List.empty
    for (index <- connectorTypes.indices) {
      connectors ::= new Connector(this, index, connectorTypes(index))  // TODO ::= might not work... consider :::
    }
  }

  override def getDeviceClass: DeviceClass.DeviceClass = DeviceClass.HUB

  /**
    * Signifies the arrival of a message at the given connector in the device.
    * @param message the string message being received
    * @param connector the connector at which the message arrived
    */
  override def recv(message: StringMessage, connector: Connector) {
    println("[Log] >>  " + "recv not yet supported")
  }

}


object Hub {

  class Builder extends AbstractDevice.Builder[Builder] {

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
    }

    /**
      * Initializes the hub with the builder’s version, product code, serial number, and connector list.
      * @return the initialized hub
      * @throws IllegalStateException if the version is null, or the hub has no computer connector, or the hub has no peripheral connector.
      */
    @throws[IllegalStateException]
    def build(): Hub = {
      validate()
      new Hub(this)
    }

    /**
      * Returns this builder.
      * @return this builder
      */
    override protected def getThis: Builder = this

    /**
      * TODO
      * @throws IllegalStateException if and only if the version number is null, or if the hub has no computer connector, or if the hub has no peripheral connector
      */
    @throws[IllegalStateException]
    override protected def validate() {
      // Check if the version number is null
      try {
        super.validate()
      } catch {
        case e: NullPointerException => throw new IllegalStateException(e)
      }
      // Check if the hub has no computer connector
      if (!getConnectors.contains(Connector.Type.COMPUTER)) {
        throw new IllegalStateException("Validation failed: hub has no computer connector.")
      }
      // Check if the hub has no peripheral connector
      if (!getConnectors.contains(Connector.Type.PERIPHERAL)) {
        throw new IllegalStateException("Validation failed: hub has no peripheral connector.")
      }
    }

  }


}
