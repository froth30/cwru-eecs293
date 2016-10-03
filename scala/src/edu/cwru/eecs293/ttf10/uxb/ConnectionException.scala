package edu.cwru.eecs293.ttf10.uxb

/**
  * <p> A <tt>ConnectionException</tt> will be used to signal problems when attempting to interconnect device.
  *
  * @see TODO
  * @since Programming Assignment 4
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Theodore Frohlich &lt;ttf10@case.edu&gt;
  */
@SerialVersionUID(293)
class ConnectionException(private val connector: Connector,
                          private val errorCode: ConnectionException.ErrorCode
                         ) extends Exception {
  
  def getConnector = connector
  def getErrorCode = errorCode
  
}


object ConnectionException {
  
  case object ErrorCode extends Enumeration {
    val CONNECTOR_BUSY, CONNECTOR_MISMATCH, CONNECTION_CYCLE = Value
  }
  type ErrorCode = ErrorCode.Value
  
}
