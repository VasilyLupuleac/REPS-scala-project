package plant

import sensors.{Sensor, SensorReading}

trait Plant {
  val name: String
  val sensors: List[Sensor]
  def getHealthData(): List[SensorReading]
  def getEnergyOutputData(): List[SensorReading]
  def getAlertIDs() = sensors.filter(_.hasAlerts()).map(_.id)
}
