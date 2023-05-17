package plant

import java.time.LocalDateTime

trait DateTimeProvider {
  def getDateTime(): LocalDateTime
}
