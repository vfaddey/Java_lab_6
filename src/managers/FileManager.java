package managers;

import exceptions.CommandNotExistsException;
import exceptions.ElementNotFoundException;
import exceptions.IncorrectFilenameException;
import exceptions.WrongParameterException;
import model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.LinkedList;

public class FileManager {
    public static LinkedList<Organization> readCollectionFromCSV(String filename, ConsoleHandler consoleHandler) throws CommandNotExistsException, IncorrectFilenameException, ElementNotFoundException, WrongParameterException {
        LinkedList<Organization> collection = new LinkedList<>();
        try {
            File file = new File(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String firstLine = bufferedReader.readLine();
            if (firstLine == null) throw new NullPointerException("Этот файл пустой!");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                collection.add(parseOrganizationFromStrings(values));
            }
            consoleHandler.println("Коллекция загружена!");
            consoleHandler.printAdvice("Напишите help для просмотра списка команд");
            return collection;
        } catch (IOException e) {
            consoleHandler.printError("Файл с таким именем не найден. Попробуйте еще раз.");
            readCollectionFromCSV(filename, consoleHandler);
        } catch (NullPointerException e) {
            consoleHandler.printError(e.toString());
            readCollectionFromCSV(filename, consoleHandler);
        }
        return null;
    }

    public static void writeCollectionToCSV(LinkedList<Organization> collection, String filename) {
        String line;
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            writer.println("id,name,coordinatesX,coordinatesY,creationDate,annualTurnover,employeesCount,type,zipCode,locationX,locationY,locationZ");
            for (Organization organization : collection) {
                line = organization.getId().toString() + "," +
                        organization.getName() + "," +
                        organization.getCoordinates().getX() + "," +
                        organization.getCoordinates().getY() + "," +
                        organization.getCreationDate().toString() + "," +
                        organization.getAnnualTurnover().toString() + "," +
                        organization.getEmployeesCount().toString() + "," +
                        organization.getType().ordinal() + "," +
                        organization.getOfficialAddress().getZipCode() + "," +
                        organization.getOfficialAddress().getTown().getX() + "," +
                        organization.getOfficialAddress().getTown().getY() + "," +
                        organization.getOfficialAddress().getTown().getZ();
                writer.println(line);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    private static Organization parseOrganizationFromStrings(String[] data) {
        Long id = Long.parseLong(data[0]); //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
        String name = data[1]; //Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates = new Coordinates(Integer.parseInt(data[2]), Long.parseLong(data[3])); //Поле не может быть null
        java.time.LocalDate creationDate = LocalDate.parse(data[4]); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        Long annualTurnover = Long.parseLong(data[5]); //Поле не может быть null, Значение поля должно быть больше 0
        Integer employeesCount = Integer.parseInt(data[6]); //Поле не может быть null, Значение поля должно быть больше 0
        OrganizationType type = OrganizationType.values()[Integer.parseInt(data[7])]; //Поле не может быть null
        Location location = new Location(Double.parseDouble(data[9]), Double.parseDouble(data[10]), Long.parseLong(data[11]));
        Address officialAddress = new Address(data[8], location); //Поле может быть null

        return new Organization(id, name, coordinates, creationDate, annualTurnover, employeesCount, type, officialAddress);
    }

}
