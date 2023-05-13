trait SingleSourcePlant {
  val name: String
  val sensors: List[Sensor]
  val health: Int
  def getAlertIDs() = sensors.filter(_.hasAlerts()).map(_.id)
}
