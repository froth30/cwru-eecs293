package edu.cwru.eecs293.ttf10.uxb

/**
  * Represents a prototypical UXB peripheral.
  *
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 3  |  Due at beginning of discussion on Wednesday, September 21, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/courses/eecs293_vxl11/Hw3%283%29.pdf Hw3.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
abstract class AbstractPeripheral[T <: AbstractPeripheral.Builder]
  extends AbstractDevice[AbstractPeripheral.Builder] {

  /**
    * Initializes the abstract peripheral from the given builder.
    * @param builder a builder for initializing the abstract peripheral
    */
  protected def this(builder: AbstractPeripheral.Builder) {
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

}


object AbstractPeripheral {

  abstract class Builder extends AbstractDevice.Builder[Builder] {

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
      * TODO
      * @throws IllegalStateException if and only if the version number is null, or if one of the connectors is <i>not</i> of type peripheral
      */
    @throws[IllegalStateException]
    override protected def validate() {
      // Check if the version number is null
      try {
        super.validate()
      } catch {
        case e: NullPointerException => throw new IllegalStateException(e)
      }
      // Check if one of the connectors is _not_ of type peripheral
      if (getConnectors.exists(_ /*p => p*/ != Connector.Type.PERIPHERAL))  //TODO see in line
        throw new IllegalStateException("Validation failed: all connectors must be of type peripheral.")
    }

  }


}
