package o1.adventure



/** A `Player` object represents a player character controlled by the real-life user of the program.
  *
  * A player object's state is mutable: the player's location and possessions can change, for instance.
  *
  * @param startingArea  the initial location of the player */
class Player(startingArea: Area) {

  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag

  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven


  /** Returns the current location of the player. */
  def location = this.currentLocation


  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def quit() = {
    this.quitCommandGiven = true
    ""
  }


  /** Move a disk from src to des tower. */
  def moveDisk(src: Int, des: Int) = {
    if (this.currentLocation.moveDisk(src, des)) {
      s"Moved disk from Tower${src+1} to Tower${des+1}."
    } else {
      "Cannot make this move!"
    }
  }

  /** Reset the game. */
  def reset() = {
    this.currentLocation.addDisk()
    "The game has been reset!"
  }

  /** Shows instructions. */
  def help: String = {
    """If you want to move the disks from Tower1 to Tower3, you might want to first move the 4 upper disks
      #from Tower1 to Tower2, then the last disk from Tower1 to Tower3, and finally the 4 disks from Tower2 to Tower3. Similarly,
      #to move 4 disks from Tower1 to Tower2, first move 3 upper disks from Tower1 to Tower3, then 1 disk from Tower1 to Tower2, then
      #3 disks from Tower3 to Tower2. And so on and so on... Available commands: move, reset, help, quit.""".stripMargin('#').replaceAll("\n", " ")
  }


  /** Returns a brief description of the player's state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name


}


