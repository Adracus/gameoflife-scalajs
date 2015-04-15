package gameoflife.core

import gameoflife.component.GameComponent
import gameoflife.gamefield.GameField
import gameoflife.graphics.Renderer
import org.scalajs.dom.raw.{Event, HTMLInputElement, HTMLButtonElement, HTMLCanvasElement}

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document

import scala.scalajs.js.annotation.JSExport

/**
 * Created by axel on 13/04/15.
 */
object Main extends JSApp {
  def main(): Unit = {
    val canvas = document.querySelector("#canvas").asInstanceOf[HTMLCanvasElement]
    val toggleButton = document.querySelector("#toggle").asInstanceOf[HTMLButtonElement]
    val clearButton = document.querySelector("#clear").asInstanceOf[HTMLButtonElement]
    val updateButton = document.querySelector("#update").asInstanceOf[HTMLButtonElement]
    val randomButton = document.querySelector("#random").asInstanceOf[HTMLButtonElement]
    val sizeInput = document.querySelector("#size").asInstanceOf[HTMLInputElement]

    val game = new GameComponent(canvas)

    document.onreadystatechange = (_: Any) => ready()

    def ready(): Unit = {
      if (document.readyState == "complete") {
        updateButton.onclick = (_: Any) => update()
        clearButton.onclick = (_: Any) => clearGame()
        toggleButton.onclick = (_: Any) => toggleGame()
        randomButton.onclick = (_: Any) => random()
      }
    }

    def random(): Unit = {
      game.random()
    }

    def update(): Unit = {
      val value = sizeInput.value.toInt
      game.setSize(value)
    }

    def clearGame(): Unit = {
      game.clear()
    }

    def toggleGame(): Unit = {
      game.start()
    }
  }
}
