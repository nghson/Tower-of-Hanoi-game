package o1.adventure


/** The class `Action` represents actions that a player may take in a text adventure game.
  * `Action` objects are constructed on the basis of textual commands and are, in effect,
  * parsers for such commands. An action object is immutable after creation.
  * @param input  a textual in-game command such as "go east" or "rest" */
class Action(input: String) {

  private val commandText = input.trim.toLowerCase
  private val verb = commandText.takeWhile( _ != ' ' )
  private val src = commandText.drop(verb.length).trim.takeWhile( _ != ' ' )
  private val des = commandText.drop(verb.length + 1 + src.length).trim


  /** Causes the player to move the disk. Returns a description of what happened as well as whether the player has made a move.*/
  def execute(actor: Player): (Option[String], String) = {
    if (verb == "help") {
      (Some(actor.help), verb)
    } else if (verb == "quit") {
      (Some(actor.quit()), verb)
    } else if (verb == "reset") {
      (Some(actor.reset()), verb)
    } else if (verb == "move") {
      try {
        val (s, d) = (src.toInt - 1, des.toInt - 1)
        if (0 <= s && s <= 2 && 0 <= d && d <= 2) {
          (Some(actor.moveDisk(s, d)), verb)
        } else {
          (None, verb)
        }
      } catch {
        case e: NumberFormatException => (None, verb)
      }
    } else {
      (None, verb)
    }
  }


  /** Returns a textual description of the action object, for debugging purposes. */
  override def toString = "source: " + src + ", des: " + des


}

