import Screens.HomeScreen;
import Utilities.Utils;

public class Main {

    public static void main(String[] args) {
        HomeScreen home = new HomeScreen();
        boolean isExitFromHome = false;
        while (!isExitFromHome) {
            home.showHomeScreenOptionsMenu();
            String userOption = home.receiveUserOption();
            isExitFromHome = home.performUserOption(userOption);
        }
        System.out.println("Exiting the application, see you next time!");
    }
}
