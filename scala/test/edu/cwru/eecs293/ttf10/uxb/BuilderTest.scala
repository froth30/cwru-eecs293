package edu.cwru.eecs293.ttf10.uxb

import org.junit.Assert._
import org.junit.{Before, Test}

/**
  * Test class for <tt>Hub.Builder</tt>
  */
class BuilderTest {

  private var validBuilder: Hub.Builder = _
  private var validVersion: Int = _
  private var validConnectors: List[Connector.Type.Type] = _

  private var invalidBuilder: Hub.Builder = _

  @Before
  def setUp(): Unit = {
    validVersion = 2016
    validBuilder = new Hub.Builder(validVersion)
    validConnectors = List(Connector.Type.COMPUTER, Connector.Type.PERIPHERAL)
    validBuilder.connectors(validConnectors)

    invalidBuilder = new Hub.Builder(null.asInstanceOf[Int])
    invalidBuilder.connectors(null)
  }

  @Test
  @throws[Exception]
  def build() {
    val validHub = validBuilder.build()
    assertTrue(validHub.getVersion equals validVersion)
    assertEquals(validHub.getProductCode, Option.empty)
    assertEquals(validHub.getSerialNumber, Option.empty)
    assertEquals(validHub.getConnectors, validConnectors)
  }

  @Test
  @throws[Exception]
  def productCode() {
  }

  @Test
  @throws[Exception]
  def serialNumber() {
  }

  @Test
  @throws[Exception]
  def connectors() {
  }

  @Test
  @throws[Exception]
  def validate() {
  }

}
