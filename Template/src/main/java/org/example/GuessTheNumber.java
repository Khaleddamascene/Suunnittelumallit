package org.example;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber extends Game {

    private int numberOfPlayers;
    private int secretNumber;
    private boolean gameOver;
    private int winner;
    private Scanner scanner;



    @Override
    public void initializeGame(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.scanner = new Scanner(System.in);

        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;

        gameOver = false;
        winner = -1;

        System.out.println("===== Guess The game =====");
        System.out.println("The a number is between 1 and 100");
        System.out.println("There are " + numberOfPlayers + " players in the game." );
        System.out.println();



    }

    @Override
    public boolean endOfGame() {
        return gameOver;
    }

    @Override
    public void playSingleTurn(int player) {
        System.out.print("Player " + (player + 1) + ", enter your guess: ");

        int guess = scanner.nextInt();

        if (guess < 1 || guess > 100) {
            System.out.println("Please enter a number between 1 and 100");
            System.out.println();
            return;
        }

        if (guess == secretNumber) {
            System.out.println("Correct!");
            System.out.println("Player " + (player + 1) + " wins!");
            winner = player;
            gameOver = true;
        } else if (guess < secretNumber) {
            System.out.println("The number is higher");
        } else {
            System.out.println("The number is lower");
        }
        System.out.println();
    }

    @Override
    public void displayWinner() {
        System.out.println("==== Game Over ====");

        System.out.println("Player " + (winner + 1) + "is the winner!");
    }
}
