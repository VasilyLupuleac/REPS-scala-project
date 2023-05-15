object ConsoleApp extends App {
  println("Welcome to X power plant!")


  def mainMenu1(): Unit = {
    println("Check data\n" + "1. Check the cameras \n" + "2. Check the temperature in the building\n" + "3.Check the health \n" + "4. Check the energy sources\n" + "5. Exit")
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

  def askForValue(prompt: String): Int = {
    println(prompt)
    val value = scala.io.StdIn.readLine()
    value.toIntOption match {
      case Some(intValue) => intValue
      case None => {
        println("Invalid input. Please enter a valid integer.")
        askForValue(prompt)
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
    val angle = askForValue("Enter the solar panel angle:")
  }

  def solarTrackingSystem(): Unit = {
    val movement = askForValue("Enter how much you want the solar panels to move:")
  }


  def cleaningSolarPanels(): Unit = {
    println("The panels are cleaned")
  }

  def setRotorBladeAngle(): Unit = {
    val angle = askForValue("Enter the rotor blade angle:")
  }

  def yawControl(): Unit = {
    val yaw = askForValue("Enter the yaw value:")
  }


  def flowControl(): Unit = {
    val flow = askForValue("Enter the flow value:")
  }

  def gateOpening(): Unit = {
    val gate = askForValue("Enter how much you want the gate to be opened:")
  }

  def waterLevel(): Unit = {
    val level = askForValue("Enter the water level:")
  }

  def exit(): Unit = {
    println("Exiting the program...")
    System.exit(0)
  }

  def checkCameras(): Unit = {
    println("Checking the cameras...")

  }

  def checkBuildingTemperature(): Unit = {
    println("Checking the temperature in the building...")

  }

  def checkHealth(): Unit = {
    println("Checking the health...")
  }

  def checkEnergySources(): Unit = {
   mainMenu2()
  }


  mainMenu1()
  mainMenu2()
}
