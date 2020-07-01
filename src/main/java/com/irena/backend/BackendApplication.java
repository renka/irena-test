package com.irena.backend;

import com.irena.backend.utils.InitUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class BackendApplication {
	private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

	@Autowired
	private InitUtil initUtil;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@PostConstruct
	private void init() {
		log.info("generating ...");
		initUtil.init();

	}
}
