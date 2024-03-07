package ru.gb.java.exceptions;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите данные в формате: Фамилия Имя Отчество дата _ рождения номер _ телефона пол");
            String in = scanner.nextLine();
            String[] personData = in.split(" ");
            if (personData.length != 6) {
                System.out.println("Введено неверное количество данных");
                continue;
            }
            String lastName = personData[0];
            String name = personData[1];
            String patronymic = personData[2];
            String dateOfBirth = personData[3];
            String phoneNumber = personData[4];
            String gender = personData[5];
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
                dateFormat.setLenient(false);
                dateFormat.parse(dateOfBirth);
                Long.parseLong(phoneNumber);
                if (!gender.equals("f") && !gender.equals("m")){
                    throw new IllegalArgumentException("Пол должен указываться в формате 'f' или 'm'");
                }
            } catch (ParseException e) {
                System.out.println("Дата рождения должна указываться в формате dd.mm.yyyy");
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Номер телефона должен указываться целым беззнаковым числом без форматирования");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            try {
                FileWriter fileWriter = new FileWriter(lastName + ".txt", true);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(lastName).append(" ").append(name).append(" ")
                        .append(patronymic).append(" ").append(dateOfBirth).append(" ")
                        .append(phoneNumber).append(" ").append(gender).append("\n");
                fileWriter.write(stringBuilder.toString());
                fileWriter.close();
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}