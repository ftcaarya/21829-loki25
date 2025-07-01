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



                if (gamepad1.a) {
                    actionSchedular.addAction(new ParallelAction(
                            robot.intakeDown(),
                            robot.checkColorRed()
                    ));
                }


                if (gamepad1.b) {
                    actionSchedular.addAction(new ParallelAction(
                            robot.intakeUp(),
                            robot.stopIntake()
                            )
                    );
                }

                if (gamepad1.dpad_left) {
                    actionSchedular.addAction(
                            robot.armUp()
                    );
                }

                if (gamepad1.dpad_right) {
                    actionSchedular.addAction(
                            robot.armDown()
                    );
                }
                if (gamepad1.right_bumper) {
                    actionSchedular.addAction(
                            robot.clawClose()
                    );
                }
                if (gamepad1.left_bumper) {
                    actionSchedular.addAction(
                            robot.clawOpen()
                    );
                }
                if (gamepad1.dpad_up) {
                    actionSchedular.addAction(
                            robot.wristUp()
                    );
                }

                if (gamepad1.dpad_down) {
                    actionSchedular.addAction(
                            robot.wristDown()
                    );
                }
                if (gamepad1.x) {
                    actionSchedular.addAction(
                            robot.rotateHor()
                    );
                }
                if (gamepad1.y) {
                    actionSchedular.addAction(
                            robot.rotateVert()
                    );
                }
                if (gamepad2.dpad_up) {
                    actionSchedular.addAction(
                            robot.setVertTarget(1000)
                    );
                }
                if (gamepad2.dpad_down) {
                    actionSchedular.addAction(
                            robot.setVertTarget(0)
                    );
                }




                actionSchedular.run();
            }



        }

    }

