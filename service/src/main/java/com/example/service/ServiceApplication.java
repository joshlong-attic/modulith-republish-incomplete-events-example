package com.example.service;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

	/*@Bean
	ApplicationRunner applicationRunner (IncompleteEventPublications publications){
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				publications.resubmitIncompletePublications(new Predicate<EventPublication>() {
					@Override
					public boolean test(EventPublication eventPublication) {
						return eventPublication.getEvent();
					}
				});
			}
		} ;
	}*/

    @Bean
    ApplicationRunner demo() {
        return args -> {
            var es = Executors.newVirtualThreadPerTaskExecutor();

            // professor José Paumard
            // Java developer advocate chez Oracle

            var threads = new ArrayList<Thread>();
            var names = new ConcurrentSkipListSet<String>();
            for (var i = 0; i < 1_000; i++) {
                var first = i == 0;
                var thread = Thread.ofVirtual().unstarted(() -> {

                    if (first) names.add(Thread.currentThread().toString());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                    if (first) names.add(Thread.currentThread().toString());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                    if (first) names.add(Thread.currentThread().toString());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                    if (first) names.add(Thread.currentThread().toString());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                });
                threads.add(thread);

            }

            for (var th : threads) th.start();

            for (var th : threads) th.join();

            System.out.println(names);


        };
    }

}

