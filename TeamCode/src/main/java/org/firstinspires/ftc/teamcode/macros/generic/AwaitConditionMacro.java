package org.firstinspires.ftc.teamcode.macros.generic;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.macros.MacroStep;

import java.util.function.Predicate;

/**
 * Standard macro.
 * Simply moves on to the next step only after a Predicate resolves to TRUE.
 * You can put any condition in here... maybe something is going on separately of the
 * macro and you need it to be in a certain state before the succeeding steps. Go wild.
 *
 * Can ALSO be used to run something every tick, since predicates are just lambda functions,
 * and end the step once that function decides it's done.
 */
public class AwaitConditionMacro extends MacroStep {

    private Predicate<OpMode> condition;

    public AwaitConditionMacro(Predicate<OpMode> condition) {
        this.condition = condition;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onTick(OpMode opMode) {
        if (condition.test(opMode)) {
            finish();
        }
    }

    @Override
    public boolean canTimeout() {
        return false;
    }
}
