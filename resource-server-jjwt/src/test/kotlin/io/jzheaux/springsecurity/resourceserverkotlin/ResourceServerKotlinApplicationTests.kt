package io.jzheaux.springsecurity.resourceserverkotlin

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS256
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient


@SpringBootTest
@AutoConfigureWebTestClient
class ResourceServerKotlinApplicationTests(@Autowired val web: WebTestClient) {

	@Test
	fun getWhenJwtThenOk() {
		val jws: String = Jwts.builder().setSubject("subject")
				.signWith(HS256, "secret").compact();
		this.web.get().uri("/")
				.headers { it.setBearerAuth(jws) }
				.exchange()
				.expectStatus().isOk
	}

}
