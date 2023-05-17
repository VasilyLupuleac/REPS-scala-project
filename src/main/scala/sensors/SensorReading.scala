package sensors

import java.time.{LocalDate, LocalTime}
case class SensorReading(time: LocalTime, date: LocalDate, value: Int, sensorId: Int)