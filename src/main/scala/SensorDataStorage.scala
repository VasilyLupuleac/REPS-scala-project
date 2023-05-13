trait SensorDataStorage {
  def addReading(entry: SensorReading): Unit
  def getAllReadings(): List[SensorReading]
}
