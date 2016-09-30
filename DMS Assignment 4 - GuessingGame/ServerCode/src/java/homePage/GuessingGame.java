/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homePage;

import java.util.Random;

/**
 *
 * @author Aziel Shaw - 14847095
 */
public class GuessingGame {
    private Random random;
    private int randNum;
    private int guess;
    private String response;
    private int count;
    private boolean won;
    
    public GuessingGame() {
        this.random = new Random();
        startGame();
    }
    
    public void guess(int guess) {
        if(!won) {
            if(guess > this.randNum) {
                response = "Guess was too high!\nTry again.";
                count++;
                won = false;
            } else if(guess < this.randNum) {
                response = "Guess was too low!\nTry again.";
                count++;
                won = false;
            } else {
                response = "Your guess was correct!\nPlay again?";
                won = true;
            }
        }
    }
    
    public void startGame() {
        randNum = random.nextInt(100);
        guess = -1;
        response = "Guess a Number!";
        count = 0;
        won = false;
    }
    
    public int getCount() {
        return count;
    }
    public int getRandomNumber() {
        return randNum;
    }
    public int getGuess() {
        return guess;
    }
    public String getResponse() {
        return response;
    }
    
}
