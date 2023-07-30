

import java.net.URLDecoder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GoogleAnalyticsService(
    val deviceService: DeviceService,
    val googleAnalyticsClient: GoogleAnalyticsClient,
) {

    @Value("\${analytics.ga4.secret.android}")
    lateinit var measurementProtocolAndroidApiSecret: String
    @Value("\${analytics.ga4.api.secret.ios}")
    lateinit var measurementProtocolIosApiSecret: String
    @Value("\${analytics.firebase.app.id.android}") lateinit var firebaseAndroidAppId: String
    @Value("\${analytics.firebase.app.id.ios}") lateinit var firebaseIosAppId: String


    companion object {
        const val INR = "INR"
    }

    fun sendEvent(analyticsRequest: AnalyticsKafkaRequest) {

        val deviceInfo = deviceService.getDeviceInfo(analyticsRequest.userId)
        var measurementProtocolApiSecret : String
        var firebaseAppId: String
        if(deviceInfo.platform == Platform.ANDROID || deviceInfo.platform == Platform.android)
        {
            measurementProtocolApiSecret = measurementProtocolAndroidApiSecret
            firebaseAppId = firebaseAndroidAppId
        }
        else
        {
            measurementProtocolApiSecret = measurementProtocolIosApiSecret
            firebaseAppId = firebaseIosAppId
        }

        val appInstanceId = deviceInfo?.deviceId?.replace("-", "")
        val eventName = analyticsRequest.event.name
        firebaseAppId = URLDecoder.decode(firebaseAppId, "UTF-8")
        var googleEvent =
            GoogleEvent(
                name = eventName,
                params =
                GoogleParams(
                    goalStatus = analyticsRequest.event.analyticsEventParams.goalStatus,
                    planName = analyticsRequest.event.analyticsEventParams.planName,
                    brandName = analyticsRequest.event.analyticsEventParams.brandName ?: "",
                    value = analyticsRequest.event.analyticsEventParams.amount,
                    currency = INR,
                    paymentType = analyticsRequest.event.analyticsEventParams.paymentType ?: "",
                    appVersion = deviceInfo?.appVersion,
                    platform = deviceInfo?.platform.toString()
                )
            )
        var googleAnalyticsEvent =
            GoogleAnalyticsEvent(
                appInstanceId = appInstanceId.toString(),
                userId = analyticsRequest.userId,
                timestampMicros = (System.currentTimeMillis() * 1000).toString(),
                nonPersonalizedAds = false,
                events = listOf(googleEvent)
            )

        googleAnalyticsClient.sendEvent(
            PathParameters(firebaseAppId, measurementProtocolApiSecret),
            googleAnalyticsEvent
        )
    }
}
