package seedu.smarthomebot.ui;

import seedu.smarthomebot.commands.CommandResult;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static seedu.smarthomebot.common.Messages.DIVIDER;
import static seedu.smarthomebot.common.Messages.MESSAGE_GOODBYE;
import static seedu.smarthomebot.common.Messages.MESSAGE_WELCOME;

/**
 * Text UI of the application.
 */

public class TextUi {

    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";
    private static final String ENTER_COMMAND = "Enter command: ";
    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(System.in);
        this.out = out;
    }

    /**
     * Shows message(s) to the user.
     */
    public void printToUser(String message) {
        out.println(message);
    }

    public void printResultToUser(CommandResult result) {
        printToUser(result.feedbackToUser);
    }

    /**
     * Print a divider.
     */
    private void printDivider() {
        printToUser(DIVIDER);
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        printDivider();
        out.print(ENTER_COMMAND);
        String fullInputLine = in.nextLine();
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }
        return fullInputLine.trim();
    }

    public void showWelcomeMessage() {
        printDivider();
        printToUser(MESSAGE_WELCOME);
    }

    /**
     * Generates and prints the Goodbye message upon the end of the application.
     */
    public void showGoodByeMessage() {
        printToUser(MESSAGE_GOODBYE);
    }
}
