package org.firstinspires.ftc.teamcode.teleop;

import android.app.Notification;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.extraneous.ActionSchedular;
import org.firstinspires.ftc.teamcode.extraneous.AllMechs;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="TeleOp Testing")
public class TeleOpTesting extends OpMode {
    AllMechs robot;
    ActionSchedular actionSchedular;
    MecanumDrive drive;

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        robot = new AllMechs(hardwareMap, 0, 0, gamepad1, gamepad2);
        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        actionSchedular = new ActionSchedular();
    }

    @Override
    public void start() {
        runningActions.add(
                robot.updateVertPID()
        );
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();

        drive.setDrivePowers(new PoseVelocity2d(
                new Vector2d(
                        .484038 * Math.tan(1.12 * -gamepad1.left_stick_y),
                        .484038 * Math.tan(1.12 * -gamepad1.left_stick_x)
                ),
                -gamepad1.right_stick_x
        ));


        if (gamepad1.right_stick_button) {
            runningActions.add(new ParallelAction(
                    robot.intakeDown(),
                    robot.checkColorRed()
            ));
        }


        if (gamepad1.circle) {
            runningActions.add(new ParallelAction(
                            robot.intakeUp(),
                            robot.stopIntake()
                    )
            );
        }

        if (gamepad1.dpad_left) {
            runningActions.add(
                    robot.horExtend()
            );
        }

        if (gamepad1.dpad_right) {
            runningActions.add(
                    robot.horRetract()
            );
        }
        if (gamepad1.right_bumper) {
            runningActions.add(
                    robot.clawClose()
            );
        }

        if (gamepad1.left_bumper) {
            runningActions.add(
                    robot.clawOpen()
            );
        }
        if (gamepad1.cross) {
            runningActions.add(
                    robot.rotateHor()
            );
        }

        if (gamepad1.triangle) {
            runningActions.add(
                    robot.rotateVert()
            );
        }

        if (gamepad1.dpad_up) {
            runningActions.add(
                    new SequentialAction(
                            robot.setVertTarget(3000),
                            new SleepAction(2),
                            robot.armUp(),
                            robot.wristUp()
                    )

            );
        }

        if (gamepad1.dpad_down) {
            runningActions.add(
                    new SequentialAction(
                            robot.setVertTarget(0),
                            robot.armDown(),
                            robot.wristDown()
                    )
            );
        }
        if (gamepad2.dpad_down) {
            runningActions.add(
                    robot.armWait()
            );
        }
        if (gamepad2.dpad_right) {
            runningActions.add(
                    robot.setExtTarget(550)
            );
        }
        if (gamepad2.dpad_left) {
            runningActions.add(
                    robot.setExtTarget(20)
            );
        }






        List<Action> newActions = new ArrayList<>();
        for (Action action : runningActions) {
            action.preview(packet.fieldOverlay());
            if (action.run(packet)) {
                newActions.add(action);
            }
        }
        runningActions = newActions;

        dash.sendTelemetryPacket(packet);
    }
}

