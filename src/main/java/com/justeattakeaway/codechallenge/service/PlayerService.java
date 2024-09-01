package com.justeattakeaway.codechallenge.service;

import com.justeattakeaway.codechallenge.kafka.producer.PlayerProducer;
import com.justeattakeaway.codechallenge.kafka.producer.WinnerProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private static boolean manualInput;

    @Value("${player.current}")
    private String currentPlayer;
    @Value("${player.opponent}")
    private String opponentPlayer;

    private final PlayerProducer playerProducer;
    private final WinnerProducer winnerProducer;

    public void startGame() {
        System.out.println(opponentPlayer + " wants to play, type Yes to continue!");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if ("Yes".equalsIgnoreCase(input)) {
            System.out.println("To continue with manual input type M and press Enter, for automatic play press Enter.");
            input = scanner.nextLine();
            if ("M".equalsIgnoreCase(input)) {
                manualInput = true;
            }

            int randomNumber = generateRandomNumber();
            System.out.println(currentPlayer + " starts the game with number: " + randomNumber);
            sendNumberToKafka(randomNumber);
        }
    }

    public int generateRandomNumber() {
        return new Random().nextInt(100) + 1;
    }

    public void processNumber(String message) {
        int number = Integer.parseInt(message);
        System.out.println(currentPlayer + " received number: " + number);
        if (manualInput) {
            processNumberManually(number);
        } else {
            processNumberAutomatically(number);
        }
    }

    public void processNumberAutomatically(int number) {
        int modifiedNumber = modifyNumberToBeDivisibleBy3Automatically(number);
        processDividedNumber(modifiedNumber);
    }

    public int modifyNumberToBeDivisibleBy3Automatically(int number) {
        if (number % 3 == 0) {
            System.out.println(currentPlayer + " added: 0");
            return number;
        } else if ((number + 1) % 3 == 0) {
            System.out.println(currentPlayer + " added: +1");
            return number + 1;
        } else {
            System.out.println(currentPlayer + " added: -1");
            return number - 1;
        }
    }

    public void processNumberManually(int number) {
        int modifiedNumber = modifyNumberToBeDivisibleBy3Manually(number);
        processDividedNumber(modifiedNumber);
    }

    public void processDividedNumber(int modifiedNumber) {
        int dividedNumber = modifiedNumber / 3;
        if (dividedNumber == 1) {
            String winnerMessage = currentPlayer + " wins the game!";
            System.out.println(winnerMessage);
            winnerProducer.send(winnerMessage);
            manualInput = false;
            return;
        }
        sendNumberToKafka(dividedNumber);
    }

    public int modifyNumberToBeDivisibleBy3Manually(int number) {
        Scanner scanner = new Scanner(System.in);
        int input;

        while (true) {
            System.out.println("Please type 1, -1 or 0 to get a number divided by 3");
            try {
                input = Integer.parseInt(scanner.next());

                if (input == 0 && number % 3 == 0) {
                    System.out.println(currentPlayer + " added: 0");
                    return number + input;
                } else if (input == 1 && (number + 1) % 3 == 0) {
                    System.out.println(currentPlayer + " added: +1");
                    return number + input;
                } else if (input == -1 && (number - 1) % 3 == 0) {
                    System.out.println(currentPlayer + " added: -1");
                    return number + input;
                } else {
                    System.out.println(number + input + " could not be divided by 3!");
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Please enter only 1, -1 or 0!!!");
            }
        }
    }

    public void sendNumberToKafka(int number) {
        System.out.println(currentPlayer + " sent number: " + number);
        playerProducer.send(String.valueOf(number));
    }

    public void printWinner(String message) {
        System.out.println(message);
        manualInput = false;
    }
}
