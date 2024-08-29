package org.firstinspires.ftc.teamcode.macros.generic;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.macros.MacroStep;

/**
 * Standard macro.
 * Runs a continuous rotation (CR) servo for a set millisecond duration at a set power,
 * and then moves on to the next step. Obviously, not for super precise movements, but if
 * you just wanna 'eehhhhh.... run my intake for a little while...' this works well.
 */
public class CRServoRunMacro extends MacroStep {

    private ElapsedTime runtime = new ElapsedTime();
    private CRServo servo = null;
    private double msDuration = 0, power = 0;

    public CRServoRunMacro(CRServo servo, double power, double msDuration) {

        this.servo = servo;
        this.msDuration = msDuration;
        this.power = power;
    }

    @Override
    public void onStart() {
        runtime.reset();
        servo.setPower(power);
    }

    @Override
    public void onTick(OpMode opMode) {
        if (runtime.milliseconds() > msDuration) {
            finish();
            return;
        }
    }
}
