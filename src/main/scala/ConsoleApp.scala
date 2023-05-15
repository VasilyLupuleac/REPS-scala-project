import scala.io.StdIn.readLine

object ConsoleApp extends App {
  println("Welcome to X power plant!")
  val plant = new REPSPlant()
  plant.run()
  def ask(): Unit = {
    println("Do you want to see the health readings? (y/n)")
    val choice = readLine()
    choice match {
      case "y" => {
        println("Showing the results")
        plant.getHealthData().foreach((r) => println(s"${r.time.toString()}: ${r.value}."))
        ask()
      }
      case _ => plant.stop()
    }
  }
  ask()
}
