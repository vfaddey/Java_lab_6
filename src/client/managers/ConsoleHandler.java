package client.managers;


import common.exceptions.*;
import common.requests.Request;
import common.requests.RequestWithParameters;
import common.responses.ErrorResponse;
import common.responses.Response;
import server.managers.CommandManager;
import common.model.*;
import client.managers.Sender;
import server.managers.Validator;

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
    private RequestManager requestManager;
    private Sender sender;

    public ConsoleHandler(Receiver receiver) {
        this.receiver = receiver;
    }

    public ConsoleHandler(Receiver receiver, RequestManager requestManager, Sender sender) {
        this.receiver = receiver;
        this.requestManager = requestManager;
        this.sender = sender;
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
                print(">>>");
                String request = scanner.nextLine();
                Response response = processUserRequest(request);
                println(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Response processUserRequest(String request) {
        try {
            String[] processed = splitUserRequest(request);
            Request requestToServer = this.requestManager.get(processed[0]);
            if (requestToServer instanceof RequestWithParameters) {
                String[] parameters = new String[processed.length-1];
                for (int i = 1; i < processed.length; i++) {
                    parameters[i-1] = processed[i];
                }
                ((RequestWithParameters) requestToServer).setParameters(parameters);
            }
            return this.sender.sendRequest(requestToServer);

        } catch (NullUserRequestException | CommandNotExistsException | WrongParameterException e) {
            return new ErrorResponse(e.toString());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private String[] splitUserRequest(String request) throws NullUserRequestException {
        if (request.isEmpty()) throw new NullUserRequestException("Введена пустая строка");
        if (!request.contains(" ")) return new String[]{request};
        String command = request.split(" ", 2)[0];
        String[] parameters = request.split(" ", 2)[1].split(" ");
        if (parameters.length != 0) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i].isEmpty()) {
                    parameters[i] = null;
                }
            }
        }

        String[] processed;
        if (Validator.isArrayConsistsOfOnlyNull(parameters)) {
            processed = new String[]{command};
            return processed;
        } else {
            processed = new String[parameters.length + 1];
            processed[0] = command;
            System.arraycopy(parameters, 0, processed, 1, parameters.length);
        }
        return processed;
    }

//    public void listen() {
//        while (true) {
//            try {
//                print(">>> ");
//                String request = scanner.nextLine();
//                if (!request.isEmpty()) {
//                    receiver.connect();
//                    receiver.write(request);
//                    receiver.close();
//                }
//            } catch (IOException | InterruptedException e) {
//                receiver.close();
//                throw new RuntimeException(e);
//            }
//        }
//    }

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