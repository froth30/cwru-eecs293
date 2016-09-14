package edu.cwru.eecs293.ttf10.uxb

import edu.cwru.eecs293.ttf10.uxb.DeviceClass.DeviceClass

/**
  * Represents a UXB hub, which can have multiple computer-side and peripheral-side connectors.
  *
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see <a href="Hw2.pdf">https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf</a>
  * @author Theodore Frohlich <ttf10@case.edu>
  */
object Hub {

  class Builder(val version: Integer)

  /**
    * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
    * @param version the UXB version that this device supports
    */
    extends AbstractDevice.Builder[Hub.Builder](version) {
    /**
      * Initializes the hub with the builder’s version, product code, serial number, and connector list.
      * @return the initialized hub
      * @throws IllegalStateException if the version is null, or the hub has no computer connector, or the hub has no peripheral connector.
      */
    @throws[IllegalStateException]
    def build: Hub = {
      validate
      new Hub(this)
    }

    /**
      * Returns this builder.
      * @return this builder
      */
    protected def getThis: Hub.Builder = this

    /**
      *
      * @throws IllegalStateException if and only if the version number is null, or if the hub has no computer connector, or if the hub has no peripheral connector
      */
    @throws[IllegalStateException]
    override protected def validate {
      // Check if the version number is null
      try {
        super.validate
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

class Hub private(val builder: Hub.Builder)

/**
  * Initializes the hub from the given builder.
  * @param builder a builder for initializing the hub
  */
  extends AbstractDevice[Hub.Builder](builder) {
  override def getDeviceClass: DeviceClass = DeviceClass.HUB
}
