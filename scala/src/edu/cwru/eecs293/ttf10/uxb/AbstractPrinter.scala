package edu.cwru.eecs293.ttf10.uxb

import DeviceClass._

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
abstract class AbstractPrinter[T <: AbstractPrinter.Builder[T]]
(private val builder: AbstractPrinter.Builder[T]) extends AbstractPeripheral(builder) {
  
  override def getDeviceClass: DeviceClass = DeviceClass.PRINTER
  
}


object AbstractPrinter {
  
  abstract class Builder[+T <: AbstractPrinter.Builder[T]]
  (override protected val version: Int) extends AbstractPeripheral.Builder(version)
  
}
