import java.util.Scanner;

public class HangmanGame {
    public static void main(String[] args) {
        String[] words = {"happy", "cake", "fun", "computer", "flowers", "motivation"};
        String wordToGuess = pickRandomWord(words).toLowerCase();
        char[] guessedLetters = new char[wordToGuess.length()];
        int incorrectAttempts = 0;
        int maxIncorrectAttempts = 6;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");
        System.out.println("Try to guess the word.");

        while (incorrectAttempts < maxIncorrectAttempts && !isWordGuessed(guessedLetters)) {
            displayWord(wordToGuess, guessedLetters);
            System.out.println("Incorrect Attempts: " + incorrectAttempts + "/" + maxIncorrectAttempts);
            System.out.print("Enter a letter: ");

            char guessedLetter = scanner.next().toLowerCase().charAt(0);

            if (isLetterInWord(guessedLetter, wordToGuess, guessedLetters)) {
                System.out.println("Good guess!");
            } else {
                System.out.println("Incorrect guess. Try again.");
                incorrectAttempts++;
            }
        }

        if (isWordGuessed(guessedLetters)) {
            System.out.println("Congratulations! You guessed the word: " + wordToGuess);
        } else {
            System.out.println("Sorry, you ran out of attempts. The correct word was: " + wordToGuess);
        }

        scanner.close();
    }

    private static String pickRandomWord(String[] words) {
        int randomIndex = (int) (Math.random() * words.length);
        return words[randomIndex];
    }

    private static void displayWord(String word, char[] guessedLetters) {
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (isLetterGuessed(letter, guessedLetters)) {
                System.out.print(letter + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    private static boolean isLetterInWord(char letter, String word, char[] guessedLetters) {
        boolean isLetterInWord = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                guessedLetters[i] = letter;
                isLetterInWord = true;
            }
        }

        return isLetterInWord;
    }

    private static boolean isLetterGuessed(char letter, char[] guessedLetters) {
        for (char guessedLetter : guessedLetters) {
            if (guessedLetter == letter) {
                return true;
            }
        }
        return false;
    }

    private static boolean isWordGuessed(char[] guessedLetters) {
        for (char letter : guessedLetters) {
            if (letter == 0) {
                return false;
            }
        }
        return true;
    }
}
