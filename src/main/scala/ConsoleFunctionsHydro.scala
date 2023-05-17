import ConsoleApp.{askForValue}

import scala.io.StdIn.readLine

object ConsoleFunctionsHydro {
  def hydroPowerAdjustmentMenu(): Unit = {
    println("Hydropower menu options:\n" + "1. Flow control\n" + "2. Gate opening\n" + "3. Water level\n" + "0. Back to main menu")

    val option = readLine()
    option match {
      case "1" => flowControl()
      case "2" => gateOpening()
      case "3" => waterLevel()
      case "0" => ()
      case _ => {
        println("Invalid choice. Please try again.")
        hydroPowerAdjustmentMenu()
      }
    }
  }

  def flowControl(): Unit = {
    val flow = askForValue("Enter the flow value:", _ => true)
  }

  def gateOpening(): Unit = {
    val gate = askForValue("Enter how much you want the gate to be opened:", _ => true)
  }

  def waterLevel(): Unit = {
    val level = askForValue("Enter the water level:", _ => true)
  }

}
