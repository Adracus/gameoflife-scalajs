package gameoflife.component

import gameoflife.gamefield.GameField
import gameoflife.graphics.Renderer
import org.scalajs.dom
import dom.document
import org.scalajs.dom.raw.{MouseEvent, HTMLCanvasElement}

/**
 * Created by axel on 15/04/15.
 */
class GameComponent(val canvas: HTMLCanvasElement, val blockSize: Int = 16) {
  private val builder = new GameBuilder(23)
  private val renderer = new Renderer(canvas)
  private var field: Option[GameField] = None
  private var handlerId: Option[Int] = None

  update()

  renderer.draw(builder.fields)

  canvas.onclick = onClick _

  def onClick(event: MouseEvent):Unit = {
    stop()
    val rect = canvas.getBoundingClientRect()
    builder.invert(interpolate(event.clientX - rect.left, event.clientY - rect.top))
    draw()
  }

  def size_= (size: Int):Unit = {
    builder.size = size
    update()
  }

  private def update(): Unit = {
    canvas.width = blockSize * builder.size
    canvas.height = blockSize * builder.size
    draw()
  }

  private def draw(): Unit = {
    if (handlerId.isDefined) {
      renderer.draw(field.get.field)
      return
    }
    renderer.draw(builder.fields)
  }

  def interpolate(x: Double, y: Double): Tuple2[Int, Int] = {
    val xPos = x / cellWidth
    val yPos = y / cellHeight
    (xPos.toInt, yPos.toInt)
  }

  def cellWidth = canvas.width / builder.size
  def cellHeight = canvas.height / builder.size

  def start(): Unit = {
    stop()
    field = Some(builder.build)
    handlerId = Some(dom.setInterval(tick _, 1000))
  }

  def stop(): Unit = {
    if (handlerId.isDefined) {
      dom.clearInterval(handlerId.get)
      handlerId = None
    }
    if (field.isDefined) builder.fields = field.get.field
  }

  def clear(): Unit = {
    stop()
    builder.clear()
    field = Some(builder.build)
    draw()
  }

  private def tick(): Unit = {
    if (!field.get.hasNext) {
      stop()
      return
    }
    field = Some(field.get.next())
    draw()
  }
}
