public class EnterMenu {
    public void run() {
        // if stay logged in login that user
        String input;
        System.out.println("Do you already have an account?\n(Please type in exit if you want to end the program or answer with yes or no)");
        while (true) {
            input = Main.scanner.nextLine();
            if (input.equals("yes")) {
                LoginMenu menu = new LoginMenu();
                menu.run();
            }
            else if (input.equals("no")) {
                //RegisterMenu menu = new RegisterMenu();
                //menu.run();
            }
            else if (input.equals("exit")) {
                System.out.println("Thanks for being with us!");
                return;
            }
            else {
                System.out.println("Please try again!");
            }
        }
    }
}
