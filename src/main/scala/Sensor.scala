// A trait for all sensors
trait Sensor {
  val id: Int
  val dateTimeProvider: DateTimeProvider
  val storage: SensorDataStorage
  //TODO update lastReading
  var lastReading: SensorReading
  def getName(): String
  def getValue(): Int
  private def createReading(sensorValue: Int): SensorReading = {
    val dateTime = dateTimeProvider.getDateTime()
    lastReading = SensorReading(
      time = dateTime.toLocalTime(),
      date = dateTime.toLocalDate(),
      value = sensorValue,
      sensorId = id
    )
    lastReading
  }

  def saveReading() = storage.addReading(createReading(getValue()))
  def hasAlerts(): Boolean
}
