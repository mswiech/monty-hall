package org.msw.montyhall.service.impl;

import org.apache.commons.lang.math.RandomUtils;
import org.msw.montyhall.service.MontyHallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Martin Swiech on 6.10.2016.
 */
@Component
public class MontyHallServiceImpl implements MontyHallService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${doors}")
    private Integer doors;

    @Value("${guesses}")
    private Integer guesses;

    @Override
    public float runMontyHallGuesses(final boolean stayOnGuess) {
        LOGGER.info("Running case {}: doors={}, guesses={}", stayOnGuess ? "STAY ON GUESS" : "SWITCH GUESS", doors, guesses);
        int wins = 0;
        for (int i = 0; i < guesses; i++) {
            final boolean win = runMontyHallGuess(stayOnGuess, i);
            if (win) {
                wins++;
            }
        }
        return ((float) wins / guesses.floatValue());
    }

    @Override
    public boolean runMontyHallGuess(final boolean stayOnGuess, final int guessIndex) {
        final int winDoor = RandomUtils.nextInt(doors) + 1;
        final int yourFirstChoice = RandomUtils.nextInt(doors) + 1;
        final int moderatorChoice = winDoor == yourFirstChoice ? randomDoorExceptOf(winDoor) : winDoor;

        final int yourFinalChoice = stayOnGuess ? yourFirstChoice : moderatorChoice;
        final boolean win = yourFinalChoice == winDoor;

        LOGGER.info("   guess {}: doors={}, yourFirstChoice={}, moderatorOpenedAllExceptOfYourFirstChoiceAnd={}, yourFinalChoice={}, win={}",
                guessIndex + 1, doors, yourFirstChoice, moderatorChoice, yourFinalChoice, win);
        return win;
    }

    private int randomDoorExceptOf(int exclusion) {
        final int result = RandomUtils.nextInt(doors - 1) + 1;
        if (result >= exclusion) {
            return result + 1;
        }
        return result;
    }
}
