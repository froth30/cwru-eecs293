package edu.cwru.eecs293.ttf10.uxb

/**
  * Represents one of many <i>classes</i> in which UXB devices are grouped.
  * <p> The concept of device class similar in traditional USB. UXB classes are: audio, communication, human-interface, physical-interface (e.g., force feedback joysticks), image, printer, mass storage, video, audio-video, virtual reality, and hub.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4287477_1/xid-4287477_1 Hw2.pdf]]
  * @since Programming Assignment 2
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Ted Frohlich < ttf10@case.edu >
  */
case object DeviceClass extends Enumeration {
  
  type DeviceClass = Value
  
  val UNSPECIFIED, AUDIO, COMM, HID, PID, IMAGE, PRINTER, STORAGE, VIDEO, AV, VR, HUB = Value
  
}
