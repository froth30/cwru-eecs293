package edu.cwru.eecs293.ttf10.uxb

/**
  * <p> Represents a prototypical UXB printer.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * @since Programming Assignment 3
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Theodore Frohlich &lt;ttf10@case.edu&gt;
  */
abstract class AbstractPrinter[T <: AbstractPrinter.Builder[T]] protected
(override protected val productCode: Option[Int],
 override protected val serialNumber: Option[BigInt],
 override protected val version: Int,
 override protected val connectors: List[Connector]
) extends AbstractPeripheral[T](productCode, serialNumber, version, connectors) {
  
  /**
    * Initializes the abstract printer from the given builder.
    *
    * @param builder a builder for initializing the abstract printer
    */
  protected def this(builder: AbstractPrinter.Builder[T]) {
    this(builder)
  }
  
  override def getDeviceClass: DeviceClass.DeviceClass = DeviceClass.PRINTER
  
}


object AbstractPrinter {
  
  abstract class Builder[T] extends AbstractPeripheral.Builder[Builder[T]]
  
}
