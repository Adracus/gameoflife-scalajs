package gameoflife.graphics

import gameoflife.gamefield.GameField
import org.scalajs.dom.raw.{CanvasRenderingContext2D, HTMLCanvasElement}

/**
 * Created by axel on 13/04/15.
 */
class Renderer(private val canvas: HTMLCanvasElement) {
  val ctx = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]

  def draw(gameField: GameField): Unit = {
    val ratio = getRatio(gameField)
    val cellWidth = ratio._1.toInt
    val cellHeight = ratio._2.toInt
    clear(Color.White)
    for (x <- 0 until gameField.width; y <- 0 until gameField.height) {
      val color = if (gameField(x, y)) Color.Red else Color.White
      fillRectangle(x * cellWidth, y * cellHeight, cellWidth, cellHeight, color)
    }
  }

  private def clear(color: Color = Color.White): Unit = {
    fillRectangle(0, 0, width, height, color)
  }

  private def fillRectangle(x: Int, y: Int, width: Int, height: Int, color: Color = Color.Black): Unit = {
    val temp = ctx.fillStyle
    ctx.fillStyle = color.toString
    ctx.fillRect(x, y, width, height)
    ctx.fillStyle = temp
  }

  private def width = canvas.width
  private def height = canvas.height
  private def dim = (width, height)
  private def getXRatio(gameField: GameField) = width.toDouble / gameField.width
  private def getYRatio(gameField: GameField) = height.toDouble / gameField.height
  private def getRatio(gameField: GameField) = (getXRatio(gameField), getYRatio(gameField))
}
