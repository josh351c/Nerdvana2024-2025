package org.firstinspires.ftc.teamcode.macros.generic;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.macros.MacroStep;

/**
 * Standard macro.
 * Simply moves on to the next step only after a Predicate resolves to TRUE.
 * You can put any condition in here... maybe something is going on separately of the
 * macro and you need it to be in a certain state before the succeeding steps. Go wild.
 */
public class ServoPositionMacro extends MacroStep {

    private ElapsedTime runtime = new ElapsedTime();
    private Servo servo = null;
    private double msDuration = 0, position = 0;

    public ServoPositionMacro(Servo servo, double position, double delayAfter) {

        this.servo = servo;
        this.msDuration = delayAfter;
        this.position = position;
    }

    @Override
    public void onStart() {
        runtime.reset();
        servo.setPosition(position);
    }

    @Override
    public void onTick(OpMode opMode) {
        if (runtime.milliseconds() > msDuration) {
            finish();
            return;
        }
    }
}
