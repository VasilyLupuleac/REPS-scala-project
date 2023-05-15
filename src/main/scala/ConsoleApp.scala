import scala.io.StdIn.readLine

object ConsoleApp extends App {
  println("Welcome to X power plant!")
  println("Choose a type of energy source!\n" + "1. Solar panels\n" + "2. Wind turbines\n" + "3.Hydropower")

  val energySource = scala.io.StdIn.readLine()
  energySource match {
    case 1 => //print solarPanelsFile contents
    case 2 => //print windTurbinesFile contents
    case 3 => //print hydropowerFile contents
  }

  println("What do you want to do next?")

  val option = scala.io.StdIn.readLine()
  option match{
    case 1 => {
      println("How would you like to filter the data?\n" + "1. By hour\n" + "2. By day\n" + "3. By week\n" + "By month\n" + "By month, in a specific year")
      println("What would you like to calculate?\n" + "Mean\n" + "Median\n" + "Mode\n" + "Range\n" + "Midrange\n")
    }  //filer the data

    case 2 => //check the power plant's health
    case 3 => //check the security cameras
    case 4 => //go back to the first menu
    case 5 => //exit the programme
  }
}
