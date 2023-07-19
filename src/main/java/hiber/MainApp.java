package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        carService.add(new Car("BMW", 3));
        carService.add(new Car("Toyota", 5));
        carService.add(new Car("BMW", 3));
        carService.add(new Car("Lexus", 4));

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", carService.get(1L)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", carService.get(2L)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", carService.get(3L)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", carService.get(4L)));

        List<User> users = userService.listUsers();
        printUsers(users);

        users = userService.getUserWhereCar("BMW", 3);
        printUsers(users);

        context.close();
    }

    private static void printUsers(List<User> users) {
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
    }
}
