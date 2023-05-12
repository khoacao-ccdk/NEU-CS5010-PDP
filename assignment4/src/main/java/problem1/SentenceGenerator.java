package problem1;

import java.util.List;
import java.util.Scanner;
import problem1.Reader.JSONReader;
import problem1.Reader.Reader;

/**
 * Reppresents a SentenceGenerator object - Main class of the program
 *
 * @author Cody Cao, Letian Shi
 */
public class SentenceGenerator {

  /**
   * Message that first displays when the program starts
   */
  public static final String WELCOME_PROMPT = "Loading grammars...";
  /**
   * Message when showing the available grammar
   */
  public static final String PROMPT_AVAILABLE_GRAMMAR = "The following grammars are available:";
  /**
   * Message that prompts user to input which grammar they want to use
   */
  public static final String GRAMMAR_NUMBER_INPUT_PROMPT = "Which grammar would you like to use? (q to quit)";
  /**
   * Message that prompts user to enter whether they want to generate another sentence
   */
  public static final String CONTINUE_INPUT_PROMPT = "Would you like another? (y/n)";
  /**
   * Error message when user enter an invalid grammar choice
   */
  public static final String ERROR_INVALID_NUMBER_INPUT = "Invalid input. Enter a number represents the grammar that you would like to choose or q to exit";
  /**
   * Error message when user enter an invalid choice for whether they want to generate another
   * sentence
   */
  public static final String ERROR_INVALID_CONTINUE_INPUT = "Invalid input. Enter y to continue or n to choose another grammar.";

  //A Scanner object that will be used throughout the program
  private static Scanner sc;

  //A JSONReader instance that will be used throughout the program
  private static Reader reader;
  //A List of Grammar objects returned by the Reader after reading directory
  private static List<Grammar> grammarList;

  /**
   * Main function to handle program's functionalities
   *
   * @param args include the grammars' directory name for the program to search
   */
  public static void main(String args[]) {
    System.out.println(WELCOME_PROMPT);

    String jsonDirectory = args[0];
    reader = new JSONReader();
    //Get all available grammar from directory
    try {
      grammarList = reader.getGrammarList(jsonDirectory);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return;
    }

    handleInput(grammarList);
  }

  /**
   * Handles user input using the console
   * @param grammarList a List of available grammars
   */
  private static void handleInput(List<Grammar> grammarList){
    sc = new Scanner(System.in);
    String input = "";

    while (true) {
      System.out.println(PROMPT_AVAILABLE_GRAMMAR);
      for (int i = 0; i < grammarList.size(); i++) {
        Grammar current = grammarList.get(i);
        System.out.println(String.format("%d. %s", i + 1, current.getGrammarTitle()));
      }
      System.out.println(GRAMMAR_NUMBER_INPUT_PROMPT);

      input = sc
          .nextLine()
          .trim()
          .toLowerCase();

      //Exit program if user enters q
      if (input.equals("q")) {
        sc.close();
        return;
      }

      int chosen;
      try {
        chosen = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println(ERROR_INVALID_NUMBER_INPUT);
        continue;
      }

      if (chosen < 1 || chosen > grammarList.size()) {
        System.out.println(ERROR_INVALID_NUMBER_INPUT);
        continue;
      }

      Grammar chosenGrammar = grammarList.get(chosen - 1);

      getOutput(chosenGrammar);
    }
  }

  /**
   * Generates output based on the user's choice of grammar
   *
   * @param grammar a Grammar object represents the user's choice
   * @throws Exception an Exception when the sentence could not be created
   */
  private static void getOutput(Grammar grammar) {
    try {
      reader.getGrammarBody(grammar);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }
    while (true) {
      try {
        String output = grammar.generateOutput();
        System.out.println(output);
      }
      catch (Exception e){
        System.out.println(e.getMessage());
        return;
      }

      //Check if the user wants to create another output using the current grammar
      System.out.println(CONTINUE_INPUT_PROMPT);
      String input = sc
          .nextLine()
          .trim()
          .toLowerCase();

      switch (input) {
        case "y":
          continue;
        case "n":
          return;
        default:
          System.out.println(ERROR_INVALID_CONTINUE_INPUT);
      }
    }
  }
}
