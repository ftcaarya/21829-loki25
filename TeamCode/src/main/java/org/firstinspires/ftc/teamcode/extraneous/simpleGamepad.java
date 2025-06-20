package org.firstinspires.ftc.teamcode.extraneous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Testing Motor Configs", group = "exercise")
public class simpleGamepad extends OpMode {
    private final int speedCap = 1;

    public DcMotor frontLeft, rearLeft, rearRight, frontRight;


    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "leftfront");
        rearLeft = hardwareMap.get(DcMotor.class, "leftback");
        rearRight = hardwareMap.get(DcMotor.class, "rightback");
        frontRight = hardwareMap.get(DcMotor.class, "rightfront");

        // Change this
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {

//        double redColor = (double) robot.colorSensor.red() / 2;
//        double greenColor = (double) robot.colorSensor.green() / 2;
//        double blueColor = (double) robot.colorSensor.blue() / 2;
//        telemetry.addData("Values: ", "(" + robot.colorSensor.red() + ", " + robot.colorSensor.green() + ", " + robot.colorSensor.blue() + ")");
//        telemetry.addData("Scaled values: ", "(" + redColor + ", " + greenColor + ", " + blueColor + ")");
//        telemetry.addData("Another value:", robot.colorSensor.argb());
//
//        if (robot.colorSensor.red() > robot.colorSensor.green() + 50 && robot.colorSensor.red() > robot.colorSensor.blue() + 50) {
//            gamepad1.rumbleBlips(1);
//            gamepad1.setLedColor(255, 0, 0, 5000);
//        } else if (greenColor > blueColor && redColor > blueColor) {
//            gamepad1.setLedColor(230, 230, 0, 5000);
//            gamepad1.rumbleBlips(2);
//        } else if (robot.colorSensor.blue() > robot.colorSensor.red() + 50 && blueColor > greenColor) {
//            gamepad1.setLedColor(0, 0, 225, 5000);
//            gamepad1.rumbleBlips(3);
//        } else {
//            gamepad1.setLedColor(0, 250, 0, 5000);
//            gamepad1.stopRumble();
//        }

        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        double frontLeftPower = (y + x + rx);
        double backLeftPower = (y - x + rx);
        double frontRightPower = (y - x - rx);
        double backRightPower = (y + x - rx);

        frontLeftPower = Range.clip(frontLeftPower, -speedCap, speedCap);
        backRightPower = Range.clip(backRightPower, -speedCap, speedCap);
        backLeftPower = Range.clip(backLeftPower, -speedCap, speedCap);
        frontRightPower = Range.clip(frontRightPower, -speedCap, speedCap);

        frontLeft.setPower(frontLeftPower);
        rearLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        rearRight.setPower(backRightPower);

        telemetry.update();
    }
}
