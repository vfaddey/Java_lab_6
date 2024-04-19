package client.managers;


import common.exceptions.*;
import common.requests.*;
import common.responses.ErrorResponse;
import common.responses.Response;
import common.responses.SuccessResponse;
import common.model.*;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * Class that handles users' inputs and sends them to CommandManager
 */
public class ConsoleHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final RequestManager requestManager;
    private final Sender sender;
    private ConsoleMode consoleMode;
    private final ResponseHandler responseHandler;
    private final Asker asker = new Asker();
    private final ScriptHandler scriptHandler = new ScriptHandler();


    public ConsoleHandler(RequestManager requestManager, Sender sender, ResponseHandler responseHandler) {
        this.requestManager = requestManager;
        this.sender = sender;
        this.responseHandler = responseHandler;
        this.consoleMode = ConsoleMode.INTERACTIVE;
    }

    public class Asker {
        public void interactiveOrganizationCreation(AddRequest request) {
            request.setName(askName());
            request.setCoordinates(askCoordinates());
            request.setAnnualTurnover(askAnnualTurnover());
            request.setEmployeesCount(askEmployeesCount());
            request.setOrganizationType(askType());
            request.setAddress(askAddress());
        }

        public void updateElement(UpdateRequest request) {
            String answer = askWhatToChange();
            try {
                if (Validator.isStringWithIntegers(answer)) {
                    String[] splitted = answer.split(" ");
                    int[] fieldsNumbers = new int[splitted.length];
                    for (int i = 0; i < fieldsNumbers.length; i++) {
                        fieldsNumbers[i] = Integer.parseInt(splitted[i]);
                    }
                    for (int num : fieldsNumbers) {
                        if (num > 6) {
                            throw new WrongParameterException("Число " + num + " не соответствует ни одному из полей");
                        }
                        switch (num) {
                            case 1 -> request.setName(askName());
                            case 2 -> request.setCoordinates(askCoordinates());
                            case 3 -> request.setAnnualTurnover(askAnnualTurnover());
                            case 4 -> request.setEmployeesCount(askEmployeesCount());
                            case 5 -> request.setOrganizationType(askType());
                            case 6 -> request.setAddress(askAddress());
                        }
                    }
                } else throw new WrongParameterException("Строка должна состоять только из чисел и пробелов.");
            } catch (WrongParameterException e) {
                printError(e.toString());
            }

        }

        public String askName() {
            String name = ask("Введите название организации: ");
            try {
                if (Validator.isValidName(name)) {
                    return name;
                } else {
                    throw new WrongParameterException("Имя не может быть пустым");
                }
            } catch (WrongParameterException e) {
                printError(e.toString());
                return askName();
            }
        }

        public Coordinates askCoordinates() {
            String response = ask("Введите через пробел координаты x и y (числа целые): ");
            int x;
            long y;
            try {
                if (response.split(" ").length < 2) {
                    throw new WrongParameterException("Введены не все параметры.");
                }
                if (Validator.isCorrectNumber(response.split(" ")[0], Integer.class) && Validator.isCorrectNumber(response.split(" ")[1], Long.class)) {
                    if (!Validator.isNull(response.split(" ")[0])) {
                        x = Integer.parseInt(response.split(" ")[0]);
                        y = Long.parseLong(response.split(" ")[1]);
                        return new Coordinates(x,y);
                    } else {
                        throw new WrongParameterException("x не может быть null.");
                    }
                } else {
                    throw new WrongParameterException("Неверно введены числа.");
                }
            } catch (WrongParameterException e) {
                printError(e.toString());
                return askCoordinates();
            }
        }

        public long askAnnualTurnover() {
            long result = -1;
            String response = ask("Введите годовой оборот компании (целое число): ");

            try {
                if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                    throw new NullUserRequestException("Введена пустая строка");
                }
                if (response.contains(" ")) {
                    String[] splitted = response.split(" ");
                    if (Validator.isCorrectNumber(splitted[0], Long.class)) {
                        result = Long.parseLong(splitted[0]);
                    }
                } else if (Validator.isCorrectNumber(response, Long.class)) {
                    result = Long.parseLong(response);
                } else {
                    throw new WrongParameterException("Неверно введено число.");
                }
                if (result > 0) {
                    return result;
                } else {
                    throw new WrongParameterException("Годовой оборот не может быть меньше нуля.");
                }
            } catch (WrongParameterException | NullUserRequestException e) {
                printError(e.toString());
                return askAnnualTurnover();
            }
        }

        public int askEmployeesCount() {
            int result = -1;
            String response = ask("Введите количество сотрудников: ");

            try {
                if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                    throw new NullUserRequestException("Введена пустая строка");
                }
                if (response.contains(" ")) {
                    String[] splitted = response.split(" ");
                    if (Validator.isCorrectNumber(splitted[0], Integer.class)) {
                        result = Integer.parseInt(splitted[0]);
                    }
                } else if (Validator.isCorrectNumber(response, Integer.class)) {
                    result = Integer.parseInt(response);
                } else {
                    throw new WrongParameterException("Неверно введено число.");
                }
                if (result > 0) {
                    return result;
                } else {
                    throw new WrongParameterException("Число сотрудников не может быть меньше одного.");
                }
            } catch (WrongParameterException | NullUserRequestException e) {
                printError(e.toString());
                return askEmployeesCount();
            }
        }

        public OrganizationType askType() {
            OrganizationType[] values = OrganizationType.values();
            StringBuilder question = new StringBuilder();
            question.append("Введите номер типа Вашей организации: \n");
            for (OrganizationType type : values) {
                question.append(type.ordinal() + 1).append(") ").append(type.name()).append("\n");
            }
            String response = ask(question.toString());

            String num;
            try {
                if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                    throw new NullUserRequestException("Поле не может быть пустым.");
                }
                if (response.contains(" ")) {
                    num = response.split(" ")[0];
                    if (Validator.isNull(num)) {
                        throw new WrongParameterException("Строка введена неверно.");
                    }

                } else {
                    num = response;
                }
                if (Validator.isCorrectNumber(num, Integer.class)) {
                    if (Integer.parseInt(num) <= OrganizationType.values().length && Integer.parseInt(num) >= 1) {
                        return OrganizationType.values()[Integer.parseInt(num)-1];
                    } else {
                        throw new WrongParameterException("Введено неверный номер.");
                    }
                } else {
                    throw new WrongParameterException("Неправильно введено число.");
                }

            } catch (WrongParameterException | NumberFormatException | NullUserRequestException e) {
                printError(e.toString());
                return askType();
            }
        }

        public Address askAddress() {
            String zipCode = ask("Введите город(?): ");
            String loc = ask("Введите координаты локации x, y, z через пробел (x и y - вещественные, z - целое): ");
            try {
                if (loc.split(" ").length < 3) {
                    throw new WrongParameterException("Неверно введены координаты локации");
                }
                if (Validator.isStringWithNumbers(loc) && Validator.isValidName(zipCode)) {
                    if (Validator.areCorrectLocationParams(loc.split(" ")[0], loc.split(" ")[1], loc.split(" ")[2])) {
                        double x = Double.parseDouble(loc.split(" ")[0]);
                        double y = Double.parseDouble(loc.split(" ")[1]);
                        long z = Long.parseLong(loc.split(" ")[2]);
                        return new Address(zipCode, new Location(x,y,z));
                    } else {
                        throw new WrongParameterException("Неверно введены координаты локации.");
                    }
                } throw new WrongParameterException("Неверно введены параметры. Попробуйте снова.");
            } catch (WrongParameterException e) {
                printError(e.toString());
                return askAddress();
            }
        }
    }

    public class ScriptHandler {
        private Stack<String> filenames = new Stack<>();
        private Queue<String> commands = new ArrayDeque<>();

        public String nextLine() {
            try {
                return this.commands.poll();
            } finally {
                if (commands.isEmpty()) {
                    consoleMode = ConsoleMode.INTERACTIVE;
                    commands = new ArrayDeque<>();
                }
            }
        }

        public void clear() {
            this.commands.clear();
        }

        private void readScript(String filename) throws WrongParameterException {
            try {
                if (filenames.contains(filename)) {
                    throw new RecursionExecutionException("Рекурсивный вызов файла.");
                }
                filenames.push(filename);
                File file = new File(filename);
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.equals("execute_script " + filename)) {
                        throw new RecursionExecutionException("Рекурсивный вызов файла.");
                    }
                    this.commands.add(line);
                }
            } catch (IOException e) {
                throw new WrongParameterException("Файл не найден или нет доступа к нему.");
            }
        }
    }

    public void listen() {
        while (true) {
            try {
                print(">>> ");
                String request = next();
                Response response = processUserRequest(request);
                println(this.responseHandler.handleResponse(response));
            } catch (Exception e) {
                printError(e.toString());
                break;
            }
        }
    }

    public String next() {
        if (this.consoleMode == ConsoleMode.INTERACTIVE) {
            return scanner.nextLine();
        } else if (this.consoleMode == ConsoleMode.FILE_READER) {
            return scriptHandler.nextLine();
        }
        return scanner.nextLine();
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
            if (requestToServer instanceof AddRequest) {
                this.asker.interactiveOrganizationCreation((AddRequest) requestToServer);
            }
            if (requestToServer instanceof UpdateRequest) {
                this.asker.updateElement((UpdateRequest) requestToServer);
            }
            if (requestToServer instanceof ExecuteScriptRequest) {
                this.consoleMode = ConsoleMode.FILE_READER;
                scriptHandler.readScript(((ExecuteScriptRequest) requestToServer).getFilename());
                return new SuccessResponse(requestToServer.getCommandName(), "Началось выполнение скрипта...");
            }
            return this.sender.sendRequest(requestToServer);

        } catch (NullUserRequestException | CommandNotExistsException | WrongParameterException e) {
            return new ErrorResponse(e.toString());
        } catch (IOException e) {
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

    public String ask(String message) {
        print(message);
        return next();
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
        return next();
    }

    public void println(Object obj) {
        if (consoleMode == ConsoleMode.INTERACTIVE || obj != null) {
            System.out.println(obj.toString());
        }
    }

    public void print(Object obj) {
        if (consoleMode == ConsoleMode.INTERACTIVE) {
            System.out.print(obj.toString());
        }
    }

    public void printAdvice(String advice) {
        if (consoleMode == ConsoleMode.INTERACTIVE) {
            System.out.println("Совет: " + advice);
        }
    }

    public void printError(String message) {
        System.out.println("Ошибка: " + message);
        if (this.consoleMode == ConsoleMode.FILE_READER) {
            this.scriptHandler.clear();
            System.out.println("Выполнение файла завершено.");
            this.consoleMode = ConsoleMode.INTERACTIVE;
            listen();
        }
    }

}