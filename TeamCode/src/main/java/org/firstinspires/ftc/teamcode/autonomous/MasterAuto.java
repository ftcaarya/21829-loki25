package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public abstract class MasterAuto extends LinearOpMode {
    MecanumDrive drive;
    MultipleTelemetry mTelemetry;

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d initialPose = new Pose2d(-35, -63, Math.toRadians(90));
        drive = new MecanumDrive(hardwareMap, initialPose);

        mTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        Action action = onRun();

        waitForStart();

        while (opModeIsActive()) {
            Actions.runBlocking(
                    new SequentialAction(
                            action
                    )
            );

        }
    }

    protected abstract Action onRun();
}
