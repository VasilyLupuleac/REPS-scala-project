trait SingleSourcePlant {
  val name: String
  val sensors: List[Sensor]
  def getAlertIDs() = sensors.filter(_.hasAlerts()).map(_.id)
}
