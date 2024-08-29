package org.firstinspires.ftc.teamcode.macros;

public interface Positionable {

    enum PositionFormat {
        /***
         * Named for "0-1" values, e.g. servos where rotation is normalized from 0-1 rather than degrees.
         */
        Normalized,
        /**
         * Named for integer values, e.g. encoder ticks.
         */
        Ticks
    }

    void setDPosition(double position);
    double getDPosition();
    PositionFormat getPositionFormat();
}
