import java.time.{LocalDate, LocalDateTime, LocalTime}
import scala.util.Random

class RandomHealthSensor(val id: Int, val plantName: String) extends Sensor {
  private var alerted = false
  val lowerLimit = 80
  override val dateTimeProvider: DateTimeProvider = new HourlyDateTimeProvider(LocalDateTime.now())
  private val decreaseProbability = 0.2
  private val rng = new Random(id)
  private var health = 95
  override val storage: SensorDataStorage = new FileSensorStorage(plantName + "Health.txt")
  override var lastReading = SensorReading(LocalTime.now(), LocalDate.now(), health, id)
  override def getName() = plantName + " health sensor"
  def setHealth(newHealth: Int): Unit = {
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
