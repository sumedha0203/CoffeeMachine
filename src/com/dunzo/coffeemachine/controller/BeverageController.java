package com.dunzo.coffeemachine.controller;

import com.dunzo.coffeemachine.exceptions.CustomException;
import com.dunzo.coffeemachine.models.Beverage;
import com.dunzo.coffeemachine.models.Machine;
import com.dunzo.coffeemachine.service.BeverageService;
import com.dunzo.coffeemachine.service.impl.BeverageServiceImpl;
import com.dunzo.coffeemachine.utility.ParseInputJson;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BeverageController {

    private static BeverageController instance = null;
    private Machine machine;
    private final BeverageService beverageService = new BeverageServiceImpl();

    private BeverageController() {
    }

    public static BeverageController getInstance() {
        if (instance == null) {
            instance = new BeverageController();
            instance.machine = new Machine();
        }
        return instance;
    }

    /**
     * to disperse the drinks
     *
     * @param fileLocation fileLocation
     */
    public void disperseDrinks(String fileLocation) {

        try{
            ParseInputJson parseInputJson = ParseInputJson.getInstance();
            List<Beverage> beverageList = parseInputJson.parseJsonFile(fileLocation, machine);

            if (beverageList == null || beverageList.isEmpty()) {
                throw new CustomException("Invalid/empty input.");
            }

            int usedOutlets = 0;

            for (Beverage beverage : beverageList) {
                // if used outlets are max outlets in a machine, break the loop.
                if(usedOutlets == machine.getOutlets()) {
                    System.out.println("Cannot dispense " + beverage.getName() + " as all outlets are occupied.");
                    continue;
                }
                usedOutlets = beverageService.disperseBeverage(beverage, machine) ? usedOutlets + 1: usedOutlets;
            }
            System.out.println();
            // check ingredients which are running low
            beverageService.checkLowIngredients(machine);
            System.out.println();
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File not Found. Try again...");
        } catch (IOException e) {
            System.out.println("IO exception. Please try again !!!");
        } catch (ParseException e) {
            System.out.println("Cannot Parse Json Input, invalid Json provided");
        } catch (ClassCastException e) {
            System.out.println("invalid input provided");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * to refill the ingredients
     *
     */
    public void refillIngredients() {
        Scanner sc = new Scanner(System.in);
        String input;

        do {
            System.out.println("Enter Ingredient Name to refill : ");

            try {
                String name = sc.next();

                if (!machine.getInventory().containsKey(name))
                    throw new CustomException(name + " does not exist in inventory.");

                System.out.println("Enter Ingredient Quantity to refill : ");
                long refillQuantity = Long.parseLong(sc.next());
                if (refillQuantity < 0) {
                    throw new CustomException("Refill Quantity is Negative.");
                }
                beverageService.refillIngredients(machine, name, refillQuantity);
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity.");

            } catch (CustomException e) {
                System.out.println(e.getMessage());

            } catch (Exception e) {
                System.out.println("Unknown exception.");

            }
            System.out.println("Do you want to Refill another item? Press 'Y' to continue or any other button to exit");
            input = sc.next();
        } while (input.equalsIgnoreCase("Y"));
    }

}
