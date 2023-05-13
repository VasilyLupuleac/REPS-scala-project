trait SingleSourcePlant extends Plant {
  override def getAlertIDs() = sensors.filter(_.hasAlerts()).map(_.id)
}
