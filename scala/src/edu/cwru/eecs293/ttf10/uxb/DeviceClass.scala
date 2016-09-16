package edu.cwru.eecs293.ttf10.uxb

/**
  * Represents one of many <i>classes</i> in which UXB devices are grouped. The concept of device class similar in traditional USB. UXB classes are: audio, communication, human-interface, physical-interface (e.g., force feedback joysticks), image, printer, mass storage, video, audio-video, virtual reality, and hub.
  *
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf Hw2.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
case object DeviceClass extends Enumeration {

  final val UNSPECIFIED, AUDIO, COMM, HID, PID, IMAGE, PRINTER, STORAGE, VIDEO, AV, VR, HUB = Value

}
