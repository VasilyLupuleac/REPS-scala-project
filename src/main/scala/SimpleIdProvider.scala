class SimpleIdProvider extends IdProvider {
  import SimpleIdProvider.increment
  def nextId() = increment()
}

object SimpleIdProvider {
  private var lastId = 0
  def increment() = {
    lastId += 1
    lastId
  }
}