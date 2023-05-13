trait SingleSourcePlant {
  val name: String
  val sensors: List[Sensor]
  val health: Int
  def hasAlerts(): Boolean
}
