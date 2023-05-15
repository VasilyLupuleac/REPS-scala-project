import java.io.{BufferedWriter, FileWriter}
import java.time.{LocalDate, LocalDateTime, LocalTime}
import java.time.format.DateTimeFormatter
import scala.io.Source


class FileSensorStorage(val filename: String) extends SensorDataStorage {
  def toString(reading: SensorReading): String = s"${reading.time.format(DateTimeFormatter.ISO_LOCAL_TIME)} ${reading.date.format(DateTimeFormatter.ISO_LOCAL_DATE)} ${reading.value} ${reading.sensorId} "

  def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: Exception => None
    }
  }

    private def fromString(line: String): Option[SensorReading] = {
      val fields = line.split(" ")
      val time = LocalTime.parse(fields(0), DateTimeFormatter.ISO_LOCAL_TIME)
      val date = LocalDate.parse(fields(1), DateTimeFormatter.ISO_LOCAL_DATE)
      val value = toInt(fields(2)).getOrElse(return None)
      val sensorId = toInt(fields(3)).getOrElse(return None)
      Some(SensorReading(time = time,date = date, value = value, sensorId = sensorId))
    }


  override def addReading(entry: SensorReading): Unit = {
    val writer = new BufferedWriter(new FileWriter(filename, true))
    try {
      writer.write(toString(entry))
      writer.newLine()
    } finally {
      writer.close()
    }
  }
    override def getAllReadings(): List[SensorReading] = {
      val source = Source.fromFile(filename)
      try {
        val lines = source.getLines().toList
        lines.flatMap(fromString)
      } finally {
        source.close()
      }
    }
}
