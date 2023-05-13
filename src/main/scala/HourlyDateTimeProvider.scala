import java.time.LocalDateTime

class HourlyDateTimeProvider(var currentDateTime: LocalDateTime) extends DateTimeProvider {
  override def getDateTime(): LocalDateTime = {
    val dt = currentDateTime
    currentDateTime = currentDateTime.plusHours(1)
    dt
  }
}
