import scala.math.{max, min}

object DataAnalysis {
  implicit class calculateStatistics(readings: List[SensorReading]) {
    val values = readings.map(_.value)
    def calculateMean(): Option[Double] = readings match {
      case Nil => None
      case _ => Option(1.0 * values.sum / values.length)
    }
    def calculateMedian(): Option[Int] = values.sorted.drop(values.length / 2).headOption
    def calculateMode(): Option[Int] = readings match {
      case Nil => None
      case _ => Option(values.groupBy(x => x).reduce((a, b) =>
        if (a._2.length < b._2.length) b else a)._1)
    }
    def calculateRange(): Option[(Int, Int)] = readings match {
      case Nil => None
      case _ => Option((
        values.reduceLeft(min),
        values.reduceLeft(max)
        ))
    }
    def calculateMidrange(): Option[Double] = {
      val range = calculateRange()
      range.map(x => (x._1 + x._2) / 2.0)
    }
  }

}

