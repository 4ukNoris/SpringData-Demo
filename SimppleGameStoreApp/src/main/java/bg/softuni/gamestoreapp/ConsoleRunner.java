package bg.softuni.gamestoreapp;

import bg.softuni.gamestoreapp.constants.Command;
import bg.softuni.gamestoreapp.myExceptions.GameExistException;
import bg.softuni.gamestoreapp.myExceptions.UserNotAdmin;
import bg.softuni.gamestoreapp.myExceptions.UserNotLoggedInException;
import bg.softuni.gamestoreapp.myExceptions.ValidationException;
import bg.softuni.gamestoreapp.services.execute.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ExecutorService executorService;

    @Autowired
    public ConsoleRunner(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        while (!command.equals(Command.CLOSE)) {

            String outputAfterCommand;
            try {
                outputAfterCommand = executorService.execute(command);
            } catch (IllegalArgumentException | GameExistException | UserNotAdmin
                     | ValidationException | UserNotLoggedInException exception ) {
                outputAfterCommand = exception.getMessage();
            }

            System.out.println(outputAfterCommand);

            command = scanner.nextLine();
        }

    }
}
