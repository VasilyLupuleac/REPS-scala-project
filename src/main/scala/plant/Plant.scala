package plant

import sensors.SensorReading

trait Plant {
  val name: String
  def getHealthData(): List[SensorReading]
  def getEnergyOutputData(): List[SensorReading]
  def getAlertIDs(): List[Int]
}
