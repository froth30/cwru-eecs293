package edu.cwru.eecs293.ttf10.uxb

/**
  * <p> Represents one of multiple <i>connectors</i> belonging to a UXB device.
  * <p> Each connector is a physical plug that enables the user to insert a cable for connecting the device to a computer or to a UXB hub. Connectors are of two types: <i>computer-side</i> and <i>peripheral-side</i>. A computer-side connector is an outlet that is installed in a computer and a peripheral-side connector is a plug that is installed in a peripheral device. A UXB <i>cable</i> always runs from a peripheral to a computer but never from a computer to a computer or from a peripheral to a peripheral. A computer can have multiple computer-side connectors so that multiple peripherals can be added. A peripheral can have multiple peripheral-side connectors so that the peripheral can be added to multiple computers. A UXB hub can have multiple computer-side and peripheral-side connectors.
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
final class Connector(private val device: Device,
                      private val index: Int,
                      private val `type`: Connector.Type) {
  
  private var peer: Option[Connector] = Option.empty
  
  /**
    * Returns the device to which this connector belongs.
    *
    * @return the device to which this connector belongs
    */
  def getDevice: Device = device
  
  /**
    * Returns the index of this connector.
    *
    * @return the plug number in the connector's device
    */
  def getIndex: Int = index
  
  /**
    * Returns the type of this connector.
    *
    * @return the type of this connector
    */
  def getType: Connector.Type = `type`
  
  /**
    * Returns the peer of this connector.
    *
    * @return the other connector if any to which this connector is plugged
    */
  def getPeer: Option[Connector] = peer
  
  @throws[ConnectionException]
  @throws[NullPointerException]
  def setPeer(peer: Connector) {
    if (peer == null) {
      throw new NullPointerException
    }
    this.peer = Option(peer)
  }
  
  /**
    * Makes sure that the message reaches the connector's device.
    *
    * @param message the message reaching the device
    * @throws NullPointerException     if either the message or connector's device is null
    * @throws IllegalArgumentException if the connector does not belong to the device
    */
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: Message) {
    message.reach(device, this)
  }
  
}


object Connector {
  
  case object Type extends Enumeration {
    val COMPUTER, PERIPHERAL = Value
  }
  type Type = Type.Value  /*
  TODO[DIS] => Although not the conventional way to implement an enumeration in Scala, approaches like this are suggested in Scala Cookbook 10.26  ((keep this if okay with Andrew))
  */
  
}
