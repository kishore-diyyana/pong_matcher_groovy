import matcher.pong.RedisDriver

class Collections extends spock.lang.Specification {
  def "storing and retrieving"() {
    expect:
    coll.clear()
    coll << [ foo: "bar" ]
    coll.find { it.containsKey("foo") }.foo == "bar"

    where:
    coll                                            | _
    []                                              | _
    RedisDriver.fromEnv("test_collection_groovy")   | _
  }

  def "clearing is idempotent"() {
    expect:
    coll.clear()
    coll.size() == 0
    coll.clear()
    coll.size() == 0

    where:
    coll                                            | _
    []                                              | _
    RedisDriver.fromEnv("test_collection_groovy")   | _
  }
}
