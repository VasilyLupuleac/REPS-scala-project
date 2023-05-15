object SimpleIdProvider extends IdProvider {
  private var lastId = 0
  def nextId() = {
    lastId += 1
    lastId
  }
}