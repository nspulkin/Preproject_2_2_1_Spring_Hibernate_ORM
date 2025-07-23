package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Nick", "Name", "user1@mail.ru");
        User user2 = new User("Jack", "Surname", "user2@mail.ru");
        User user3 = new User("Mary", "Lastname", "user3@mail.ru");
        User user4 = new User("John", "Familyname", "user4@mail.ru");

        Car car1 = new Car(1111111, "Tesla");
        Car car2 = new Car(2222222, "Toyota");
        Car car3 = new Car(3333333, "BMW");
        Car car4 = new Car(4444444, "Chevrolet");

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);


        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().getModel());
            System.out.println();
        }


        User foundUser = userService.getUserByCarId("Tesla", 1111111);
        if (foundUser != null) {
            System.out.println("Found user: " + foundUser.getFirstName());
        } else {
            System.out.println("User not found!");
        }


        context.close();
    }
}

