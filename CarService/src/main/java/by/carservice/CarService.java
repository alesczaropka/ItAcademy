package by.carservice;

import by.carservice.app.Menu;

public class CarService {
    public static void main(String[] args) {
        try {
            System.out.println("Запуск программы Автодиагностика.");
            Menu.run();
            System.out.println("Закрытие программы Автодиагностика.");
        } catch (Exception e) {
            System.err.println("Ошибка выполнения программы.");
            e.printStackTrace();
        }
    }
}
