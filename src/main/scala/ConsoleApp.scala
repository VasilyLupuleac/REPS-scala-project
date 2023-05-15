import java.time.format.DateTimeFormatter
import scala.io.StdIn.readLine

object ConsoleApp extends App {

  println("Welcome to X power plant!")
  val plant = new REPSPlant
  plant.run()

  def mainMenu1(): Unit = {
    println("Check data\n" + "1. Check the cameras \n" + "2. Check the temperature in the building\n" + "3. Check the health \n" + "4. Check the energy sources\n" + "5. Exit")
    val option = scala.io.StdIn.readLine()
    option match {
      case "1" => checkCameras()
      case "2" => checkBuildingTemperature()
      case "3" => checkHealth()
      case "4" => checkEnergySources()
      case "5" => exit()
    }

  }

  def mainMenu2(): Unit = {
    println("Choose a type of energy source!\n" + "1. Solar panels\n" + "2. Wind turbines\n" + "3. Hydropower\n" + "4. Exit")

    val energySource = scala.io.StdIn.readLine()
    energySource match {
      case "1" => solarPanelMenu()
      case "2" => windTurbineMenu()
      case "3" => hydroPowerMenu()
      case "4" => exit()
      case _ => {
        println("Invalid choice. Please try again.")
        mainMenu2()
      }
    }
  }

  def askForValue(prompt: String, inLimits: (Int) => Boolean): Int = {
    println(prompt)
    val value = scala.io.StdIn.readLine()
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

  def solarPanelMenu(): Unit = {
    println("Solar panel menu options:\n" + "1. Set solar panel angle\n" + "2. Solar tracking system\n" + "3. Cleaning solar panels\n" + "4. Back to main menu")

    val option = scala.io.StdIn.readLine()
    option match {
      case "1" => setSolarPanelAngle()
      case "2" => solarTrackingSystem()
      case "3" => cleaningSolarPanels()
      case "4" => mainMenu2()
      case _ => {
        println("Invalid choice. Please try again.")
        solarPanelMenu()
      }
    }
  }

  def windTurbineMenu(): Unit = {
    println("Wind turbine menu options:\n" + "1. Set rotor blade angle\n" + "2. Yaw control\n" + "3. Back to main menu")

    val option = scala.io.StdIn.readLine()
    option match {
      case "1" => setRotorBladeAngle()
      case "2" => yawControl()
      case "3" => mainMenu2()
      case _ => {
        println("Invalid choice. Please try again.")
        windTurbineMenu()
      }
    }
  }

  def hydroPowerMenu(): Unit = {
    println("Hydropower menu options:\n" + "1. Flow control\n" + "2. Gate opening\n" + "3. Water level\n" + "4. Back to main menu")

    val option = scala.io.StdIn.readLine()
    option match {
      case "1" => flowControl()
      case "2" => gateOpening()
      case "3" => waterLevel()
      case "4" => mainMenu2()
      case _ => {
        println("Invalid choice. Please try again.")
        hydroPowerMenu()
      }
    }
  }

  def setSolarPanelAngle(): Unit = {
    val angle = askForValue("Enter the solar panel angle (0-60):", (x) => x <= 60 && x >= 0)
  }

  def solarTrackingSystem(): Unit = {
    val direction = askForValue("Enter the direction the panels should face (0-359):", (x) => x < 360 && x >= 0)
  }

  def cleaningSolarPanels(): Unit = {
    plant.solarPlant.cleanPanels()
    println("The panels are cleaned")
  }

  def setRotorBladeAngle(): Unit = {
    val angle = askForValue("Enter the rotor blade angle:", _ => true)
  }

  def yawControl(): Unit = {
    val yaw = askForValue("Enter the yaw value:", _ => true)
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

  private val dateFormat = DateTimeFormatter.ISO_LOCAL_DATE
  private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
  def printEntry(r: SensorReading) =
    println(s"Date: ${r.date.format(dateFormat)}, Time: ${r.time.format(timeFormat)}, Value: ${r.value}, ID: ${r.sensorId}")
  def checkHealth(): Unit = {
    println("Checking the health...")
    plant.getHealthData().foreach(printEntry)
  }

  def checkEnergySources(): Unit = {
    mainMenu2()
  }


  mainMenu1()
  mainMenu2()
}
