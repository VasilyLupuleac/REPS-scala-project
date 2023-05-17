import ConsoleApp.{askForValue}
import scala.io.StdIn.readLine

object ConsoleFunctionsWind {
  def windTurbineAdjustmentMenu(): Unit = {
    println("Wind turbine menu options:\n"
      + "1. Set rotor blade angle\n"
      + "2. Yaw control\n"
      + "0. Back to main menu")

    val option = readLine()
    option match {
      case "1" => setRotorBladeAngle()
      case "2" => yawControl()
      case "0" => ()
      case _ => {
        println("Invalid choice. Please try again.")
        windTurbineAdjustmentMenu()
      }
    }
  }

  def setRotorBladeAngle(): Unit = {
    val angle = askForValue("Enter the rotor blade angle:", _ => true)
  }

  def yawControl(): Unit = {
    val yaw = askForValue("Enter the yaw value:", _ => true)
  }
}
