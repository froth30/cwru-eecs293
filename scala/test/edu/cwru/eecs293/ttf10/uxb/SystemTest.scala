/*                                                                      *\
**                    Case Western Reserve University                   **
**                                                                      **
**                               EECS 293                               **
**                        Software Craftsmanship                        **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.eecs293.ttf10.uxb

import org.junit.{Before, Test}

/**
  * This test creates a UXB system containing two hubs, three printers (not all of the same type), and a Webcam. These UXB devices will all be connected with each other, directly or indirectly. Then, the following scenarios will be explored:
  * <ul>
  *   <li>A string message is broadcast from a hub.
  *   <li>A binary message is sent from a hub along a connector that links the hub to a Webcam.
  *   <li>A binary message is broadcast from a hub.
  * </ul>
  * @since Programming Assignment 5
  * @author Ted Frohlich
  */
class SystemTest {
  
  private var hub1, hub2 : Hub = _
  private var printer1, printer2 : SisterPrinter = _
  private var printer3 : CannonPrinter = _
  private var webcam: GoAmateur = _
  
  @Before
  def setUp() {
    webcam = new GoAmateur.Builder(2)
      .connectors(List(
        Connector.Type.PERIPHERAL)) // to hub1
      .build()
    hub1 = new Hub.Builder(1)
      .connectors(List(
        Connector.Type.COMPUTER,    // to webcam
        Connector.Type.COMPUTER,    // to printer3
        Connector.Type.COMPUTER,    // to hub2
        Connector.Type.PERIPHERAL)) // hub needs both types of connectors
      .build()
    printer3 = new CannonPrinter.Builder(2)
      .connectors(List(
        Connector.Type.PERIPHERAL)) // to hub1
      .build()
    hub2 = new Hub.Builder(2)
      .connectors(List(
        Connector.Type.PERIPHERAL,  // to hub1
        Connector.Type.COMPUTER,    // to printer1
        Connector.Type.COMPUTER))   // to printer2
      .build()
    val sisterPrinterBuilder = new SisterPrinter.Builder(3)
      .connectors(List(
        Connector.Type.PERIPHERAL)) // to hub2
    printer1 = sisterPrinterBuilder.build()
    printer2 = sisterPrinterBuilder.build()
  }
  
  /**
    * Tests the scenario in which a string message is broadcast from a hub.
    *
    * @throws Exception if the test fails
    */
  @Test
  @throws[Exception]
  def broadcastStringMessage() {
    hub1.broadcast(StringMessage("A string message is broadcast from a hub."))
  }
  
  /**
    * Tests the scenario in which a binary message is sent from a hub along a connector that links the hub to a Webcam.
    *
    * @throws Exception if the test fails
    */
  @Test
  @throws[Exception]
  def sendBinaryMessage() {
    hub1.sendTo(BinaryMessage(132), hub1.getConnector(0))
  }
  
  /**
    * Tests the scenario in which a binary message is broadcast from a hub.
    *
    * @throws Exception if the test fails
    */
  @Test
  @throws[Exception]
  def broadcastBinaryMessage() {
    hub2.broadcast(BinaryMessage(132465798))
  }
  
}
