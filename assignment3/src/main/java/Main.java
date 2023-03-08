import java.util.Scanner;

/**
 * Main class, used to retrieve user input
 */
public class Main {

  /**
   * Main function of the program
   * @param args arguments of the main function (not needed)
   */
  public static void main(String[] args) {
    for (String s : args){
      System.out.println(s);
    }
    System.out.println(CommandHandler.USAGE);
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    sc.close();
    CommandHandler handler = new CommandHandler();

    //Prints the error message if the function throws any exception during execution
    try {
      handler.handleInput(input);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
