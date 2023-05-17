package plant

import SimpleIdProvider.nextId
import sensors._

class SolarPlant extends Plant {
  override val name: String = "Solar Plant"
  val healthSensor = new RandomHealthSensor(nextId(), name)
  val energyOutputSensor = new SolarOutputSensor(nextId())
  // private val temperatureSensor = ???

  private def sensors = List(healthSensor, energyOutputSensor)//, temperatureSensor)
  def checkSensors() = sensors.foreach(_.saveReading())
  override def getEnergyOutputData(): List[SensorReading] = energyOutputSensor.storage.getAllReadings()

  override def getHealthData(): List[SensorReading] = healthSensor.storage.getAllReadings()

  override def getAlertIDs(): List[Int] = sensors.filter(_.hasAlerts()).map(_.id)

  def cleanPanels() = energyOutputSensor.clean()
  def setAngle(angle: Int) = energyOutputSensor.setAngle(angle)
  def setDirection(direction: Int) = energyOutputSensor.setDirection(direction)
}