package console

import ConsoleApp.{askForValue, inRange}
import analysis.DataAnalysis._
import analysis.SensorDataFilters._

import scala.io.StdIn.readLine
import sensors.SensorReading

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object ConsoleDataProcessing {
  private val dateFormat = DateTimeFormatter.ISO_LOCAL_DATE
  private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

  def processAndPrint(readings: List[SensorReading]): Unit = printFiltered(filterReadings(readings))

  def printFiltered(readings: List[SensorReading]): Unit = {
    println("Do you want to group the data?\n"
      + "1. Group and aggregate the readings\n"
      + "2. Show all readings")
    val groupingOption = readLine()

    groupingOption match {
      case "1" => {
        printStatistics(
          sortData(
            aggregateReadings(
              groupReadings(readings)).toSeq,
            (x: (String, String)) => x._1,
            (x: (String, String)) => x._2)
        )
      }
      case "2" => printAllReadings(sortData(
        readings,
        (r: SensorReading) => (dateFormat.format(r.date),
          timeFormat.format(r.time)),
        (r: SensorReading) => r.value))
      case _ => {
        println("Invalid choice. Please enter again.")
        printFiltered(readings)
      }
    }
  }

  def printReading(r: SensorReading) =
    println(s"Date: ${r.date.format(dateFormat)}, Time: ${r.time.format(timeFormat)}, Value: ${r.value}")

  def printAllReadings(readings: Seq[SensorReading]) =
    readings.foreach(printReading)

  def askDay(): Int = askForValue("Enter the day number(1-31):", inRange(1, 31))

  def askMonth(): Int = askForValue("Enter the month number (1-12):", inRange(1, 12))

  def askYear(): Int = askForValue("Enter the year:", inRange(LocalDate.MIN.getYear, LocalDate.MAX.getYear))

  def groupReadings(readings: List[SensorReading]): Map[String, List[SensorReading]] = {
    println("How do you want to group the data?\n"
      + "1. Group by hour\n"
      + "2. Group by day\n"
      + "3. Group by month\n"
      + "4. Group by year\n"
      + "Enter your selection:")
    val groupingOption = readLine()
    groupingOption match {
      case "1" => readings.groupBy(_.hour)
      case "2" => readings.groupBy(_.day)
      case "3" => readings.groupBy(_.month)
      case "4" => readings.groupBy(_.year)
      case _ => {
        println("Invalid choice. Please enter again.")
        groupReadings(readings)
      }
    }
  }

  def aggregateReadings(groupedReadings: Map[String, List[SensorReading]]): Map[String, String] = {
    println("How do you want to aggregate the data?\n"
      + "1. Calculate mean\n"
      + "2. Calculate median\n"
      + "3. Calculate mode\n"
      + "4. Calculate range\n"
      + "5. Calculate midrange\n"
      + "Enter your selection:")
    val formulaOption = readLine()

    def aggregate[A](label: String,
                     aggregatorFunc: List[SensorReading] => Option[A]): Map[String, String] =
      groupedReadings.map(x => (x._1, label + ": " + aggregatorFunc(x._2).fold("No values")(_.toString)))

    formulaOption match {
      case "1" => aggregate("Mean", _.calculateMean())
      case "2" => aggregate("Median", _.calculateMedian())
      case "3" => aggregate("Mode", _.calculateMode())
      case "4" => aggregate("Range", _.calculateRange())
      case "5" => aggregate("Midrange", _.calculateMidrange())
      case _ => {
        println("Invalid choice. Please enter again.")
        aggregateReadings(groupedReadings)
      }
    }
  }

  def printStatistics(stats: Seq[(String, String)]) {
    stats.foreach(x => println(s"Date: ${x._1} ${x._2}"))
  }

  def sortData[T, A <: Seq[T], T1, T2](data: A, sortDate: T => T1, sortVal: T => T2)
                                      (implicit ordT1: Ordering[T1],
                                       ordT2: Ordering[T2]): Seq[T] = {
    println("How do you want to sort the data?\n"
      + "1. Sort by date\n"
      + "2. Sort by value\n"
      + "3. Do not sort the data\n"
      + "Enter your selection:")
    val option = readLine()
    option match {
      case "1" => data.sortBy(sortDate)
      case "2" => data.sortBy(sortVal)
      case "3" => data
      case _ => {
        println("Invalid choice. Please enter again.")
        sortData(data, sortDate, sortVal)
      }
    }
  }

  def filterReadings(readings: List[SensorReading]): List[SensorReading] = {
    println("How do you want to filter the data?\n"
      + "1. Choose a specific day\n"
      + "2. Choose a specific month\n"
      + "3. Choose a specific year\n"
      + "4. Show all data\n"
      + "Enter your selection:")
    val filterOption = readLine()
    filterOption match {
      case "1" => {
        val year = askYear()
        val month = askMonth()
        val day = askDay()
        applyFilters(List(yearEquals(year), monthEquals(month), dayEquals(day)))(readings)
      }
      case "2" => {
        val year = askYear()
        val month = askMonth()
        applyFilters(List(yearEquals(year), monthEquals(month)))(readings)
      }
      case "3" => {
        val year = askYear()
        applyFilters(List(yearEquals(year)))(readings)
      }
      case "4" => readings
      case _ => {
        println("Invalid choice. Please enter again.")
        filterReadings(readings)
      }
    }
  }
}
