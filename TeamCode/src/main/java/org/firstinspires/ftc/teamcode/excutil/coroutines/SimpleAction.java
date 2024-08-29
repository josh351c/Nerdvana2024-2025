package org.firstinspires.ftc.teamcode.excutil.coroutines;

/**
 * A simpler coroutine action when you need access to neither self-termination nor 
                the opmode/data arguments.
 * @see CoroutineManager#runLater(SimpleAction, double)
 */
@FunctionalInterface
public interface SimpleAction {
    void tick();
}
