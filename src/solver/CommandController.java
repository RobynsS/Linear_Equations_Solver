package solver;

import java.util.ArrayList;
import java.util.ListIterator;

public class CommandController {
    private Command command;
    private ArrayList<SwapColCommand> swapColCommandHistory;

    public void setCommand(Command command) {
        this.command = command;
        this.swapColCommandHistory = new ArrayList<>();
    }

    public void executeCommand() {
        command.execute();
        if (command instanceof SwapColCommand){
            swapColCommandHistory.add((SwapColCommand) command);
        }
    }

    public void undoSwaps(){
        ListIterator<SwapColCommand> iterator = swapColCommandHistory.listIterator(swapColCommandHistory.size());

        while(iterator.hasPrevious()){
            SwapColCommand swap = iterator.previous();
            swap.undo();
        }

    }
}
