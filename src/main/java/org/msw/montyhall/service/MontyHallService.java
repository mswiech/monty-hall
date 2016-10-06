package org.msw.montyhall.service;

/**
 * Created by Martin Swiech on 6.10.2016.
 */
public interface MontyHallService {
    float runMontyHallGuesses(boolean stayOnGuess);
    boolean runMontyHallGuess(boolean stayOnGuess, int guessIndex);
}
