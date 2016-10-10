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
  * Represents a prototypical UXB printer.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * @since Programming Assignment 3
  * @author Ted Frohlich
  */
abstract class AbstractPrinter[T <: AbstractPrinter.Builder[T]](private val builder: AbstractPrinter.Builder[T])
  extends AbstractPeripheral(builder) {
  
  override def getDeviceClass: DeviceClass = DeviceClass.PRINTER
  
}


object AbstractPrinter {
  
  abstract class Builder[T <: AbstractPrinter.Builder[T]](override protected val version: Int)
    extends AbstractPeripheral.Builder[Builder[T]](version) {
  
    override def productCode(productCode: Int): T = super.productCode(productCode).asInstanceOf[T]
  
    override def serialNumber(serialNumber: BigInt): T = super.serialNumber(serialNumber).asInstanceOf[T]
  
    override def connectors(connectors: List[Connector.Type]): T = super.connectors(connectors).asInstanceOf[T]
    
  }
  
}
