package org.firstinspires.ftc.teamcode.derived;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.BotLogger;
import org.firstinspires.ftc.teamcode.excutil.RMath;

public class ClampedServo extends PositionableServo {

    public final BotLogger logger;

    protected Servo servo;
    private double MinPosition;
    private double MaxPosition;

    public ClampedServo(Servo servo) {
        this(servo, 0, 1);
    }

    public ClampedServo(Servo servo, double min, double max) {
        super(servo);
        setLimits(min, max);

        logger = new BotLogger(String.format("ClampedServo[min=%.3f, max=%.3f]", min, max));
    }

    public void setLimits(double min, double max) {
        MinPosition = min;
        MaxPosition = max;
    }

    @Override
    public void setPosition(double position) {
        if (position < MinPosition || position > MaxPosition) {
            logger.warn(String.format("Invalid position move requested! (to %.3f)", position));
        }
        super.setDPosition(RMath.clamp(position, MinPosition, MaxPosition));
    }

}
