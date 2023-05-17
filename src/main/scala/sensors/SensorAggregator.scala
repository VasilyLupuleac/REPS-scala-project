package sensors

import plant.{DateTimeProvider, HourlyDateTimeProvider}

import java.time.{LocalDate, LocalTime}

class SensorAggregator(val name: String,
                       val id: Int,
                       val sensors: List[Sensor],
                       val aggregatorFunc: (List[Int]) => Int
                      ) extends Sensor {
  override var lastReading = SensorReading(LocalTime.MIN, LocalDate.MIN, 0, id)
  override def getValue(): Int = aggregatorFunc(sensors.map(_.lastReading.value))

  override val dateTimeProvider: DateTimeProvider = new HourlyDateTimeProvider
  override val storage: SensorDataStorage = new FileSensorStorage(name + id + ".txt")
  override def hasAlerts(): Boolean = sensors.exists(_.hasAlerts())
  override def getName(): String = name
}
