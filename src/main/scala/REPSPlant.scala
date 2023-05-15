import java.util.{Timer, TimerTask}

class REPSPlant extends Plant {
  override val name: String = "REPS Plant"
  // val solarPlant: SingleSourcePlant = ???
  // val hydroPlant: SingleSourcePlant = ???
  // val windPlant: SingleSourcePlant = ???
  val executionTimer = new Timer()
  override val healthSensor = new RandomHealthSensor(1, name)
  override val dataStorages: List[SensorDataStorage] = List(healthSensor.storage)
  override val energyOutputSensor: Sensor = new RandomHealthSensor(2, name)
  override val sensors: List[Sensor] = List()
  override def getAlertIDs(): List[Int] = ???
  def run() = {
    val checkSensors = new TimerTask {
      def run() = healthSensor.saveReading()
    }
    executionTimer.schedule(checkSensors, 1000L, 5000L)
  }
  def stop(): Unit = {
    executionTimer.cancel()
    executionTimer.purge()
  }
}
