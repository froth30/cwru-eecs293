package edu.cwru.eecs293.ttf10.uxb

/**
  * Represents one of multiple <i>connectors</i> belonging to a UXB device. Each connector is a physical plug that enables the user to insert a cable for connecting the device to a computer or to a UXB hub. Connectors are of two types: computer-side and peripheral-side. A computer-side connector is an outlet that is installed in a computer and a peripheral-side connector is a plug that is installed in a peripheral device. A UXB cable always runs from a peripheral to a computer but never from a computer to a computer or from a peripheral to a peripheral. A computer can have multiple computer-side connectors so that multiple peripherals can be added. A peripheral can have multiple peripheral-side connectors so that the peripheral can be added to multiple computers. A UXB hub can have multiple computer-side and peripheral-side connectors.
  *
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see <a href="Hw2.pdf">https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf</a>
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
object Connector {

  final case class Type() extends Enumeration {
    type Type = Value
    val COMPUTER, PERIPHERAL = Value
  }

  final class Connector(private val device: Device,
                        private val index: Int,
                        private val `type`: Type) {

    private val peer: Option[Connector] = Option.empty

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
    def getType: Type = `type`

    /**
      * Returns the peer of this connector.
      *
      * @return the other connector if any to which this connector is plugged
      */
    def getPeer: Option[Connector] = peer

  }

}
