package console

import scala.io.StdIn.readLine
import ConsoleDataProcessing.processAndPrint
import ConsoleFunctionsSolar._
import ConsoleFunctionsWind.windTurbineAdjustmentMenu
import ConsoleFunctionsHydro.hydroPowerAdjustmentMenu
import plant.{Plant, REPSPlant}

object ConsoleApp extends App {
  println("Welcome to REPS power plant!")
  val plant = new REPSPlant()
  plant.run()
  mainMenu()

  implicit class PlantData(powerPlant: Plant) {
    def checkHealth(): Unit =
      processAndPrint(powerPlant.getHealthData())

    def checkOutput(): Unit =
      processAndPrint(powerPlant.getEnergyOutputData())
  }

  def inRange(lower: Int, upper: Int)(x: Int) =
    x <= upper && x >= lower

  def mainMenu(): Unit = {
    println("What would you like to do?\n"
      + "1. Look at the data\n"
      + "2. Adjust plant parameters\n"
      + "0. Exit the application")
    val option = readLine()
    option match {
      case "1" => checkData()
      case "2" => adjustParameters()
      case "0" => exit()
    }
    mainMenu()
  }

  def checkData(): Unit = {
    println("What data do you want to see?\n"
      + "1. Check the cameras (Not implemented)\n"
      + "2. Check the temperature in the building (Not implemented)\n"
      + "3. Check the health \n"
      + "4. Check the total energy output\n"
      + "5. Check the energy sources\n"
      + "0. Go to the main menu ")
    val option = readLine()
    option match {
      case "1" => checkCameras()
      case "2" => checkBuildingTemperature()
      case "3" => plant.checkHealth()
      case "4" => plant.checkOutput()
      case "5" => chooseDataSource()
      case "0" => ()
    }
  }

  def adjustParameters() = {
    println("Choose the type of energy source:\n" +
      "1. Solar panels\n" +
      "2. Wind turbines\n" +
      "3. Hydropower\n" +
      "0. Go to the main menu")

    val energySource = readLine()
    energySource match {
      case "1" => plant.solarPlant.adjustSolarPlantParameters()
      case "2" => hydroPowerAdjustmentMenu()
      case "3" => windTurbineAdjustmentMenu()
      case "0" => mainMenu()
      case _ => {
        println("Invalid choice. Please try again.")
        chooseDataSource()
      }
    }
  }

  def chooseDataSource(): Unit = {
    println("Choose the type of energy source:\n" +
      "1. Solar panels\n" +
      "2. Wind turbines (Not implemented)\n" +
      "3. Hydropower (Not implemented)\n" +
      "0. Go to the main menu")

    val energySource = readLine()
    energySource match {
      case "1" => plant.solarPlant.displaySolarData()
      case "0" => ()
      case "2" => chooseDataSource()
      case "3" => chooseDataSource()
      case _ => {
        println("Invalid choice. Please try again.")
        chooseDataSource()
      }
    }
  }

  def askForValue(prompt: String, inLimits: (Int) => Boolean): Int = {
    println(prompt)
    val value = readLine()
    value.toIntOption match {
      case Some(intValue) =>
        if (inLimits(intValue)) {
          intValue
        } else {
          println("The value exceeds the limits.")
          askForValue(prompt, inLimits)
        }
      case None => {
        println("Invalid input. Please enter a valid integer.")
        askForValue(prompt, inLimits)
      }
    }
  }

  def exit(): Unit = {
    println("Exiting the program...")
    plant.stop()
    System.exit(0)
  }

  def checkCameras(): Unit = {
    println("Checking the cameras...")
  }

  def checkBuildingTemperature(): Unit = {
    println("Checking the temperature in the building...")
  }
}

