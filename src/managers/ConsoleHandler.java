package managers;

import commands.Add;
import commands.Command;
import commands.Help;
import exceptions.CommandNotExistsException;
import exceptions.ElementNotFoundException;
import exceptions.IncorrectFilenameException;
import exceptions.WrongParameterException;
import model.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleHandler {
    private Scanner scanner;
    private CommandManager commandManager;
    public ConsoleHandler(Scanner scanner, CommandManager commandManager) {
        this.scanner = scanner;
        this.commandManager = commandManager;
    }

    public void listen() throws CommandNotExistsException {
        try {
            while (true) {
                String[] command = scanner.nextLine().split(" ", 2);
                String commandName = command[0];
                if (command.length > 1) {
                    String[] parameters = command[1].split(" ");
                    if (parameters.length >= 1 && parameters[0].isEmpty()) {
                        commandManager.exec(commandName);
                    } else if (parameters.length >= 1) {
                        commandManager.exec(commandName, parameters);
                    }
                } else commandManager.exec(commandName);

            }
        } catch (CommandNotExistsException | ElementNotFoundException | WrongParameterException |
                 IncorrectFilenameException e) {
            printError(e.toString());
            commandManager.exec("help");
            listen();
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