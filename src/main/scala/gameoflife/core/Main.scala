package gameoflife.core

import gameoflife.component.GameComponent
import gameoflife.gamefield.GameField
import gameoflife.graphics.Renderer
import org.scalajs.dom.raw.{Event, HTMLInputElement, HTMLButtonElement, HTMLCanvasElement}

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document

/**
 * Created by axel on 13/04/15.
 */
object Main extends JSApp {
  def main(): Unit = {
    val canvas = document.querySelector("#canvas").asInstanceOf[HTMLCanvasElement]

    val game = new GameComponent(canvas)

    document.onreadystatechange = (event: Event) => {
      if (document.readyState == "complete") {
        document.querySelector("#start").asInstanceOf[HTMLButtonElement].onclick =
          (_: Any) => game.start()

        document.querySelector("#clear").asInstanceOf[HTMLButtonElement].onclick =
          (_: Any) => game.clear()

        document.querySelector("#update").asInstanceOf[HTMLButtonElement].onclick =
          (_: Any) => {
            val value = document.querySelector("#size").asInstanceOf[HTMLInputElement].value.toInt
            game.setSize(value)
          }
      }
    }
  }
}
