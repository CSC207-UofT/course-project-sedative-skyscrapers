package main.java.TaskComponent;

public interface Command {

    // interface used by CommandTask to execute command
    void execute() throws Exception;

}

