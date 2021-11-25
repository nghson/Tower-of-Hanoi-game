package o1.adventure


/** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of "hard-coded" information which pertain to a very
  * specific adventure game that involves a small trip through a twisted forest. All newly created
  * instances of class `Adventure` are identical to each other. To create other kinds of adventure
  * games, you will need to modify or replace the source code of this class. */
class Adventure {

  /** The title of the adventure game. */
  val title = "Tower of Hanoi"

  /** Creating the game's area. */
  private val home        = new Area("Tower of Hanoi", "")


  /** Initialize the game. */
  home.addDisk()

  /** The character that the player controls in the game. */
  val player = new Player(home)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0

  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete = this.home.complete

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage =
    """Move all the disks from Tower1 to Tower3.
      |You can't place a larger disk on a smaller disk.
      |Type move #1 #2 to move disk from Tower#1 to Tower#2,
      |reset to reset the game, help for some more tips, quit to stop playing.""".stripMargin.replaceAll("\n", " ")


  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage = {
    if (this.isComplete)
      "Well Done!"
    else  // game over due to player quitting
      "Goodbye!"
  }


  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String): String = {
    val action = new Action(command)
    val (outcomeReport, verb) = action.execute(this.player)
    if (outcomeReport.isDefined) {
      if (verb == "move") {
        this.turnCount += 1
      } else if (verb == "reset") {
        this.turnCount = 0
      }
    }
    outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
  }


}

