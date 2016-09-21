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
    // TODO: set up devices and messages
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
