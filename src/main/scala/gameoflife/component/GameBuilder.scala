package gameoflife.component

import gameoflife.gamefield.GameField

/**
 * Created by axel on 15/04/15.
 */
class GameBuilder(initialSize: Int) {
  checkSize(initialSize)
  private var _size: Int = initialSize
  var fields = Array.ofDim[Boolean](_size, _size)

  def size_= (value: Int): Unit = {
    checkSize(value)
    _size = value
    fields = Array.ofDim[Boolean](_size, _size)
  }

  def checkSize(value: Int): Unit = {
    if (value <= 0) throw new IllegalArgumentException("Value has to be greater than 0")
  }

  def clear(): Unit = {
    fields = Array.ofDim[Boolean](_size, _size)
  }

  def size = _size

  def get(x: Int, y: Int) = fields(x)(y)
  def invert(x: Int, y: Int):Unit = set(!get(x, y), x, y)
  def invert(pos: (Int, Int)):Unit = invert(pos._1, pos._2)
  def set(value: Boolean, x: Int, y: Int):Unit = fields(x)(y) = value

  def build = new GameField(fields)
}
