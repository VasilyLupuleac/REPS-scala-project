package analysis

import sensors.SensorReading
import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalDateTime}

object SensorDataFilters {
  implicit class ReadingToDateParts(reading: SensorReading) {
    def hour: String = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(reading.date) +
      DateTimeFormatter.ofPattern(" HH:00").format(reading.time)

    def day: String = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(reading.date)

    def month: String = DateTimeFormatter.ofPattern("yyyy-MM").format(reading.date)

    def year: String = DateTimeFormatter.ofPattern("yyyy").format(reading.date)
  }


  def monthEquals(monthNo: Int)(reading: SensorReading): Boolean =
    reading.date.getMonth.getValue == monthNo

  def yearEquals(yearNo: Int)(reading: SensorReading): Boolean =
    reading.date.getYear == yearNo

  def dayEquals(dayNo: Int)(reading: SensorReading): Boolean =
    reading.date.getDayOfMonth == dayNo

  def hourEquals(hourNo: Int)(reading: SensorReading): Boolean =
    reading.time.getHour == hourNo

  def applyFilters(filters: List[(SensorReading) => Boolean])(readings: List[SensorReading]) =
    filters.foldLeft(readings)((rs, f) => rs.filter(f))
}
