// A trait for all sensors
trait Sensor {
  val id: Int
  val storage: SensorDataStorage
  var lastReading: SensorReading
  def getName(): String
  def getReading(): SensorReading
  def saveReading() = storage.addReading(getReading())
  def hasAlerts(): Boolean
}
