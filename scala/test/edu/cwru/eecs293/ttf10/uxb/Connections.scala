package edu.cwru.eecs293.ttf10.uxb

import org.junit.{Before, Test}

/**
  * Test class for making sure that the primary methods work as intended.
  *
  * @since Programming Assignment 4
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Ted Frohlich < ttf10@case.edu >
  */
class Connections {
  
  private var hub, hubPeer1, hubPeer2, hubPeer3, hubPeer4, goJustBad : Device = _
  
  @Before
  def setUp() {
    // Create devices
    hub = new Hub.Builder(2016)
      .connectors(List(
        Connector.Type.PERIPHERAL,
        Connector.Type.COMPUTER,
        Connector.Type.COMPUTER,
        Connector.Type.COMPUTER))
      .build()
    hubPeer1 = new Hub.Builder(2012)
      .connectors(List(
        Connector.Type.COMPUTER,
        Connector.Type.PERIPHERAL,
        Connector.Type.COMPUTER))
      .build()
    hubPeer2 = new SisterPrinter.Builder(1984)
      .connectors(List(Connector.Type.PERIPHERAL))
      .build()
    hubPeer3 = new CannonPrinter.Builder(2000)
      .connectors(List(Connector.Type.PERIPHERAL))
      .build()
    hubPeer4 = new GoAmateur.Builder(2009)
      .connectors(List(Connector.Type.PERIPHERAL))
      .build()
    goJustBad = new GoAmateur.Builder(2010)
      .connectors(List(
        Connector.Type.PERIPHERAL,
        Connector.Type.PERIPHERAL))
      .build()
  
    // Connect peers to hub
    hub.getConnector(0).setPeer(hubPeer1.getConnector(0))
    hub.getConnector(1).setPeer(hubPeer2.getConnector(0))
    hub.getConnector(2).setPeer(hubPeer3.getConnector(0))
    hub.getConnector(3).setPeer(hubPeer4.getConnector(0))
  }
  
  @Test
  @throws[Exception]
  def connect_BUSY() {
    // Try to connect where the old one was
    var thrown = false
    try {
      hub.getConnector(1).setPeer(goJustBad.getConnector(0))
    } catch {
      case e: ConnectionException => thrown = true
    } finally {
      if (!thrown) throw new IllegalStateException("Test failed: connector was supposed to be busy.")
    }
  }
  
  @Test
  @throws[Exception]
  def connect_MISMATCH() {
    // Try to connect two like types
    var thrown = false
    try {
      hubPeer1.getConnector(1).setPeer(goJustBad.getConnector(0))
    } catch {
      case e: ConnectionException => thrown = true
    } finally {
      if (!thrown) throw new IllegalStateException("Test failed: connectors were of the same type.")
    }
  }
  
  @Test
  @throws[Exception]
  def connect_CYCLE() {
    // Try to create a cycle
    var thrown = false
    try {
      hubPeer1.getConnector(1).setPeer(goJustBad.getConnector(1))
    } catch {
      case e: ConnectionException => thrown = true
    } finally {
      if (!thrown) throw new IllegalStateException("Test failed: created a connection cycle.")
    }
  }
  
}
