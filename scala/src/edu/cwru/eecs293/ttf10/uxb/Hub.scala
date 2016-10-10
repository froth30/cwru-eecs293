/*                                                                      *\
**                    Case Western Reserve University                   **
**                                                                      **
**                               EECS 293                               **
**                        Software Craftsmanship                        **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.eecs293.ttf10.uxb

import DeviceClass._

/**
  * Represents a UXB hub, which can have multiple computer-side and peripheral-side connectors.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * <br> [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4287477_1/xid-4287477_1 Hw2.pdf]]
  * @since Programming Assignment 2
  * @author Ted Frohlich
  */
class Hub(private val builder: Hub.Builder) extends AbstractDevice(builder) {
  
  override def getDeviceClass: DeviceClass = DeviceClass.HUB
  
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: StringMessage, connector: Connector) {
    validateRecv(message, connector)
    println("[Log] >>  " + "recv not yet supported")
  }
  
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: BinaryMessage, connector: Connector) {
    validateRecv(message, connector)
    println("[Log] >>  " + "recv not yet supported")
  }
  
}


object Hub {
  
  /**
    * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
    *
    * @param version the UXB version that this device supports
    */
  class Builder(override protected val version: Int) extends AbstractDevice.Builder[Builder](version) {
  
    protected def getThis = this
  
    /**
      * Initializes the hub with the builder’s version, product code, serial number, and connector list.
      *
      * @return the initialized hub
      * @throws IllegalStateException if the version is null, or the hub has no computer connector, or the hub has no peripheral connector.
      */
    @throws[IllegalStateException]
    def build(): Hub = {
      validate()
      new Hub(this)
    }
  
    /**
      * Validates this builder.
      *
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
