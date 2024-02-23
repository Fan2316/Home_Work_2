package animals;

import validators.DataValidator;

import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Animal {
    private String name;
    private int weight;
    private String color;
    private int age;
    private DataValidator dataValidator = new DataValidator();
    private DataValidator commandValidator = new DataValidator();


    private int getAgeWeightData(Scanner scanner) {
        int data;
        while (true) {
            String ageStr = scanner.next();
            if (dataValidator.isDataByRegExp(ageStr, Pattern.compile("^\\d+$"))) {
                data = Integer.parseInt(ageStr);
                if (data > 50 || data <= 0) {
                    System.out.println("Ошибка! Убедитесь, что указано значение в диапазоне от 1 до 50");
                    continue;
                }
                break;
            }
            System.out.println("Ошибка! Убедитесь, что указано значение в диапазоне от 1 до 50");
        }
        return data;
    }


    public void setAge(Scanner scanner) {
        this.age = this.getAgeWeightData(scanner);
    }

    public int getAge() {
        return age;
    }


    public void setWeight(Scanner scanner) {
        this.weight = this.getAgeWeightData(scanner);
    }

    public int getWeight() {
        return weight;
    }

    private String getNameColor(Scanner scanner) {

        StringBuilder str;
        while (true) {
            String nameStr = scanner.next();

            if (commandValidator.isDataByRegExp(nameStr, Pattern.compile("^[а-яА-ЯЁё]+$"))) {
                str = new StringBuilder(nameStr);
                break;
            }

            System.out.println("Ошибка! Убедитесь, что включена русская раскладка клавиатуры");
        }

        return String.valueOf(str);
    }

    public void setName(Scanner scanner) {
        this.name = this.getNameColor(scanner);
    }

    public String getName() {
        return name;
    }

    public void setColor(Scanner scanner) {
        this.color = this.getNameColor(scanner);
    }

    public String getColor() {
        return color;
    }

    public abstract void say();


    public void go() {
        System.out.println("Я иду");
    }


    public void drink() {
        System.out.println("Я пью");
    }


    public void eat() {
        System.out.println("Я ем");
    }


    @Override
    public String toString() {
        String yearPadej = getYearPadej();
        if (yearPadej == null) {
            return "Возраст введён неверно";
        }

        return String.format("Привет! Меня зовут %s, мне %d %s, я вешу - %d кг, мой цвет - %s",
                getName(), getAge(), yearPadej, getWeight(), getColor());
    }

    //падеж окончаний возраста
    private String getYearPadej() {

        if (getAge() > 50) {
            return null;
        }

        if (getAge() >= 11 && getAge() <= 19) {
            return "лет";
        }

        int ostatok = getAge() % 10;
        if (ostatok == 0 || ostatok >= 5) {
            return "лет";
        }

        if (ostatok == 1) {
            return "год";
        }

        return "года";

    }

}