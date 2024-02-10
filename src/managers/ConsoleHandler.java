package managers;

import exceptions.*;
import model.*;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleHandler {
    private final Scanner scanner;
    private final CommandManager commandManager;
    public ConsoleHandler(Scanner scanner, CommandManager commandManager) {
        this.scanner = scanner;
        this.commandManager = commandManager;

    }

    public String collectionFilenameRequest() {
        System.out.print("Введите путь к файлу коллекции: ");
        return scanner.nextLine();
    }

    public static class ScriptHandler {
        public static void readCommands(String filename, CommandManager commandManager) throws IOException, WrongParameterException, IncorrectFilenameException, ElementNotFoundException, CommandNotExistsException, NullUserRequestException {
            String[] commands = readScript(filename);
            commandManager.processFileCommands(commands);
        }

        private static String[] readScript(String filename) throws WrongParameterException {
            try {
                List<String> commands = new ArrayList<>();
                File file = new File(filename);
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    commands.add(line);
                }
                return commands.toArray(new String[0]);
            } catch (IOException e) {
                throw new WrongParameterException("Файл не найден или нет доступа к нему.");
            }

        }
    }

    public void listen() throws CommandNotExistsException, IncorrectFilenameException, ElementNotFoundException, WrongParameterException {
        try {
            while (true) {
                System.out.print(">>> ");
                String request = scanner.nextLine();
                commandManager.exec(request);
            }
        } catch (CommandNotExistsException | ElementNotFoundException | WrongParameterException |
                 IncorrectFilenameException | NullUserRequestException e) {
            printError(e.toString());
            listen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public String askName() {
        String response;
        System.out.print("Введите имя организации: ");
        response = scanner.nextLine();
        //валидация
        return response;
    }

    public Coordinates askCoordinates() {
        Coordinates response;
        System.out.print("Введите через пробел координаты x и y (числа целые): ");
        String str = scanner.nextLine();
        // валидация
        int x = Integer.parseInt(str.split(" ")[0]);
        long y = Long.parseLong(str.split(" ")[1]);
        response = new Coordinates(x,y);
        return response;
    }

    public long askAnnualTurnover() {
        long response;
        System.out.print("Введите годовой оборот компании (целое число): ");
        String str  = scanner.nextLine();
        // валидация
        response = Long.parseLong(str);
        return response;
    }

    public int askEmployeesCount() {
        int response;
        System.out.print("Введите количество сотрудников: ");
        String str = scanner.nextLine();
        // валидация
        response = Integer.parseInt(str);
        return response;
    }

    public String askOrganizationType() {
        System.out.println("Введите номер типа Вашей организации: ");
        for (OrganizationType type : OrganizationType.values()) {
            System.out.println(type.ordinal() + 1 + ") " + type.name());
        }
        String response = scanner.nextLine();
        return response;
    }

    public Address askOfficialAddress() {
        Address response;
        System.out.print("Введите город(?): ");
        String zipCode = scanner.nextLine();
        // валидация
        System.out.print("Введите координаты локации x, y, z через пробел (x и y - вещественные, z - целое): ");
        String loc = scanner.nextLine();
        // валидация
        double x = Double.parseDouble(loc.split(" ")[0]);
        double y = Double.parseDouble(loc.split(" ")[1]);
        long z = Long.parseLong(loc.split(" ")[2]);
        response = new Address(zipCode, new Location(x,y,z));
        return response;
    }

    public String askWhatToChange() {
        System.out.println("Выберите, что Вы хотите поменять и укажите соответствующие номера характеристик через пробел: ");
        Field[] fields = Organization.class.getDeclaredFields();
        List<Field> filteredFields = new ArrayList<>();

        for (Field field : fields) {
            if (!field.getName().equals("id") && !field.getName().equals("creationDate")) {
                filteredFields.add(field);
            }
        }
        Field[] resultingArray = filteredFields.toArray(new Field[0]);
        for (int i = 1; i <= resultingArray.length; i++) {
            System.out.println(i + ") " + resultingArray[i-1].getName());
        }
        return scanner.nextLine();
    }

    public void print(String str) {
        System.out.println(str);
    }

    public void printAdvice(String advice) {
        System.out.println("Совет: " + advice);
    }

    public void printError(String message) {
        System.out.println("Ошибка: " + message);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

}