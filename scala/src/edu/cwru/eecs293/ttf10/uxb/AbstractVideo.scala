package edu.cwru.eecs293.ttf10.uxb

import DeviceClass._

/**
  * <p> Represents a prototypical UXB video peripheral device.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * @since Programming Assignment 3
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Theodore Frohlich &lt;ttf10@case.edu&gt;
  */
abstract class AbstractVideo[T <: AbstractVideo.Builder[T]](private val builder: AbstractVideo.Builder[T])
  extends AbstractPeripheral(builder) {
  
  override def getDeviceClass: DeviceClass = DeviceClass.VIDEO
  
}


object AbstractVideo {
  
  abstract class Builder[T <: AbstractVideo.Builder[T]](override protected val version: Int)
    extends AbstractPeripheral.Builder[Builder[T]](version) {
  
    override def productCode(productCode: Int): T = super.productCode(productCode).asInstanceOf[T]
  
    override def serialNumber(serialNumber: BigInt): T = super.serialNumber(serialNumber).asInstanceOf[T]
  
    override def connectors(connectors: List[Connector.Type]): T = super.connectors(connectors).asInstanceOf[T]
    
  }
  
}
