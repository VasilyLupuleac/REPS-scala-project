import java.time.{LocalDate, LocalDateTime, LocalTime}
import scala.util.Random

class RandomHealthSensor(val id: Int, val plantName: String) extends Sensor {
  private var alerted = false
  private val lowerLimit = 80
  override val dateTimeProvider: DateTimeProvider = new HourlyDateTimeProvider()
  private val decreaseProbability = 0.2
  private val rng = new Random(id)
  private var health = 95
  override val storage: SensorDataStorage = new FileSensorStorage(plantName + "Health.txt")
  override var lastReading = SensorReading(LocalTime.MIN, LocalDate.MIN, 0, id)
  override def getName() = plantName + " health sensor"
  def setHealth(newHealth: Int): Unit = {
    if (health >= 0)
      health = newHealth
  }
  override def getValue(): Int = {
    if (rng.nextDouble() < decreaseProbability && health > 0) {
      health -= 1
    }
    if (health < lowerLimit)
      alerted = true
    health
  }
  override def hasAlerts(): Boolean = alerted
}
