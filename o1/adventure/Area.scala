package o1.adventure

import scala.collection.mutable.Stack

/** The class `Area` represents locations in a text adventure game world. A game world
  * consists of areas. In general, an "area" can be pretty much anything: a room, a building,
  * an acre of forest, or something completely different. What different areas have in
  * common is that players can be located in them and that they can have exits leading to
  * other, neighboring areas. An area also has a name and a description.
  * @param name         the name of the area
  * @param description  a basic description of the area (typically not including information about items) */
class Area(var name: String, var description: String) {


  /** The towers that hold the disks. */
  private val towers = new Array[Stack[Item]](3)



  /** Add disk to the towers at the beginning. */
  def addDisk(): Unit = {
    for (i <- towers.indices) {
      towers(i) = new Stack[Item]()
    }
    (0 to 4).foreach(i => towers(0).push(new Item("Disk"+(5-i), 5-i)))
  }

  /** Move a disk from src to des tower if possible. Returns a Boolean
    * to indicate if the move was successful. */
  def moveDisk(src: Int, des: Int): Boolean = {
    var moved = false
    if (towers(src).nonEmpty) {
      val disk = towers(src).top
      if (towers(des).isEmpty || disk.size < towers(des).top.size) {
        towers(src).pop()
        towers(des).push(disk)
        moved = true
      }
    }
    moved
  }

  /** Checks if completed. */
  def complete: Boolean = towers(2).length == 5


  /** Describes the current positions of the disks. */
  def fullDescription = {
    var disks = ""
    for (i <-towers.indices) {
      if (towers(i).nonEmpty) {
        disks += "Tower" + (i+1) + ":\n" + towers(i).mkString("\n") + "\n\n"
      } else {
        disks += "Tower" + (i+1) + ": Empty\n\n"
      }
    }
    disks
  }


  /** Returns a single-line description of the area for debugging purposes. */
  override def toString = this.name + ": " + this.description.replaceAll("\n", " ").take(150)



}
