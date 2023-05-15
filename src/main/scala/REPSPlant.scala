import java.util.{Timer, TimerTask}

class REPSPlant extends Plant {
  override val name: String = "REPS Plant"
  val solarPlant: SingleSourcePlant = ???
  val hydroPlant: SingleSourcePlant = ???
  val windPlant: SingleSourcePlant = ???
  override val healthSensor = new RandomHealthSensor(1, name)
  override val dataStorages: List[SensorDataStorage] = ???
  override val energyOutputSensor: Sensor = ???
  override val sensors: List[Sensor] = ???
  override def getAlertIDs(): List[Int] = ???
  def run() = {
    val timer = new Timer()
    val checkSensors = new TimerTask {
      def run() = healthSensor.saveReading()
    }
    timer.schedule(checkSensors, 1000L, 5000L)
    checkSensors.cancel()
  }
}
