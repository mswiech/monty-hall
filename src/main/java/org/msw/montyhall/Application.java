package org.msw.montyhall;

import org.msw.montyhall.service.MontyHallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Martin Swiech on 6.10.2016.
 */
@Component
public class Application {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MontyHallService montyHallService;

    public static void main(String[] args) {
        final ApplicationContext ctx = SpringApplication.run(Config.class, args);
        final Application application = ctx.getBean(Application.class);
        application.run();
    }

    public void run() {
        float stayOnGuessRatio = montyHallService.runMontyHallGuesses(true);
        float switchTheGuessRatio = montyHallService.runMontyHallGuesses(false);

        LOGGER.info("RESULTS: stayOnGuessRatio={}, switchTheGuessRatio={}", stayOnGuessRatio, switchTheGuessRatio);

        LOGGER.info("Application exit");
    }
}
