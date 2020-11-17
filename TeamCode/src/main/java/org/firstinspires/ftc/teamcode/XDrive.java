package org.firstinspires.ftc.teamcode;

import android.service.autofill.DateValueSanitizer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="XDrive")
public class XDrive extends OpMode {

    private double gamepad1LeftStickY;
    private double gamepad1LeftStickX;
    private double gamepad1RightStickX;
    private double gamepad1RightStickY;

    private double leftFrontPower;
    private double leftBackPower;
    private double rightFrontPower;
    private double rightBackPower;

    public DcMotor leftFront;
    public DcMotor leftBack;
    public DcMotor rightFront;
    public DcMotor rightBack;

    public void init() {

        gamepad1LeftStickY = 0.0;
        gamepad1LeftStickX = 0.0;
        gamepad1RightStickX = 0.0;
        gamepad1RightStickY = 0.0;

        leftFrontPower = 0.0;
        leftBackPower = 0.0;
        rightFrontPower = 0.0;
        rightBackPower = 0.0;

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

    }
    public void loop() {

        gamepad1LeftStickX = gamepad1.left_stick_x;
        gamepad1LeftStickY = gamepad1.left_stick_y;
        gamepad1RightStickX = gamepad1.right_stick_x;
        gamepad1RightStickY = gamepad1.right_stick_y;

        leftFrontPower = 0.0;
        leftBackPower = 0.0;
        rightFrontPower = 0.0;
        rightBackPower = 0.0;

        if (gamepad1LeftStickX >= 0.05 || gamepad1LeftStickX <= -0.05) {
            leftFrontPower += gamepad1LeftStickX / 2;
            leftBackPower += gamepad1LeftStickX / -2;
            rightFrontPower += gamepad1LeftStickX / -2;
            rightBackPower += gamepad1LeftStickX / 2;
        }
        if (gamepad1LeftStickY >= 0.05 || gamepad1LeftStickY <= -0.05) {
            leftFrontPower += gamepad1LeftStickY / 2;
            leftBackPower += gamepad1LeftStickY / 2;
            rightFrontPower += gamepad1LeftStickY / -2;
            rightBackPower += gamepad1LeftStickY / -2;
        }
        if (gamepad1RightStickX >= 0.05 || gamepad1RightStickX <=-0.05) {
            leftFrontPower += gamepad1RightStickX / 2;
            leftBackPower += gamepad1RightStickX / 2;
            rightFrontPower += gamepad1RightStickX / 2;
            rightBackPower += gamepad1RightStickX / 2;
        }

        leftFront.setPower(leftFrontPower);
        leftBack.setPower(leftBackPower);
        rightFront.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);

    }
}