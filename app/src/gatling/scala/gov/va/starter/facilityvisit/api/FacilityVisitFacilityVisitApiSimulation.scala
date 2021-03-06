package gov.va.starter.facilityvisit.api

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt
import scala.concurrent.duration._


class FacilityVisitFacilityVisitApiSimulation extends Simulation {

  val httpProtocol = http
    .warmUp("http://localhost:8081/actuator/health")
    .baseUrl("http://localhost:8080") // Here is the root for all relative URLs
    .acceptHeader("application/json") // Here are the common headers

  val scn = scenario("API Endpoint Simulation")
    .exec(http("Create FacilityVisit")
      .post("/v1/facilityvisit/facilityvisits")
      .body(StringBody("""{"facilityId":"653", "type": "987-65-4321", "visitorIcn": "Mary", "visitedAt":"Contrary"}"""))
      .asJson
      .check(jsonPath("$.id").saveAs("userId")))
    .exec(http("Get FacilityVisit")
      .get("/v1/facilityvisit/facilityvisits/${userId}")) // 92534752-a39c-499c-aa13-528cd0143f7c

  setUp(scn
    .inject(
      atOnceUsers(10),
      rampUsersPerSec(10) to 100 during (2.minutes))
    .protocols(httpProtocol))
}
