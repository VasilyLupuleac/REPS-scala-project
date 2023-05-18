package sensors

import java.io.{BufferedWriter, FileWriter}
import java.time.{LocalDate, LocalTime}
import java.time.format.DateTimeFormatter
import scala.io.Source


class FileSensorStorage(val filename: String) extends SensorDataStorage {

  private val dateFormat = DateTimeFormatter.ISO_LOCAL_DATE
  private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
  implicit class ReadingToString(reading: SensorReading) {
    def convertToString() = s"${reading.time.format(timeFormat)} " +
      s"${reading.date.format(dateFormat)} " +
      s"${reading.value} ${reading.sensorId}"
  }

  private def stringToReadingOption(line: String): Option[SensorReading] = {
    val fields = line.split(" ")
    val time = try {
      LocalTime.parse(fields(0), timeFormat)
    } catch {
      case e: Exception => return None
    }
    val date = try {
      LocalDate.parse(fields(1), dateFormat)
    }
    catch {
      case e: Exception => return None
    }

    val value = fields(2).toIntOption.getOrElse(return None)
    val sensorId = fields(3).toIntOption.getOrElse(return None)
    Option(SensorReading(time = time, date = date, value = value, sensorId = sensorId))
  }


  override def addReading(entry: SensorReading): Unit = {
    val writer = new BufferedWriter(new FileWriter(filename, true))
    try {
      writer.write(entry.convertToString())
      writer.newLine()
    } finally {
      writer.close()
    }
  }

  override def getAllReadings(): List[SensorReading] = {
    val source = Source.fromFile(filename)
    try {
      val lines = source.getLines().toList
      lines.flatMap(stringToReadingOption)
    } finally {
      source.close()
    }
  }
}
