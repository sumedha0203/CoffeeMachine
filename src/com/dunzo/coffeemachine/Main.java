package com.dunzo.coffeemachine;

import com.dunzo.coffeemachine.controller.BeverageController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BeverageController coffeeMachine = BeverageController.getInstance();
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Press D to disperse/ R to refill/ any other key to exit");
            String input = sc.next();
            if (input.equalsIgnoreCase("D")) {
                System.out.println("Please enter the input file location");
                String fileLocation = sc.next();
                disperseDrinks(fileLocation, coffeeMachine);
            } else if (input.equalsIgnoreCase("R")) {
                refillIngredients(coffeeMachine);
            } else {
                System.out.println("Exiting.");
                System.out.println("THANK YOU !");
                break;
            }
        }
        sc.close();
    }

    /**
     * to disperse the drinks as per input file
     *
     * @param fileLocation  fileLocation
     * @param coffeeMachine coffeeMachine
     */
    private static void disperseDrinks(String fileLocation, BeverageController coffeeMachine) {
        coffeeMachine.disperseDrinks(fileLocation);
    }


    /**
     * to ask the user if she/he wants to refill any ingredients
     *
     * @param coffeeMachine coffeeMachine
     */
    private static void refillIngredients(BeverageController coffeeMachine) {
        coffeeMachine.refillIngredients();
    }
}
