import java.time.{LocalDate, LocalTime}

// Represents a reading from a sensor
case class Reading(time: LocalTime, date: LocalDate, value: Int)
