// A trait for all sensors
trait Sensor {
  val id: Int
  def getName(): String
  def getReading(): SensorReading
}
