package edu.cwru.eecs293.ttf10.uxb

import org.junit.Assert._
import org.junit.{Before, Test}

/** <p>
  * Class for testing methods in [[edu.cwru.eecs293.ttf10.uxb.AbstractDevice AbstractDevice]] and other underlying functionality thereof. Since the <tt>AbstractDevice</tt> class is obviously abstract, test cases employ concrete subclass [[edu.cwru.eecs293.ttf10.uxb.Hub Hub]] to allow for actualization and access of the superceding functionality in question.
  * <p>
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf Hw2.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
class AbstractDeviceTest {

  private var validBuilder: Hub.Builder = _
  private var validVersion: Int = _
  private var validConnectors: List[Connector.Type] = _

  private var invalidBuilder: Hub.Builder = _

  @Before
  def setUp() {
    // Set up valid builder
    validVersion = 2016
    validBuilder = new Hub.Builder(validVersion)
    validConnectors = List(Connector.Type.COMPUTER, Connector.Type.PERIPHERAL)
    validBuilder.connectors(validConnectors)

    // Set up invalid builder
    invalidBuilder = new Hub.Builder(null.asInstanceOf[Int])
    invalidBuilder.connectors(null)
  }

  @Test
  @throws[Exception]
  def build() {
    val validHub = validBuilder.build()
    assertEquals(validHub.getVersion, validVersion)
    assertEquals(validHub.getProductCode, Option.empty)
    assertEquals(validHub.getSerialNumber, Option.empty)
    assertEquals(validHub.getConnectors, validConnectors)
  }

  @Test
  @throws[Exception]
  def productCode() {
    val builder = new Hub.Builder(validVersion)

    // Nonnull product code
    builder.productCode(293)
    assertEquals(builder.getProductCode, Option(293))

    // Null product code
    builder.productCode(null.asInstanceOf[Int])
    assertEquals(builder.getProductCode, Option.empty)
  }

  @Test
  @throws[Exception]
  def serialNumber() {
    val builder = new Hub.Builder(validVersion)

    // Nonnull serial number
    builder.serialNumber(1234567890)
    assertEquals(builder.getSerialNumber, Option(1234567890))

    // Null serial number
    builder.serialNumber(null.asInstanceOf[BigInt])
    assertEquals(builder.getSerialNumber, Option.empty)
  }

  @Test
  @throws[Exception]
  def connectors() {
    val builder = new Hub.Builder(validVersion)

    // Singleton list of connector types
    builder.connectors(List(Connector.Type.COMPUTER))
    assertEquals(builder.getConnectors, List(Connector.Type.COMPUTER))

    // Multiple-element list of connector types
    builder.connectors(List(Connector.Type.COMPUTER, Connector.Type.PERIPHERAL))
    assertEquals(builder.getConnectors, List(Connector.Type.COMPUTER, Connector.Type.PERIPHERAL))

    // Null list of connector types
    builder.connectors(null)
    assertEquals(builder.getConnectors, List.empty)
  }

  @Test
  @throws[Exception]
  def validate() {
    // Implement builder in order to reach protected method 'validate'
    new Hub.Builder(validVersion) {
      connectors(validConnectors)
      validate()
    }

    // Invalid builder: null version number
    var valid = true
    try {
      new Hub.Builder(null.asInstanceOf[Int]) {
        connectors(validConnectors)
        validate()
      }
    } catch {
      case e: IllegalStateException => valid = false
    } finally {
      if (valid) throw new IllegalStateException("Test failed: validation passed despite null version number")
    }

    // Invalid builder: no computer connector
    valid = true
    try {
      new Hub.Builder(validVersion) {
        connectors(List(Connector.Type.PERIPHERAL))
        validate()
      }
    } catch {
      case e: IllegalStateException => valid = false
    } finally {
      if (valid) throw new Exception("Test failed: validation passed despite no computer connector")
    }

    // Invalid builder: no peripheral connector
    valid = true
    try {
      new Hub.Builder(validVersion) {
        connectors(List(Connector.Type.COMPUTER))
        validate()
      }
    } catch {
      case e: IllegalStateException => valid = false
    } finally {
      if (valid) throw new Exception("Test failed: validation passed despite no peripheral connector")
    }
  }

}
