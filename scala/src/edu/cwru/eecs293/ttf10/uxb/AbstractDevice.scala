package edu.cwru.eecs293.ttf10.uxb

/**
  * Represents a prototypical UXB device.
  *
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf Hw2.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
abstract class AbstractDevice[T <: AbstractDevice[T]#Builder[T]] extends Device {

  private final var productCode: Option[Int] = _
  private final var serialNumber: Option[BigInt] = _
  private final var version: Int = _
  private final var connectors: List[Connector.Type] = _  // TODO look into Map(Connector->Connector.Type)

  /**
    * Initializes an abstract device from the given builder.
    *
    * @param builder a builder for initializing an abstract device
    */
  protected def this(builder: Builder[T]) {
    this()
    productCode = builder.productCode
    serialNumber = builder.serialNumber
    version = builder.version
    connectors = builder.connectors
  }

  override def getProductCode: Option[Int] = productCode

  override def getSerialNumber: Option[BigInt] = serialNumber

  override def getVersion: Int = version

  override def getDeviceClass: DeviceClass = DeviceClass.UNSPECIFIED

  override def getConnectorCount: Int = connectors.size

  override def getConnectors: List[Connector.Type] = connectors

  override def getConnector(index: Int): Connector = new Connector(this, index, connectors(index))  // TODO avoid instantiating new Connector on invocation?



  abstract class Builder[T] {

    protected[AbstractDevice] var version: Int = _
    protected[AbstractDevice] var productCode: Option[Int] = _
    protected[AbstractDevice] var serialNumber: Option[BigInt] = _
    protected[AbstractDevice] var connectors: List[Connector.Type] = _

    /**
      * Creates a new builder with the given UXB version, no connectors, empty product code, and empty serial number.
      *
      * @param version the UXB version that this device supports
      */
    def this(version: Int) {
      this()
      this.version = version
      productCode = None
      serialNumber = None
      connectors = Nil
    }

    /**
      * Sets the product code to the given value. If the <tt>productCode</tt> is null, set it to an empty optional.
      *
      * @param productCode the product code of this device
      * @return [[getThis]]
      */
    def productCode(productCode: Integer): T = {
      this.productCode = Option(productCode)
      getThis
    }

    /**
      * Sets the serial number to the given value. If the serial number is null, set it to an empty optional.
      *
      * @param serialNumber the serial number of this device
      * @return [[getThis()]]
      */
    def serialNumber(serialNumber: BigInt): T = {
      this.serialNumber = Option(serialNumber)
      getThis
    }

    /**
      * Sets the connector types to a copy of the given value. If the argument is null, the device will have no connectors.
      *
      * @param connectors the type of each connector in this device
      * @return [[getThis()]]
      */
    def connectors(connectors: List[Connector.Type]): T = {
      this.connectors = if (connectors != null) connectors else Nil
      getThis
    }

    protected[AbstractDevice] def getThis: T

    /**
      * Returns a copy of the connector types.
      *
      * @return a copy of the connector types
      */
    protected[AbstractDevice] def getConnectors: List[Connector.Type] = connectors

    /**
      * Validates this builder.
      *
      * @throws NullPointerException if and only if the version number is null
      */
    @throws[NullPointerException]
    protected[AbstractDevice] def validate() {
      if (version == null.asInstanceOf[Int]) {
        throw new NullPointerException("Validation failed: version number is null.")
      }
    }

  }


}
