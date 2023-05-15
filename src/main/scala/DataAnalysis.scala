import java.time.LocalDate
import javax.xml.transform.Result

class DataAnalysis (val readings: List[SensorReading]) {


  def filterData(filter: Int): List[SensorReading] = {
    def filterByYear(list: List[SensorReading], year: Int) = list.filter(_.date.getYear.equals(year))
    def filterByMonth(list: List[SensorReading], month: Int) = list.filter(_.date.getMonth.equals(month))
    def filterByWeek(list: List[SensorReading], week: Int) = list.filter((r) => getWeekNumber(r.date) == week)
    def filterByDay(list: List[SensorReading], day: Int) = list.filter(_.date.getDayOfMonth.equals(day))
    def filterByHour(list: List[SensorReading], hour: Int) = list.filter(_.time.getHour.equals((hour)))

    def getWeekNumber(date: LocalDate) = {
      (date.getDayOfYear + 5 + LocalDate.ofYearDay(date.getYear, 1).getDayOfWeek.getValue) / 7
    }

    val year = 1000
    val month = 1
    val week = 1
    val day = 1
    val hour = 0

    val filterOption = scala.io.StdIn.readLine()
    filterOption match {
      case "1" => filterByHour(readings, hour) //filer the data by hour
      case "2" => filterByDay(readings, day) //filer the data by day
      case "3" => filterByWeek(readings, week)//filer the data by week
      case "4" => filterByYear(readings, year) //filer the data by year
      case "5" => filterByMonth(filterByYear(readings, year), month) // filtering the data by month in a specific year
    }
  }
    def calculateMean() : Float = readings.map(_.value).sum

    def calculateMedian(): Float = ???

    def calculateMode(): Float = ???

    def calculateRange(): Float = ???

    def calculateMidrange(): Float = ???

    def dataAnalysis(result: Int) : Float = {
      val formulaOption = scala.io.StdIn.readLine()
      formulaOption match {
        case "1" => calculateMean()
        case "2" => calculateMedian()
        case "2" => calculateMode()
        case "3" => calculateRange()
        case "4" => calculateMidrange()
      }


    }


}
