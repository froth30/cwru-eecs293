package edu.cwru.eecs293.ttf10.uxb

/**
  * <p> Represents a concrete UXB printer.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * @since Programming Assignment 3
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Ted Frohlich < ttf10@case.edu >
  */
class SisterPrinter[T <: AbstractPrinter.Builder[T]](private val builder: SisterPrinter.Builder[T])
  extends AbstractPrinter(builder) {
  
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: StringMessage, connector: Connector) {
    validateRecv(message, connector)
    println("[Log] >>  " + "Sister printer has printed the string: \"" + message.getString + "\"")
    println("          " + "  -> printer serial number: " + serialNumber.get)
  }
  
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: BinaryMessage, connector: Connector) {
    validateRecv(message, connector)
    val result: BigInt = message.getValue + productCode.getOrElse[Int](0)
    println("[Log] >>  " + "Sister printer has printed the binary message: " + result)
  }
  
}


object SisterPrinter {
  
  /**
    * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
    *
    * @param version the UXB version that this device supports
    */
  class Builder[T <: AbstractPrinter.Builder[T]](override protected val version: Int)
    extends AbstractPrinter.Builder[Builder[T]](version) {
  
    protected def getThis = this
  
    /**
      * Initializes the sister printer with the builder’s version, product code, serial number, and connector list.
      *
      * @return the initialized sister printer
      * @throws IllegalStateException if the version number is null, or if one of the connectors is <i>not</i> of type peripheral
      */
    @throws[IllegalStateException]
    def build(): SisterPrinter[T] = {
      validate()
      new SisterPrinter(this)
    }
  
  }
  
}
