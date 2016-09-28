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
  * @author Theodore Frohlich &lt;ttf10@case.edu&gt;
  */
abstract class AbstractPeripheral[T <: AbstractPeripheral.Builder[T]]
(private val builder: AbstractPeripheral.Builder[T]) extends AbstractDevice(builder)


object AbstractPeripheral {
  
  /**
    * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
    *
    * @param version the UXB version that this device supports
    */
  abstract class Builder[+T <: AbstractPeripheral.Builder[T]]
  (override protected val version: Int) extends AbstractDevice.Builder(version) {
    
    /**
      * Validates this builder.
      *
      * @throws NullPointerException  if and only if the version number is null
      * @throws IllegalStateException if and only if the version number is null, or if one of the connectors is <i>not</i> of type peripheral
      */
    @throws[NullPointerException]
    @throws[IllegalStateException]
    override protected def validate() {
      // Check if the version number is null
      try {
        super.validate()
      } catch {
        case e: NullPointerException => throw new IllegalStateException(e)
      }
      // Check if one of the connectors is _not_ of type peripheral
      if (getConnectors.exists(_ != Connector.Type.PERIPHERAL)) {
        throw new IllegalStateException("Validation failed: all connectors must be of type peripheral.")
      }
    }
    
  }
  
  
}
