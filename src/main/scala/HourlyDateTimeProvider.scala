import java.time.LocalDateTime

class HourlyDateTimeProvider() extends DateTimeProvider {
  private var currentDateTime: LocalDateTime = LocalDateTime.of(2023, 5, 14, 12, 0)
  override def getDateTime(): LocalDateTime = {
    val dt = currentDateTime
    currentDateTime = currentDateTime.plusHours(1)
    dt
  }
}
