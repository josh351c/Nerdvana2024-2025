package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.excutil.Input;

import static org.firstinspires.ftc.teamcode.constant.BotComponentsV1.*;

/*
* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
*
*
*  Lots of code from https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
*
*
* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
*/
public class DemoBotOpmode extends OpMode {

    Input input = new Input();

    IMU imu = hardwareMap.get(IMU.class, "imu");

    @Override
    public void init() {

        // flip one half of robot for mecanum drive
        // may swap to left half instead, see how it drives
        front_right.setDirection(DcMotorSimple.Direction.REVERSE);

	// this one would be reversed by both so we leave it running forward, adjust to back_left as needed
        //back_right.setDirection(DcMotorSimple.Direction.REVERSE);

        //flip back motors because their chains are mounted opposite to front
        //may swap to front half instead
        back_left.setDirection(DcMotorSimple.Direction.REVERSE);



        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);
    }

    @Override
    public void loop() {
        input.pollGamepad(gamepad1);

        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        // This button choice was made so that it is hard to hit on accident,
        // it can be freely changed based on preference.
        // The equivalent button is start on Xbox-style controllers.
        if (gamepad1.options) {
            imu.resetYaw();
        }

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;

        front_left.setPower(frontLeftPower);
        back_left.setPower(backLeftPower);
        front_right.setPower(frontRightPower);
        back_right.setPower(backRightPower);
    }
}
