package io.jzheaux.springsecurity.resourceserverkotlin

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt

@SpringBootTest
@AutoConfigureWebTestClient
class ResourceServerKotlinApplicationTests(@Autowired val web: WebTestClient) {

	@Test
	fun getWhenJwtThenOk() {
		this.web.mutateWith(mockJwt().jwt { jwt -> jwt.subject("sub") })
				.get().uri("/")
				.exchange()
				.expectStatus().isOk;
	}

}
