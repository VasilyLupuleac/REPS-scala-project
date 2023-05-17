package plant

import java.util.{Timer, TimerTask}
import SimpleIdProvider.nextId
import sensors._

class REPSPlant extends Plant {
  override val name: String = "REPS Plant"
  val solarPlant = new SolarPlant
  // val hydroPlant: Plant = ???
  // val windPlant: Plant = ???
  private val executionTimer = new Timer()
  private val healthSensor = new RandomHealthSensor(nextId(), name)
  private val energyOutputSensor: Sensor =
    new SensorAggregator(
      "Total energy output sensor",
      nextId(),
      List(solarPlant.energyOutputSensor),
      (_.foldLeft(0)((x, y) => x + y))
    )
  override def getEnergyOutputData(): List[SensorReading] = energyOutputSensor.storage.getAllReadings()
  override def getHealthData(): List[SensorReading] = healthSensor.storage.getAllReadings()
  override def getAlertIDs(): List[Int] = ???
  def run() = {
    val checkSensors = new TimerTask {
      def run() = {
        solarPlant.checkSensors()
        healthSensor.saveReading()
        energyOutputSensor.saveReading()
      }
    }
    executionTimer.schedule(checkSensors, 1000L, 5000L)
  }
  def stop(): Unit = {
    executionTimer.cancel()
    executionTimer.purge()
  }

}
