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

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car car1 = new Car("BMW", 3);
      user1.setCar(car1);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      Car car2 = new Car("Audi", 1);
      user2.setCar(car2);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      Car car3 = new Car("Mazda", 2);
      user3.setCar(car3);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      Car car4 = new Car("BMW", 3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      users.forEach(u -> System.out.printf(" Id = %s \n First Name = %s \n Last Name = %s \n Email = %s \n %s \n",
              u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getCar()));

      List<User> usersWithCars = userService.getUserWithCar("BMW", 3);
      usersWithCars.forEach(u -> System.out.println(u.getFirstName()));

      context.close();
   }
}
