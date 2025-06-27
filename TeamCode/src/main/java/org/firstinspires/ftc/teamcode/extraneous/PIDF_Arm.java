package org.firstinspires.ftc.teamcode.extraneous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;

import java.util.Base64;

@Config
@TeleOp(name = "PID")
public class PIDF_Arm extends OpMode {

    private PIDController controller_left;
    private PIDController controller_right;

    public static double p = 0, i = 0, d = 0;
    public static double f = 0;
    public static int target = 0;
    private final double ticks_in_degree = 700/180.0;
    private DcMotorEx elevator_left;
    private DcMotorEx elevator_right;




    @Override
    public void init () {
        controller_left = new PIDController(p, i, d);
        controller_right = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        elevator_left = hardwareMap.get(DcMotorEx.class, "left elevator");
        elevator_right = hardwareMap.get(DcMotorEx.class, "right elevator");
        elevator_left.setDirection(DcMotorSimple.Direction.REVERSE);
        elevator_right.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void loop () {



        controller_left.setPID(p, i, d);
        controller_right.setPID(p, i, d);

        int leftPos = elevator_left.getCurrentPosition();
        int rightPos = elevator_right.getCurrentPosition();
        double pid_right = controller_right.calculate(rightPos, target);

        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power_right = pid_right + ff;

        elevator_left.setPower(power_right);
        elevator_right.setPower(power_right);

        telemetry.addData("Right Pos", rightPos);
        telemetry.addData("Left Pos", leftPos);
        telemetry.addData("Target", target);
        telemetry.addData("Right Power", power_right);
        telemetry.update();



    }

}
