package org.example;

import java.util.*;

public class WordGame extends Game {

    private Scanner scanner;
    private Set<Character> guessedLetters;
    private int maxWrongGuesses;
    private int wrongGuesses;
    private boolean gameWon;
    private String categoryName;
    private String secertWord;




    // Sanat kategotioittain

    private static final Map<String, List<String>> WORD_CATEGOIES = Map.of(
            "Ohjelminttikielet", List.of(
                    "JAVA", "PYHTON", "JAVASCRIPT", "TYPESCRIPT", "KOTLIN",
                    "SWIFT", "RUBY", "GO", "RUST", "PHP", "SCHARP"
            ),
            "Web-teknologiat", List.of(
                    "HTML", "CSS", "REACT", "ANGULAR", "VUE",
                    "NODE", "EXPRESS", "NEXT", "WEBPACK", "VITE"
            ),
            "Työkalut", List.of(
                    "DOCKER", "KUBERNETES", "LINUX", "GITHUB", "GITLAB",
                    "JENKINS", "TERRAFORM", "ANSIBLE", "NGINX", "APACHE"
            ),
            "Tietokannat", List.of(
                    "MYSQL", "POSTGRES", "MONGODB", "REDIS", "SQLITE",
                    "ORACLE", "CASSSANDRA", "ELASTICSEARCH"
            ),
            "Käsitteet", List.of(
                    "ALGORITMI", "TIETPRAKENNE", "FUNKIIO", "LUOKKA", "OBJEKIT",
                    "PERINTA", "POLYMORFISMI", "KAPSELOINTI", "ABCTRAKTIO", "RAJAPINTA"
            )
    );



    @Override
    public void initializeGame(int numberOfPlayers) {
        this.scanner = new Scanner(System.in);
        this.guessedLetters = new HashSet<>();
        this.maxWrongGuesses = 6;
        this.wrongGuesses = 0;
        this.gameWon = false;

        // Näytä kategoriat
        System.out.println("Tervetola sanapelihin!");
        System.out.println("===== SANAPELIT ====\n");
        System.out.println("Valitse kategoria:");

        int categoryIndex = 1;
        for (String category : WORD_CATEGOIES.keySet()) {
            System.out.println(categoryIndex + ". " + category);
            categoryIndex++;
        }

        int choice;
        while (true) {
            System.out.print("\nValintasi (1-" + WORD_CATEGOIES.size() + "): ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();  // Tyhjennä rivi
                if (choice >= 1 && choice <= WORD_CATEGOIES.size()) {
                    break;
                }
            } catch (Exception e) {
                scanner.nextLine();  //Tyhjennä rivi
            }
            System.out.println("Virheellinen valinta!");
        }

        // Valitse kategoria ja sana
        List<String> categories = WORD_CATEGOIES.keySet().stream().toList();
        this.categoryName = categories.get(choice - 1);
        List<String> words = WORD_CATEGOIES.get(this.categoryName);

        Random random = new Random();
        this.secertWord = words.get(random.nextInt(words.size()));

        System.out.println("\nKategoria: " + this.categoryName);
        System.out.println("Sinulla on " + maxWrongGuesses + " väärää arvausta.");
        System.out.println("Sana: " + getMaskedWord());
        System.out.println("");


    }

    @Override
    public boolean endOfGame() {
        return gameWon || wrongGuesses >= maxWrongGuesses;
    }

    @Override
    public void playSingleTurn(int player) {
        System.out.print("Anna kirjain: ");
        String input = scanner.nextLine().toUpperCase();

        if (input.isEmpty()) {
            System.out.println("Syötä kirjain!");
            return;
        }

        char letter = input.charAt(0);

        if (guessedLetters.contains(letter)) {
            System.out.println("Olet jo arvannut tämän kirjaimen!");
            return;
        }

        guessedLetters.add(letter);

        if (secertWord.indexOf(letter) >= 0) {
            System.out.println("Oikein kirjain '" + letter + "' on sanassa. ");
        } else {
            wrongGuesses++;
            System.out.println("Väärin! Kirjainta '" + letter + "' ei ole sanassa.");
            System.out.println("Väärät arvaukset: " + wrongGuesses + "/" + maxWrongGuesses);
        }

        gameWon = isWordGuessed();
        System.out.println("Sana: " + getMaskedWord());
        System.out.println();
    }

    @Override
    public void displayWinner() {
        if (gameWon) {
            System.out.println("VOITIT!");
            System.out.println("Sana oli :" + secertWord + " (Kategoria: " + categoryName + ")");
            System.out.println("Arvatut kirjaimet: " + guessedLetters);
            System.out.println("Väärät arvaukset: "+ wrongGuesses + "/" + maxWrongGuesses);
        } else  {
            System.out.println("Peli päättyi");
            System.out.println("Sana oli: " + secertWord + " (Kategoria: " + categoryName + ")");
            System.out.println("Arvatut kirjaimet: " + guessedLetters);
        }
        scanner.close();
    }


    private String getMaskedWord() {
        StringBuilder masked = new StringBuilder();
        for (char c : this.secertWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                masked.append(c);
            } else  {
                masked.append("_");
            }
            masked.append(" ");
        }
        return masked.toString().trim();
    }

    private boolean isWordGuessed() {
        for (char c : secertWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }
}






/*
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
*/