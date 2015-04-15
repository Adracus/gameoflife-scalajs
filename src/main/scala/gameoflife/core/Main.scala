package gameoflife.core

import gameoflife.gamefield.GameField
import gameoflife.graphics.Renderer
import org.scalajs.dom.raw.HTMLCanvasElement

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document

/**
 * Created by axel on 13/04/15.
 */
object Main extends JSApp {
  def main(): Unit = {
    val canvas = document.querySelector("#canvas").asInstanceOf[HTMLCanvasElement]
    val renderer = new Renderer(canvas)

    var field = GameField.random(34, 34)
    renderer.draw(field)
    val id = dom.setInterval(() => {
      println("Loop")
      if (field.hasNext) {
        field = field.next()
        renderer.draw(field)
      } else {
        println("Everything Dead")
      }
    }, 1000)
  }
}
