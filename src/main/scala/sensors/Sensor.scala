package sensors

import plant.DateTimeProvider

trait Sensor {
  val id: Int
  val dateTimeProvider: DateTimeProvider
  val storage: SensorDataStorage
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
