package org.firstinspires.ftc.teamcode.extraneous;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoProgramming {
    public Servo claw, rotate, wrist_left, wright_right, arm_left, arm_right, hor_left, hor_right, intake_left, intake_right;
    public double CLAW_OPEN = 0;
    public double CLAW_CLOSE = 0;


    public ServoProgramming(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw");
        rotate = hardwareMap.get(Servo.class, "rotate");
        wrist_left = hardwareMap.get(Servo.class, "wrist left");
        wright_right = hardwareMap.get(Servo.class, "wrist right");
        arm_left = hardwareMap.get(Servo.class, "arm left");
        arm_right = hardwareMap.get(Servo.class, "arm right");
        hor_left = hardwareMap.get(Servo.class, "hor left");
        hor_right = hardwareMap.get(Servo.class, "hor right");
        intake_left = hardwareMap.get(Servo.class, "intake left");
        intake_right = hardwareMap.get(Servo.class, "intake right");




    }

}
