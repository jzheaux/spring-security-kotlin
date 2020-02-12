package io.jzheaux.springsecurity.resourceserverkotlin

import io.jsonwebtoken.Jwts
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@SpringBootApplication
class ResourceServerKotlinApplication {
	@Bean
	fun jwtDecoder() : ReactiveJwtDecoder {
		val parser = Jwts.parser().setSigningKey("secret")
		return ReactiveJwtDecoder { token ->
			val jws = parser.parseClaimsJws(token)
			Mono.just(Jwt.withTokenValue(token)
					.headers { it.putAll(jws.header) }
					.claims { it.putAll(jws.body) }
					.build())
		}
	}
}

@RestController
class ResourceServerController {
	@GetMapping("/")
	fun index(): String {
		return "index"
	}
}


fun main(args: Array<String>) {
	runApplication<ResourceServerKotlinApplication>(*args)
}
