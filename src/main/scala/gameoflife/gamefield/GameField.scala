package gameoflife.gamefield

import gameoflife.core.{DefaultRuleEngine, RuleEngine}

import scala.collection.mutable.ListBuffer
import scala.util.Random


object GameField {
  def random(x: Int, y: Int, ruleEngine: RuleEngine = new DefaultRuleEngine): GameField = {
    val random = new Random()
    val fields = Array.ofDim[Boolean](x, y)
    for (x <- 0 until x; y <- 0 until y) {
      fields(x)(y) = random.nextBoolean()
    }
    new GameField(fields, ruleEngine)
  }
}

/**
 * Created by axel on 13/04/15.
 */
class GameField(val field: Array[Array[Boolean]],
                private val ruleEngine: RuleEngine = new DefaultRuleEngine()) {
  def width = field.length

  def height = if (0 == field.length) 0 else field(0).length

  def apply(x: Int, y: Int) = field(x)(y)

  def get(x: Int, y: Int) = apply(x, y)

  def next(): GameField = {
    val result = Array.ofDim[Boolean](width, height)
    for (x <- 0 until width; y <- 0 until height) {
      result(x)(y) = ruleEngine.nextState(get(x, y), livingNeighbours(x, y))
    }
    new GameField(result)
  }

  def inRange(x: Int, y: Int) = x >= 0 && x < width && y >= 0 && y < height

  def hasNext: Boolean = {
    !isEverythingDead
  }

  private def isEverythingDead: Boolean = {
    field.forall(_.forall(_ == true))
  }

  private def livingNeighbours(x: Int, y: Int): Int = {
    val ns = neighbours(x, y)
    ns.filter(_ == true).length
  }

  private def neighbours(x: Int, y: Int): Seq[Boolean] = {
    val values = new ListBuffer[Boolean]()
    if (inRange(x - 1, y)) values.append(get(x - 1, y))
    if (inRange(x - 1, y - 1)) values.append(get(x - 1, y - 1))
    if (inRange(x, y - 1)) values.append(get(x, y - 1))
    if (inRange(x + 1, y - 1)) values.append(get(x + 1, y - 1))
    if (inRange(x + 1, y)) values.append(get(x + 1, y))
    if (inRange(x + 1, y + 1)) values.append(get(x + 1, y + 1))
    if (inRange(x, y + 1)) values.append(get(x, y + 1))
    if (inRange(x - 1, y + 1)) values.append(get(x - 1, y + 1))
    values.toList
  }
}
