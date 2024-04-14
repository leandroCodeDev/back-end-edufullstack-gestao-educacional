package com.api.edufullstackgestaoeducacional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class EdufullstackGestaoEducacionalApplication {

	public static void main(String[] args) {
		log.info("inicia a aplicação");
		SpringApplication.run(EdufullstackGestaoEducacionalApplication.class, args);
	}

}
