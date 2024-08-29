package org.firstinspires.ftc.teamcode.macros.generic;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.macros.MacroSequence;
import org.firstinspires.ftc.teamcode.macros.MacroStep;

/**
 * Standard macro.
 * I don't recall ever using this. Looks like it just starts a whole new MacroSequence..?
 */
public class RunExtraMacro extends MacroStep {

    private MacroSequence nextSequence;

    public RunExtraMacro(MacroSequence next) {
        this.nextSequence = next;
    }

    @Override
    public void onStart() {
        nextSequence.start();
    }

    @Override
    public void onTick(OpMode opMode) {

    }

}
