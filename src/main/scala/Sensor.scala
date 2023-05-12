// A trait for all sensors
trait Sensor {
  def generateReading: () => SensorReading
}
