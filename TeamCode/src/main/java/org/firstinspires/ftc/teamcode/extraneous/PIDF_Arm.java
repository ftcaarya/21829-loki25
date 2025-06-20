package org.firstinspires.ftc.teamcode.extraneous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

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

        elevator_left = hardwareMap.get(DcMotorEx.class, "elevator_left");
        elevator_right = hardwareMap.get(DcMotorEx.class, "elevator_right");



    }

    @Override
    public void loop () {
        controller_left.setPID(p, i, d);
        controller_right.setPID(p, i, d);

        int leftPos = elevator_left.getCurrentPosition();
        int rightPos = elevator_right.getCurrentPosition();
        double pid_left = controller_left.calculate(leftPos, target);
        double pid_right = controller_right.calculate(rightPos, target);

        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power_left = pid_left + ff;
        double power_right = pid_right + ff;

        elevator_left.setPower(power_left);
        elevator_right.setPower(power_right);

        telemetry.addData("Right Pos", rightPos);
        telemetry.addData("Left Pos", leftPos);
        telemetry.addData("Target", target);
        telemetry.addData("Left Power", power_left);
        telemetry.addData("Right Power", power_right);
        telemetry.update();



    }

}
