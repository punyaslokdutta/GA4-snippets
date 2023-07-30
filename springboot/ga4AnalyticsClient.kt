
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.SpringQueryMap
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(
    value = "googleAnalytics-client",
    configuration = [FeignClientConfig::class],
    url = "\${analytics.ga4.url}"
)
interface GoogleAnalyticsClient {
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/mp/collect"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        headers = ["Content-type=application/json"],
    )
    fun sendEvent(
        @SpringQueryMap(encoded = true) params: PathParameters,  // sending path params in SpringQueryMap since directly utf-8 encoding is not supported in google analytics
        @RequestBody googleAnalyticsEvent: GoogleAnalyticsEvent,
    )
}

class PathParameters(val firebase_app_id: String, val api_secret: String)
