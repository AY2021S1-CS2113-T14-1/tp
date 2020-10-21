package seedu.smarthomebot.logic.commands;

import static seedu.smarthomebot.commons.Messages.LINE;

public class InvalidCommand extends Command {

    public final String feedbackToUser;

    public InvalidCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(LINE + this.feedbackToUser);
    }

}