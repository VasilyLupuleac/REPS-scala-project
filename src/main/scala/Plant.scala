trait Plant {
  val dataStorages: List[SensorDataStorage]
  val name: String
  val healthSensor: Sensor
  val energyOutputSensor: Sensor
  val sensors: List[Sensor]
  def getAlertIDs(): List[Int]
}
