import java.time.{LocalDate, LocalTime}

trait DateTimeProvider {
  def getTime(): LocalTime
  def getDate(): LocalDate
}
