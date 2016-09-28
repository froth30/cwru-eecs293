package edu.cwru.eecs293.ttf10.uxb

import org.junit.Assert._
import org.junit.{Before, Test}

/** <p>
  * A test that, given a <tt>List[Device]</tt> and a <tt>List[Message]</tt>, delivers all messages to all devices on their zero connector (if any). The device list should contain at least one device of each type, and the message list should contain at least one binary and at least one
string message.
  * <p>
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf Hw2.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
class Broadcast {

  private var devices: List[Device] = _
  private var messages: List[Message] = _

  @Before
  def setUp() {
    // Create peripheral connector doubleton list to add to each device
    val cons = List(Connector.Type.PERIPHERAL, Connector.Type.PERIPHERAL)

    // Initialize device list
    devices = List(
      new Hub.Builder(2016)
        .connectors(Connector.Type.COMPUTER :: cons)
        .asInstanceOf[Hub.Builder]
        .build(),
      new SisterPrinter.Builder(2016)
        .serialNumber(13579)
        .asInstanceOf[SisterPrinter.Builder]
        .productCode(293)
        .asInstanceOf[SisterPrinter.Builder]
        .connectors(cons)
        .asInstanceOf[SisterPrinter.Builder]
        .build(),
      new CannonPrinter.Builder(2016)
        .serialNumber(24680)
        .asInstanceOf[CannonPrinter.Builder]
        .connectors(cons)
        .asInstanceOf[CannonPrinter.Builder]
        .build(),
      new GoAmateur.Builder(2016)
        .connectors(cons)
        .asInstanceOf[GoAmateur.Builder]
        .build()
    )

    // Create message list
    messages = List(
      new StringMessage("Hello, world!"),
      new StringMessage("My name is Ted."),
      new StringMessage(""),
      new BinaryMessage(0),
      new BinaryMessage(1)
    )
  }

  @Test
  @throws[Exception]
  def broadcast() {
    for (device <- devices) {
      for (message <- messages) {
        message.reach(device, device.getConnector(0))
      }
    }
  }

}
