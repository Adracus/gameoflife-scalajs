package gameoflife.graphics

/**
 * Created by axel on 13/04/15.
 */
object Color extends Enumeration {
  val Red = Color(255, 0, 0)
  val Blue = Color(0, 255, 0)
  val Green = Color(0, 0, 255)
  val White = Color(255, 255, 255)
  val Black = Color(0, 0, 0)
}

case class Color(val r: Int, val g: Int, val b: Int, val a: Double = 1.0) {
  override def toString = s"rgb($r, $g, $b)"
}
