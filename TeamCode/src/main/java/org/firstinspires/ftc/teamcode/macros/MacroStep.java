package org.firstinspires.ftc.teamcode.macros;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Represents a 'step' in a MacroSequence. That's defined as a certain block of movements that all need to complete
 * before the next block of movements can begin. In theory, everything could be done in one step by abusing
 * delays, but use these as an organizational tool.
 */
public abstract class MacroStep {

    private MacroStep nextStep = null;
    protected boolean running = false;
    protected MacroSequence hostPath = null;

    public MacroStep() {
        this(null);
    }

    public MacroStep(MacroStep then) {
        nextStep = then;
    }

    public void start() {
        running = true;
        onStart();
    }

    /**
     * Called the first tick of PathStep execution and never again.
     */
    public abstract void onStart();

    public void setHostPath(MacroSequence path) {
        hostPath = path;
    }

    public void finish() {
        running = false;
        if (hostPath != null && !hostPath.Finished) {
            hostPath.runNext();
        }
    }

    public abstract void onTick(OpMode opMode);

    public boolean canTimeout() {
        return true;
    }

}
