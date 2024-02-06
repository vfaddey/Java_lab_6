package managers;

import commands.Add;
import commands.Command;
import commands.Help;
import exceptions.CommandNotExistsException;
import exceptions.WrongParameterException;
import model.*;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleHandler {
    private Scanner scanner;
    private CommandManager commandManager;
    private Validator validator;

    public ConsoleHandler(Scanner scanner, CommandManager commandManager,  Validator validator) {
        this.scanner = scanner;
        this.commandManager = commandManager;
        this.validator = validator;
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
        } catch (CommandNotExistsException e) {
            System.out.println("Ошибка: " + e.toString());
            commandManager.exec("help");
            listen();
        } catch (WrongParameterException e) {
            System.out.println("Ошибка: " + e.toString());
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

    public Scanner getScanner() {
        return scanner;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public Validator getValidator() {
        return validator;
    }
}