package edu.cwru.eecs293.ttf10.uxb

/**
  * <p> Represents a prototypical UXB peripheral.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * @since Programming Assignment 3
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Ted Frohlich < ttf10@case.edu >
  */
abstract class AbstractPeripheral[T <: AbstractPeripheral.Builder[T]]
(private val builder: AbstractPeripheral.Builder[T]) extends AbstractDevice(builder)


object AbstractPeripheral {
  
  /**
    * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
    *
    * @param version the UXB version that this device supports
    */
  abstract class Builder[T <: AbstractPeripheral.Builder[T]](override protected val version: Int)
    extends AbstractDevice.Builder[Builder[T]](version) {
  
    override def productCode(productCode: Int): T = super.productCode(productCode).asInstanceOf[T]
  
    override def serialNumber(serialNumber: BigInt): T = super.serialNumber(serialNumber).asInstanceOf[T]
  
    override def connectors(connectors: List[Connector.Type]): T = super.connectors(connectors).asInstanceOf[T]
  
    /**
      * Validates this builder.
      *
      * @throws NullPointerException  if and only if the version number is null
      * @throws IllegalStateException if and only if the version number is null, or if one of the connectors is <i>not</i> of type peripheral
      */
    @throws[NullPointerException]
    @throws[IllegalStateException]
    override protected def validate() {
      // Check the version
      super.validate()
      
      // Check that all connectors are of type peripheral
      if (getConnectors.exists(_ != Connector.Type.PERIPHERAL)) {
        throw new IllegalStateException("Validation failed: all connectors must be of type peripheral.")
      }
    }
    
  }
  
}
