package org.firstinspires.ftc.teamcode.constant;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BotComponentsV1 {

    public static DcMotor
            front_left, front_right,
            back_left, back_right;

    public static void init(HardwareMap hardware) {
        front_left = hardware.get(DcMotor.class, "front_left");
        front_right = hardware.get(DcMotor.class, "front_right");
        back_left = hardware.get(DcMotor.class, "back_left");
        back_right = hardware.get(DcMotor.class, "back_right");
    }

}
