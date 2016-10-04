package edu.cwru.eecs293.ttf10.uxb

import DeviceClass._

/**
  * <p> Represents a UXB device, which is a UXB-enabled computer, peripheral, or hub.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * <br> [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4287477_1/xid-4287477_1 Hw2.pdf]]
  * @since Programming Assignment 2
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Theodore Frohlich &lt;ttf10@case.edu&gt;
  */
trait Device {
  
  /**
    * Returns the product code of this device.
    *
    * @return the product code of this device. If the product code is unknown, return an empty optional.
    */
  def getProductCode: Option[Int]
  
  /**
    * Returns the serial number of this device.
    *
    * @return the serial number of this device. If the serial number is unknown, return an empty optional.
    */
  def getSerialNumber: Option[BigInt]
  
  /**
    * Returns the UXB version that this device supports.
    *
    * @return the UXB version that this device supports
    */
  def getVersion: Int
  
  /**
    * Returns the class of this UXB device.
    *
    * @return the class of this UXB device
    */
  def getDeviceClass: DeviceClass
  
  /**
    * Returns the number of connectors that this device has.
    *
    * @return the number of connectors that this device has
    */
  def getConnectorCount: Int
  
  /**
    * Returns the type of each connector in this device.
    *
    * @return the type of each connector in this device
    */
  def getConnectors: List[Connector]
  
  /**
    * Returns the connector of this device at the given index.
    *
    * @param index the plug number of the connector in this device
    * @return the connector of this device at the given index
    */
  def getConnector(index: Int): Connector
  
  /**
    * Returns the devices to which this device is connected directly through one of its connectors.
    *
    * @return a set of peer devices
    */
  def peerDevices: Set[Device]
  
  /**
    * Finds a set of all devices reachable from this device.
    *
    * @return all devices that are reachable either directly (the <tt>peerDevices</tt>) or indirectly from this device
    */
  def reachableDevices: Set[Device]
  
  /**
    * Determines whether the given device is reachable from this device.
    *
    * @param device the device in question
    * @return <tt>true</tt> if the argument is connected directly or indirectly to this device, <tt>false</tt> otherwise
    */
  def isReachable(device: Device): Boolean
  
  /**
    * Signifies the arrival of a message at the given connector in the device.
    *
    * @param message   the string message being received
    * @param connector the connector at which the message arrived
    * @throws NullPointerException     if either argument is null
    * @throws IllegalArgumentException if the connector does not belong to this device
    */
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: StringMessage, connector: Connector)
  
  /**
    * Signifies the arrival of a message at the given connector in the device.
    *
    * @param message   the binary message being received
    * @param connector the connector at which the message arrived
    * @throws NullPointerException     if either argument is null
    * @throws IllegalArgumentException if the connector does not belong to this device
    */
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: BinaryMessage, connector: Connector)
  
}
