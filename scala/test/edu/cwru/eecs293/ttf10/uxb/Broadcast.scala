package edu.cwru.eecs293.ttf10.uxb

import org.junit.Assert._
import org.junit.{Before, Test}

/** <p>
  * A test that, given a <tt>List[Device]</tt> and a <tt>List[Message]</tt>, delivers all messages to all devices on their zero connector (if any). The device list should contain at least one device of each type, and the message list should contain at least one binary and at least one
string message.
  *
  * @since Programming Assignment 2
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Ted Frohlich < ttf10@case.edu >
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
        .build(),
      new SisterPrinter.Builder(2016)
        .serialNumber(13579)
        .productCode(293)
        .connectors(cons)
        .build(),
      new CannonPrinter.Builder(2016)
        .serialNumber(24680)
        .connectors(cons)
        .build(),
      new GoAmateur.Builder(2016)
        .connectors(cons)
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
        device.getConnector(0).recv(message)
      }
    }
  }

}
