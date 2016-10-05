package edu.cwru.eecs293.ttf10.uxb

import edu.cwru.eecs293.ttf10.uxb.DeviceClass._

import scala.collection.mutable

/**
  * Represents a prototypical UXB device.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * <br> [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4287477_1/xid-4287477_1 Hw2.pdf]]
  * @since Programming Assignment 2
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Ted Frohlich < ttf10@case.edu >
  */
abstract class AbstractDevice[T <: AbstractDevice.Builder[T]](private val builder: AbstractDevice.Builder[T])
  extends Device {
  
  protected val productCode: Option[Int] = builder.getProductCode
  protected val serialNumber: Option[BigInt] = builder.getSerialNumber
  protected val version: Int = builder.getVersion
  protected val connectors: List[Connector] = {
    val connectorTypes = builder.getConnectors
    var connectors: List[Connector] = List.empty
    for (index <- connectorTypes.indices) {
      connectors ::= new Connector(this, index, connectorTypes(index))
    }
    connectors.reverse
  }
  
  def getProductCode: Option[Int] = productCode
  
  def getSerialNumber: Option[BigInt] = serialNumber
  
  def getVersion: Int = version
  
  def getDeviceClass: DeviceClass = DeviceClass.UNSPECIFIED
  
  def getConnectorCount: Int = connectors.size
  
  def getConnectors: List[Connector] = connectors
  
  def getConnector(index: Int): Connector = connectors(index)
  
  def peerDevices: Set[Device] = {
    connectors.map(connector => connector.getDevice).toSet
  }
  
  def reachableDevices: Set[Device] = {
    val queue: mutable.Queue[Device] = mutable.Queue(this)
    val traversed: mutable.Set[Device] = mutable.Set(this)
    acquireTargets(queue, traversed)
  }
  
  def isReachable(device: Device): Boolean = search(this, device)
  
  protected def search(origin: Device, target: Device): Boolean = {
    val queue: mutable.Queue[Device] = mutable.Queue(origin)
    val traversed: mutable.Set[Device] = mutable.Set(origin)
    targetAcquired(queue, traversed, target)
  }
  
  protected def acquireTargets(queue: mutable.Queue[Device], traversed: mutable.Set[Device]): Set[Device] = {
    while (queue.nonEmpty) {
      val node = queue.dequeue()
      enqueuePeers(queue, traversed, node)
    }
    traversed.toSet
  }
  
  protected def targetAcquired(queue: mutable.Queue[Device], traversed: mutable.Set[Device], target: Device): Boolean = {
    while (queue.nonEmpty) {
      val node = queue.dequeue()
      if (node == target) return true
      enqueuePeers(queue, traversed, node)
    }
    false
  }
  
  protected def enqueuePeers(queue: mutable.Queue[Device], traversed: mutable.Set[Device], node: Device) {
    node.peerDevices
      .diff(traversed)
      .foreach(peer => {
        traversed += peer
        queue += peer
      })
  }
  
  /**
    * Signifies the arrival of a message at the given connector in the device.
    *
    * @param message   the message being received
    * @param connector the connector at which the message arrived
    * @throws NullPointerException     if either argument is null
    * @throws IllegalArgumentException if the connector does not belong to this device
    */
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  protected def validate_recv(message: Message, connector: Connector) {
    if (message == null || connector == null) {
      throw new NullPointerException("Message not received: null argument.")
    } else if (connector.getDevice != this) {
      throw new IllegalArgumentException("Message not received: connector does not belong to this device.")
    }
  }
  
}


object AbstractDevice {
  
  /**
    * Creates a new builder with the given UXB version, no connectors, empty product code, and empty serial number.
    *
    * @param version the UXB version that this device supports
    */
  abstract class Builder[T <: AbstractDevice.Builder[T]](protected val version: Int) {
    
    protected var productCode: Option[Int] = Option.empty
    protected var serialNumber: Option[BigInt] = Option.empty
    protected var connectors: List[Connector.Type] = List.empty
    
    def getProductCode: Option[Int] = productCode
    
    def getSerialNumber: Option[BigInt] = serialNumber
    
    def getVersion: Int = version
    
    def getConnectors: List[Connector.Type] = connectors
  
    protected def getThis: T
    
    /**
      * Sets the product code to the given value. If the <tt>productCode</tt> is null, set it to an empty optional.
      *
      * @param productCode the product code of this device
      * @return [[getThis]]
      */
    def productCode(productCode: Int): T = {
      this.productCode = // Must perform null check since Int is not a nullable type
        if (productCode != null.asInstanceOf[Int]) Option(productCode) else Option.empty
      getThis
    }
    
    /**
      * Sets the serial number to the given value. If the serial number is null, set it to an empty optional.
      *
      * @param serialNumber the serial number of this device
      * @return [[getThis()]]
      */
    def serialNumber(serialNumber: BigInt): T = {
      this.serialNumber = Option(serialNumber) // BigInt is a nullable type (see counterexample above)
      getThis
    }
    
    /**
      * Sets the connector types to a copy of the given value. If the argument is null, the device will have no connectors.
      *
      * @param connectors the type of each connector in this device
      * @return [[getThis()]]
      */
    def connectors(connectors: List[Connector.Type]): T = {
      this.connectors = if (connectors != null) connectors else List.empty
      getThis
    }
    
    /**
      * Validates this builder.
      *
      * @throws NullPointerException if and only if the version number is null
      */
    @throws[NullPointerException]
    protected def validate() {
      if (version == null.asInstanceOf[Int]) {
        throw new NullPointerException("Validation failed: version number is null.")
      }
    }
    
  }
  
}
