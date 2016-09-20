package edu.cwru.eecs293.ttf10.uxb

/**
  * Represents a prototypical UXB printer.
  *
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 3  |  Due at beginning of discussion on Wednesday, September 21, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/courses/eecs293_vxl11/Hw3%283%29.pdf Hw3.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
abstract class AbstractPrinter[T <: AbstractPrinter.Builder[T]]
  extends AbstractPeripheral[AbstractPrinter.Builder[T]] {

  /**
    * Initializes the abstract printer from the given builder.
    * @param builder a builder for initializing the abstract printer
    */
  protected def this(builder: AbstractPrinter.Builder[T]) {
    this
    productCode = builder.getProductCode
    serialNumber = builder.getSerialNumber
    version = builder.getVersion
    val connectorTypes = builder.getConnectors
    connectors = List.empty
    for (index <- connectorTypes.indices) {
      connectors ::= new Connector(this, index, connectorTypes(index))  //TODO ::= might not work... consider :::
    }
  }  //TODO supposed to invoke parent constructor... why can't I??

  override def getDeviceClass: DeviceClass.DeviceClass = DeviceClass.PRINTER

}


object AbstractPrinter {

  abstract class Builder[T] extends AbstractPeripheral.Builder[Builder[T]] {

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

  }


}
