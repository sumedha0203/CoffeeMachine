package com.dunzo.coffeemachine.utility;

import com.dunzo.coffeemachine.exceptions.CustomException;
import com.dunzo.coffeemachine.models.Beverage;
import com.dunzo.coffeemachine.models.Ingredient;
import com.dunzo.coffeemachine.models.Machine;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseInputJson {

    private static ParseInputJson instance = null;
    private ParseInputJson() {}

    public static ParseInputJson getInstance() {
        if(instance == null)
            instance = new ParseInputJson();
        return instance;
    }

    static final String MACHINE = "machine";
    static final String OUTLETS = "outlets";
    static final String NO_OF_OUTLETS = "count_n";
    static final String TOTAL_ITEMS_QUANTITY = "total_items_quantity";
    static final String BEVERAGES = "beverages";

    /**
     * to parse the input file
     * @param fileLocation fileLocation
     * @param machineObj machineObj
     * @return List<Beverage>
     * @throws IOException IOException
     * @throws ParseException ParseException
     */
    public List<Beverage> parseJsonFile(String fileLocation, Machine machineObj) throws IOException, ParseException, CustomException, ClassCastException {

        Object obj = new JSONParser().parse(new FileReader(fileLocation));
        if(obj == null)
            throw new CustomException("Invalid/empty input.");
        // typecasting obj to JSONObject
        JSONObject jsonObject = (JSONObject) obj;
        if(jsonObject.isEmpty())
            throw new CustomException("Invalid/empty input.");

        // getting Machine
        Map machine = (Map) jsonObject.get(MACHINE);
        if(machine == null || machine.isEmpty())
            throw new CustomException("no machine found.");

        // getting outlets
        Map<String, Long> outlets = (Map) machine.get(OUTLETS);
        if(outlets == null || outlets.isEmpty())
            throw new CustomException("no outlet found.");

        if(outlets.get(NO_OF_OUTLETS) == null)
            throw new CustomException("no outlet count found.");

        machineObj.setOutlets((long) outlets.get(NO_OF_OUTLETS));

        // parse and initialize inventory
        Map<String,Long> inventoryItems = (Map) machine.get(TOTAL_ITEMS_QUANTITY);
        Map<String, Long> inventory = initializeInventory(inventoryItems);
        machineObj.setInventory(inventory);

        // parse beverages
        Map<String,Map<String,Long>> beverages = (Map<String, Map<String, Long>>) machine.get(BEVERAGES);
        return parseBeverages(beverages);
    }

    /**
     * to parse input beverages
     * @param beverages beverages
     * @return List<Beverage>
     * @throws CustomException CustomException
     */
    private List<Beverage> parseBeverages(Map<String, Map<String, Long>> beverages) throws CustomException {
        if(beverages == null || beverages.isEmpty()) {
            throw new CustomException("no beverages found.");
        }

        List<Beverage> beverageList = new ArrayList<>();
        // iterate beverages from input

        for (Map.Entry<String, Map<String, Long>> beverage : beverages.entrySet()) {
            List<Ingredient> ingredients = new ArrayList<>(); // ingredients list for a particular drink

            String drink = beverage.getKey(); // drink name eg. hot_tea

            Map<String, Long> ingredientMap = beverage.getValue(); // map of ingredients of a drink in input

            if (ingredientMap == null || ingredientMap.isEmpty())
                throw new CustomException("no ingredients found for the beverage.");

            for (Map.Entry<String, Long> pair : ingredientMap.entrySet()) {
                String name = pair.getKey(); // ingredient name eg. hot_water
                long quantity = pair.getValue(); // quantity of ingredient eg. 100

                Ingredient ing = new Ingredient(name, quantity);
                ingredients.add(ing);
            }

            beverageList.add(new Beverage(drink, ingredients));
        }
        return beverageList;
    }

    /**
     * to parse and initialize inventory from input
     * @param inventoryItems inventoryItems
     * @return Map<String, Long>
     * @throws CustomException CustomException
     */
    private Map<String, Long> initializeInventory(Map<String, Long> inventoryItems) throws CustomException {
        if(inventoryItems == null || inventoryItems.isEmpty()) {
            throw new CustomException("no inventory found.");
        }
        Map<String, Long> inventory = new HashMap<>();
        for (Map.Entry<String, Long> item : inventoryItems.entrySet()) {
            String name = item.getKey();
            long quantity = item.getValue();
            inventory.put(name, quantity);
        }
        return inventory;
    }


}
