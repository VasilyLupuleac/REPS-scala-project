trait Plant {
  val dataStorages: List[SensorDataStorage]
  val name: String
  val sensors: List[Sensor]
  def getAlertIDs(): List[Int]
}
