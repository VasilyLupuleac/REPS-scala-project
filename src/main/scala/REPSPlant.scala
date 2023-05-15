import java.util.{Timer, TimerTask}

class REPSPlant extends Plant {
  override val name: String = "REPS Plant"
  private val idProvider = SimpleIdProvider()
  // val solarPlant: SingleSourcePlant = ???
  // val hydroPlant: SingleSourcePlant = ???
  // val windPlant: SingleSourcePlant = ???
  private val executionTimer = new Timer()
  private val healthSensor = new RandomHealthSensor(idProvider.nextId(), name)
  private val energyOutputSensor: Sensor = new RandomHealthSensor(idProvider.nextId(), name)
  override def getEnergyOutputData(): List[SensorReading] = energyOutputSensor.storage.getAllReadings()
  override def getHealthData(): List[SensorReading] = healthSensor.storage.getAllReadings()
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
