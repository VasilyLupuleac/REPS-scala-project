trait Plant {
  val name: String
  val sensors: List[Sensor]
  def getAlertIDs(): List[Int]
}
