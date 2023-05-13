class DataAnalysis (val readings: List[SensorReading]) {


  def filterData(filter: Int): List[SensorReading] = {
    def filterByYear(list: List[SensorReading], year: Int) = list.filter(_.date.getYear.equals(year))

    def filterByMonth(list: List[SensorReading], month: Int) = list.filter(_.date.getMonth.equals(month))

    val year = 1000
    val month = 1
    val filter = scala.io.StdIn.readLine()
    filter match {
      case 1 => ??? //filer the data by hour
      case 2 => ??? //filer the data by day
      case 2 => ??? //filer the data by week
      case 3 => filterByYear(readings, year) //filer the data by year
      case 4 => {
        filterByMonth(filterByYear(readings, year), month) // filtering the data by month in a specific year
      }
    }
  }
    def calculateMean() : Float = readings.map(_.value).sum

    def calculateMedian(): Float = ???

    def calculateMode(): Float = ???
    def calculateRange(): Float = ???

    def calculateMidrange(): Float = ???

    def dataAnalysis(formula: Int) : Float = {
      val formula = scala.io.StdIn.readLine()
      formula match {
        case 1 => calculateMean()
        case 2 => calculateMedian()
        case 2 => calculateMode()
        case 3 => calculateRange()
        case 4 => calculateMidrange()
      }


    }


}
