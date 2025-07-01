package org.firstinspires.ftc.teamcode.teleop;

import android.app.Notification;

import com.acmerobotics.roadrunner.ParallelAction;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.extraneous.ActionSchedular;
import org.firstinspires.ftc.teamcode.extraneous.AllMechs;

@TeleOp(name="TeleOp Testing")
public class TeleOpTesting extends LinearOpMode {
    AllMechs robot;
    ActionSchedular actionSchedular;


    @Override
    public void runOpMode() throws InterruptedException {
            robot = new AllMechs(hardwareMap, 0, 0);
            actionSchedular = new ActionSchedular();

            waitForStart();

            while (opModeIsActive()) {
                double y = -gamepad1.left_stick_y;
                double x = gamepad1.left_stick_x;
                double rx = gamepad1.right_stick_x;

                double frontLeftPower = (y + x + rx);
                double rearLeftPower = (y - x + rx);
                double frontRightPower = (y - x - rx);
                double rearRightPower = (y + x - rx);

                frontLeftPower = Range.clip(frontLeftPower, -1, 1);
                rearLeftPower = Range.clip(rearLeftPower, -1, 1);
                frontRightPower = Range.clip(frontRightPower, -1, 1);
                rearRightPower = Range.clip(rearRightPower, -1, 1);

                robot.frontLeft.setPower(frontLeftPower);
                robot.rearLeft.setPower(rearLeftPower);
                robot.frontRight.setPower(frontRightPower);
                robot.rearRight.setPower(rearRightPower);



                if (gamepad1.dpad_down) {
                    actionSchedular.addAction(new ParallelAction(
                            robot.intakeDown(),
                            robot.checkColorRed(gamepad1)
                    ));
                }

                if (gamepad1.dpad_up) {
                    actionSchedular.addAction(
                            new ParallelAction()
                    );
                }

                if (gamepad1.dpad_up) {
                    actionSchedular.addAction(new ParallelAction(
                            robot.intakeUp(),
                            robot.stopIntake()
                            )
                    );
                }

                if (gamepad1.dpad_up) {
                    actionSchedular.addAction(
                            robot.armUp()
                    );
                }

                if (gamepad1.dpad_up) {
                    actionSchedular.addAction(
                            robot.armUp()
                    );
                }

                actionSchedular.run();
            }



        }

    }

