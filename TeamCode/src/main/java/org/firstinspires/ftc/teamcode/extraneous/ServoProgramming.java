package org.firstinspires.ftc.teamcode.extraneous;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Config
@TeleOp(name="ServoProgramming", group = "robot")
public class ServoProgramming extends OpMode {



    public Servo claw, rotate, wrist_left, wrist_right, arm_left, arm_right, hor_left, hor_right, intake_left, intake_right;
    public static double hor_left_extend = 0.53;
    public static double hor_left_retract = 0.18;
    public static double hor_right_extend = 0.53;
    public static double hor_right_retract = 0.18;
    public static double CLAW_OPEN = 1;
    public static double CLAW_CLOSE = 0;
    public static double wrist_left_down = 0.8;
    public static double wrist_left_up = 1;
    public static double wrist_right_down = 0.7;
    public static double wrist_right_up = 1;
    public static double intake_left_down = 0;
    public static double intake_left_up = 1;
    public static double intake_right_down = 0;
    public static double intake_right_up = 1;

    public static double arm_left_up = 1;
    public static double arm_left_down = 0;
    public static double arm_right_up = 1;
    public static double arm_right_down = 0;
    public static double rotate_down = 0;
    public static double intake_up = 1;



@Override
    public void init() {

        claw = hardwareMap.get(Servo.class, "claw");
        rotate = hardwareMap.get(Servo.class, "rotate");
        wrist_left = hardwareMap.get(Servo.class, "wrist left");
        wrist_right = hardwareMap.get(Servo.class, "wrist right");
        arm_left = hardwareMap.get(Servo.class, "arm left");
        arm_right = hardwareMap.get(Servo.class, "arm right");
        hor_left = hardwareMap.get(Servo.class, "hor left");
        hor_right = hardwareMap.get(Servo.class, "hor right");
        intake_left = hardwareMap.get(Servo.class, "intake left");
        intake_right = hardwareMap.get(Servo.class, "intake right");

}
@Override
public void loop() {
    if(gamepad1.cross) {
        arm_left.setPosition(arm_left_down);
    }
    if(gamepad1.circle) {
        arm_left.setPosition(arm_left_up);
    }
    if(gamepad1.triangle) {
        hor_left.setPosition(hor_left_extend);
    }
    if(gamepad1.square) {
        hor_left.setPosition(hor_left_retract);
    }
//    if(gamepad1.dpad_down) {
//        hor_right.setPosition(hor_right_retract);
//    }
//    if(gamepad1.dpad_up) {
//        hor_right.setPosition(hor_right_extend);
//    }
//    if(gamepad1.dpad_left) {
//        arm_right.setPosition(arm_right_up);
//    }
//    if(gamepad1.dpad_right) {
//        arm_right.setPosition(arm_right_down);
//    }
//    if(gamepad1.right_bumper) {
//        wrist_left.setPosition(wrist_left_down);
//    }
//    if(gamepad1.left_bumper) {
//        wrist_left.setPosition(wrist_left_up);
//    }
//    if(gamepad1.right_trigger > 0) {
//        wrist_right.setPosition(wrist_right_down);
//    }
//    if(gamepad1.left_trigger > 0) {
//        wrist_right.setPosition(wrist_right_up);
//    }










}

}

