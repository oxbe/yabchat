package ch.oxb.yabchat

import jakarta.ws.rs.core.Application
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Info


@OpenAPIDefinition(info = Info(title = "Yabchat API", version = "1.0"))
class ApiApplication: Application() {
}