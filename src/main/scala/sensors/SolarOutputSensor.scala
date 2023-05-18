package sensors

import java.time.{LocalDate, LocalTime}
import scala.util.Random

class SolarOutputSensor(val id: Int) extends Sensor {
  override def getName(): String = "Solar energy production sensor " + id
  private val lowerLimit = 400
  private val upperLimit = 800
  private val contaminationLimit = 50
  private var contamination = 10
  def clean(): Unit = {
    contamination = 0
  }
  private var angle = 15
  def setAngle(newAngle: Int): Unit = {
    angle = newAngle
  }
  private var direction = 100
  def setDirection(newDirection: Int) = {
    direction = newDirection
  }

  private val rng = new Random(id)
  override var lastReading: SensorReading = SensorReading(LocalTime.MIN, LocalDate.MIN, 0, id)
  override val storage: SensorDataStorage = new FileSensorStorage("SolarOutput" + id + ".txt")
  override val dateTimeProvider: DateTimeProvider = new HourlyDateTimeProvider

  override def getValue(): Int = {
    val value = (700 + rng.nextInt(direction + 50) - 3 * rng.nextInt(angle)) * (100 - contamination) / 100
    if (contamination < 100)
      contamination += rng.nextInt(1)
    if (contamination > contaminationLimit)
      alerted = true
    else if (value > upperLimit || value < lowerLimit)
      alerted = true
    value
  }

  private var alerted = false

  override def hasAlerts(): Boolean = alerted
}