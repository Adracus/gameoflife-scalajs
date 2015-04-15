package gameoflife.core

/**
 * Created by axel on 15/04/15.
 */
trait RuleEngine {
  def nextState(current: Boolean, aliveNeighbours: Int):Boolean
}

class DefaultRuleEngine extends RuleEngine {
  override def nextState(current: Boolean, aliveNeighbours: Int): Boolean = {
    aliveNeighbours match {
      case 3 => true
      case 2 => current
      case _ => false
    }
  }
}
