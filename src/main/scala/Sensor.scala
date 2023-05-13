// A trait for all sensors
trait Sensor {
  val id: Int
  val dateTimeProvider: DateTimeProvider
  val storage: SensorDataStorage
  var lastReading: SensorReading
  def getName(): String
  def getValue(): Int
  def createReading(sensorValue: Int): SensorReading =
    SensorReading(
      time = dateTimeProvider.getTime(),
      date = dateTimeProvider.getDate(),
      value = sensorValue,
      sensorId = id
    )
  def saveReading() = storage.addReading(createReading(getValue()))
  def hasAlerts(): Boolean
}
