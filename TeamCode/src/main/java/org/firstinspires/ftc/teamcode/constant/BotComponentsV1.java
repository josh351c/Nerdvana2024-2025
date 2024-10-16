package org.firstinspires.ftc.teamcode.constant;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

public class BotComponentsV1 {



    public static DcMotor
            front_left, front_right,
            back_left, back_right;

    public static IMU
            imu;

    public static void init(HardwareMap hardware) {
        front_left = hardware.get(DcMotor.class, "frontleft");
        front_right = hardware.get(DcMotor.class, "frontright");
        back_left = hardware.get(DcMotor.class, "backleft");
        back_right = hardware.get(DcMotor.class, "backright");

        imu = hardware.get(IMU.class, "imu");

    }

}
