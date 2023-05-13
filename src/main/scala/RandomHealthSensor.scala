import java.time.{LocalDate, LocalDateTime, LocalTime}
import scala.util.Random

class RandomHealthSensor(val id: Int, val plantName: String) extends Sensor {
  override val dateTimeProvider: DateTimeProvider = new HourlyDateTimeProvider(LocalDateTime.now())
  private val decreaseProbability = 0.9
  private val rng = new Random(id)
  private var health = 100
  override val storage: SensorDataStorage = new FileSensorStorage(plantName + "Health.txt")
  override var lastReading = SensorReading(LocalTime.now(), LocalDate.now(), health, id)
  override def getName() = plantName + " health sensor"

  override def getValue(): Int = {
    if (rng.nextDouble() > decreaseProbability)
      health -= 1
    health
  }

  override def hasAlerts(): Boolean = ???
}
