package systemClass;

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileReader;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.util.ArrayList;  
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;  
import java.util.Scanner;

import exception.IndexOutOfRangeException;  

public class SysConfiguration {
	private SysConfiguration() {}

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static String filePath = "system" + FILE_SEPARATOR + "SysConfiguration.txt";

    public static String getSysConfig(String configName) {
        File file = new File(filePath);
        String result = "";

        try (FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                String key = parts[0].trim();
                String value = parts[1].trim();

                if (key.equals(configName)) {
                    String configValue = value;
                    result = configValue;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void setSysConfig(String configName, Integer configValue) {
        File file = new File(filePath);

        Map<String, String> keyValueMap = new HashMap<>();

        try (FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                String key = parts[0].trim();
                String value = parts[1].trim();
                keyValueMap.put(key, value);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // if Name exist then replace, if not then add
        keyValueMap.put(configName, Integer.toString(configValue));

        // write the map into the file
        try (FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            for (Map.Entry<String, String> entry : keyValueMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                bufferedWriter.write(key + ": " + value);
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> getSysConfigs() {
        File file = new File(filePath);
        
        ArrayList<String> keys = new ArrayList<>();
        System.out.println();
        try (FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            int i = 1;
            System.out.println("+-------+-------------+-------+");
            System.out.println("| Index | Setting     | Value |");
            System.out.println("+-------+-------------+-------+  ");
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                String key = parts[0].trim();
                keys.add(key);
                String value = parts[1].trim();
                System.out.println("|   " + i + "   | " + key + " |   " + value + "   |  ");
                i++;
            }
            System.out.println("+-------+-------------+-------+  ");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return keys;
    }

    public static void changeSysConfig() {
        ArrayList<String> keys = getSysConfigs();
        System.out.println("Which setting would you like to change the quantity for?");
        boolean validInputKey = false;
        while (!validInputKey) {
            System.out.print("Enter the corresponding index number or enter 0 to exit: ");
            Scanner scanner = ProjectScanner.getInstance();
            try{
                int inputKey = scanner.nextInt();
                if (inputKey > keys.size() || inputKey < 0) {
                    throw new IndexOutOfRangeException(1, keys.size());
                } else if (inputKey == 0) {
                    validInputKey = true;
                } else {
                    validInputKey = true;
                    System.out.println("What value do you want to change to? ");
                    boolean validInputValue = false;
                    Integer inputValue = 0;
                    while(!validInputValue)
                    {
                        try{
                            inputValue = scanner.nextInt();
                            if (inputValue < 0)
                            System.out.println("Please enter a non-negative integer! ");
                            else validInputValue = true;
                        }
                        catch(InputMismatchException e)
                        {
                            System.out.println("Please enter a valid integer! ");
                            scanner.next();
                        }
                    }
                    setSysConfig(keys.get(inputKey - 1), inputValue);
                    System.out.println("Change Successfully! Change " + keys.get(inputKey - 1) + " to " + inputValue);
                }
            }
            catch (IndexOutOfRangeException e1){
                System.out.println(e1.getMessage());
            }
            catch (InputMismatchException e2){
                System.out.println("Please enter a valid integer! ");
                scanner.next();
            }
        }
    }
}
