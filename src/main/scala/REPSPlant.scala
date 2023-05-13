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
}
