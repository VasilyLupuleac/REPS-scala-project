import ConsoleApp.{PlantData, askForValue, inRange, mainMenu}

import scala.io.StdIn.readLine

object ConsoleFunctionsSolar {
  implicit class SolarPlantConsoleExtensions(solarPlant: SolarPlant) {
    def adjustSolarPlantParameters(): Unit = {
      println("Solar panels adjustment options:\n"
        + "1. Set solar panel angle\n"
        + "2. Solar tracking system\n"
        + "3. Clean solar panels\n"
        + "4. Back to the main menu")

      val option = readLine()
      option match {
        case "1" => setSolarPanelAngle()
        case "2" => solarTrackingSystem()
        case "3" => cleanSolarPanels()
        case "4" => ()
        case _ => {
          println("Invalid choice. Please try again.")
          adjustSolarPlantParameters()
        }
      }
    }

    def setSolarPanelAngle(): Unit = {
      val angle = askForValue("Enter the solar panel angle (0-60):", inRange(0, 60))
      solarPlant.setAngle(angle)
    }

    def solarTrackingSystem(): Unit = {
      val direction = askForValue("Enter the direction the panels should face (0-359):", inRange(0, 359))
      solarPlant.setDirection(direction)
    }

    def cleanSolarPanels(): Unit = {
      solarPlant.cleanPanels()
      println("The panels are cleaned")
    }

    def displaySolarData(): Unit = {
      println("What data do you want to see?\n"
        + "1. Health\n"
        + "2. Energy output\n"
        + "0. Back to the main menu")

    val option = readLine()
    option match {
      case "1" => solarPlant.checkHealth()
      case "2" => solarPlant.checkOutput()
      case "0" => ()
      case _ => {
        println("Invalid choice. Please try again.")
        displaySolarData()
      }
    }
  }}
}
