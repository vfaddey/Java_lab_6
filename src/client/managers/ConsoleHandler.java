package client.managers;

import exceptions.*;
import server.managers.CommandManager;
import server.model.*;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * Class that handles users' inputs and sends them to CommandManager
 */
public class ConsoleHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final Receiver receiver;

    public ConsoleHandler(Receiver receiver) {
        this.receiver = receiver;
    }

    public String collectionFilenameRequest() {
        System.out.print("Введите путь к файлу коллекции: ");
        return scanner.nextLine();
    }

    public static class ScriptHandler {
        public static void readCommands(String filename, CommandManager commandManager) throws IOException, WrongParameterException, IncorrectFilenameException, ElementNotFoundException, CommandNotExistsException, NullUserRequestException {
            try {
                String[] commands = readScript(filename);
                commandManager.processFileCommands(commands);
            } catch (WrongParameterException e) {
                throw new WrongParameterException(e.toString());
            }
        }

        private static String[] readScript(String filename) throws WrongParameterException {
            try {
                List<String> commands = new ArrayList<>();
                File file = new File(filename);
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.equals("execute_script " + filename)) {
                        throw new RecursionExecutionException("Рекурсивный вызов файла.");
                    }
                    commands.add(line);
                }
                return commands.toArray(new String[0]);
            } catch (IOException e) {
                throw new WrongParameterException("Файл не найден или нет доступа к нему.");
            }
        }
    }

    public void listen() {
        while (true) {
            try {
                print(">>> ");
                String request = scanner.nextLine();
                if (!request.isEmpty()) {
                    receiver.write(request);
                }
            } catch (IOException | InterruptedException e) {
                receiver.close();
                throw new RuntimeException(e);
            }
        }
    }

    public String ask(String message) {
        print(message);
        return scanner.nextLine();
    }

    public String askOrganizationType(OrganizationType[] values) {
        println("Введите номер типа Вашей организации: ");
        for (OrganizationType type : values) {
            println(type.ordinal() + 1 + ") " + type.name());
        }
        return scanner.nextLine();
    }

    public String askWhatToChange() {
        println("Выберите, что Вы хотите поменять и укажите соответствующие номера характеристик через пробел: ");
        Field[] fields = Organization.class.getDeclaredFields();
        List<Field> filteredFields = new ArrayList<>();

        for (Field field : fields) {
            if (!field.getName().equals("id") && !field.getName().equals("creationDate")) {
                filteredFields.add(field);
            }
        }
        Field[] resultingArray = filteredFields.toArray(new Field[0]);
        for (int i = 1; i <= resultingArray.length; i++) {
            println(i + ") " + resultingArray[i-1].getName());
        }
        return scanner.nextLine();
    }

    public void println(Object obj) {
        System.out.println(obj.toString());
    }

    public void print(Object obj) {
        System.out.print(obj.toString());
    }

    public void printAdvice(String advice) {
        System.out.println("Совет: " + advice);
    }

    public void printError(String message) {
        System.out.println("Ошибка: " + message);
    }

}