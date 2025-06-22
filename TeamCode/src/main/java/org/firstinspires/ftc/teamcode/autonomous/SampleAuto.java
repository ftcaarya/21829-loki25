package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public class SampleAuto extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d initialPose = new Pose2d(-35, -63, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);


        Action dropPreloaded = drive.actionBuilder(initialPose)
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(-52, -52 , Math.toRadians(45)), -Math.PI)
                .strafeToLinearHeading(new Vector2d(-54, -45), Math.toRadians(90))
                .build();



    }
}
