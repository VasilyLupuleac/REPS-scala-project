case class SimpleIdProvider() extends IdProvider {
  private var lastId = 0

  override def nextId(): Int = {
    lastId += 1
    lastId
  }
}
