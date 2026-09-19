package org.acme.fundamentals;

import lombok.extern.slf4j.Slf4j;
import org.acme.fundamentals.monadicComposition.MonadicCompositionNumbers;
import org.acme.fundamentals.monadicComposition.MonadicLawsOptional;
import org.acme.fundamentals.monadicComposition.ex1to5.MonadicCompositionExercises1to5;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class FundamentalsApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FundamentalsApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("\n\nRunning Java fundamentals lab...\n");

		new MonadicCompositionExercises1to5().run();
		new MonadicCompositionNumbers().run();
		new MonadicLawsOptional().run();

		log.info("\n\nEnd of Java fundamentals lab...");
	}

}
