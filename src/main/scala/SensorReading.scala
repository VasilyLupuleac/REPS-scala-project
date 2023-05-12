import java.time.{LocalDate, LocalTime}

// Represents a reading from a sensor
case class SensorReading(time: LocalTime, date: LocalDate, value: Int)
