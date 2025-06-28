package org.firstinspires.ftc.teamcode.extraneous;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.vision.VisionPortal.CameraState.STREAMING;

import android.app.Notification;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.vision.EnhancedColorDetectionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

import java.nio.charset.CharacterCodingException;

public class DetermineBarnacle {
    private VisionPortal visionPortal;
    private EnhancedColorDetectionProcessor colourMassDetectionProcessor;
    static MecanumDrive drive;

    // HSV takes the form: (HUE, SATURATION, VALUE)
    // the domains are: ([0, 180], [0, 255], [0, 255])
//    double lowerH = 150; // the lower hsv threshold for your detection
//    double upperH = 180; // the upper hsv threshold for your detection
//    double minArea = 100; // the minimum area for the detection to consider for your prop

    static EnhancedColorDetectionProcessor.PropPositions recordedBarnaclePosition;

    private static Action targetSampleTrajectory;

    static Pose2d pose;

    DetermineBarnacle(double lowerH, double upperH, double minArea, int left, int right, Pose2d poseGiven) {
        this.pose = poseGiven;

        colourMassDetectionProcessor = new EnhancedColorDetectionProcessor(
                lowerH,
                upperH,
                () -> minArea,
                () -> left,
                () -> right
        );

        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(colourMassDetectionProcessor)
                .build();

        drive = new MecanumDrive(hardwareMap, pose);

    }


    public Action detectTarget() {
        return new Action() {

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (visionPortal.getCameraState() == STREAMING) {
                    recordedBarnaclePosition = colourMassDetectionProcessor.getRecordedPropPosition();
                } else {
                    recordedBarnaclePosition = EnhancedColorDetectionProcessor.PropPositions.UNFOUND;
                }

                visionPortal.stopLiveView();
                visionPortal.stopStreaming();

                return false;
            }
        };

    }

    public static void generateTargetTrajectory() {

        // switch through the detected cases and run separate trajectories for each of them.
        switch (recordedBarnaclePosition) {
            case LEFT:
                targetSampleTrajectory = drive.actionBuilder(pose)
                        .turnTo(Math.toRadians(65))
                        // add the intake for the right sample
                        .setTangent(0)
                        .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), -Math.PI)
                        // add the deposit action for the sample it holds
                        .setTangent(0)
                        .splineToLinearHeading(new Pose2d(-56, -44, Math.toRadians(95)), -Math.toRadians(180))
                        // add the intake for the middle sample
                        .setTangent(0)
                        .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), -Math.PI)
                        // add the deposit action for the sample it holds
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(-45, -20, Math.toRadians(90)), Math.toRadians(90))
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-28, -9, Math.toRadians(0)), Math.toRadians(0))
                        // add the intake from the submersible
                        .setReversed(true)
                        .splineToLinearHeading(new Pose2d(-45, -20, Math.toRadians(90)), Math.toRadians(-90))
                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(180))
                        // deposit the sample that it has.
                        .build();
                break;

            case MIDDLE:
                targetSampleTrajectory = drive.actionBuilder(pose)
                        .turnTo(Math.toRadians(65))
                        // intake the sample
                        .setTangent(180 + 65)
                        .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), -Math.PI)
                        // deposit the sample that is with the robot
                        .setTangent(0)
                        .splineToLinearHeading(new Pose2d(-56, -44, Math.toRadians(130)), -Math.toRadians(180))
                        // intake the sample
                        .setReversed(true)
                        .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(-90))
                        // deposit the sample that is with the robot
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(-40, -20, Math.toRadians(90)), Math.toRadians(90))
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-28, -9, Math.toRadians(0)), Math.toRadians(0))
                        // intake the sample from the sub
                        .setTangent(Math.PI)
                        .splineToLinearHeading(new Pose2d(-40, -20, Math.toRadians(90)), Math.toRadians(-90))
                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(180))
                        // deposit the sample that is with the robot
                        .build();

                break;
            case RIGHT:
                targetSampleTrajectory = drive.actionBuilder(pose)
                        .turnTo(Math.toRadians(100))
                        // intake sample
                        .setTangent(Math.toRadians(180 + 120))
                        .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(180 + 120))
                        // deposit the sample that is with the robot
                        .setTangent((Math.PI - Math.atan((18/14.5))))
                        .splineToLinearHeading(new Pose2d(-54, -44, (Math.PI - Math.atan((18/14.5)))), (Math.PI - Math.atan((18/14.5))))
                        // intake sample
                        .setTangent(Math.toRadians(180 + 120))
                        .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(180 + 120))
                        // deposit the sample that is with the robot
                        .setTangent(Math.toRadians(180))
                        .splineToSplineHeading(new Pose2d(-59, -50, Math.toRadians(90)), Math.toRadians(180))
                        .setTangent(Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(-47, -6), Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(-30, -9, Math.toRadians(0)), Math.toRadians(0))
                        // intake sample from the sub
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-47, -6, Math.toRadians(90)), Math.toRadians(180))
                        .setTangent(Math.toRadians(180))
                        .splineToConstantHeading(new Vector2d(-59, -50), Math.toRadians(270))
                        .setTangent(0)
                        .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(270))
                        // deposit the sample that is with the robot
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(-30, -55, Math.toRadians(0)), Math.toRadians(0))
                        .build();



                break;

            case UNFOUND:
                // just do all of them.

        }

    }

    public static Action getTargetSampleTrajectory() {
        return targetSampleTrajectory;
    }






}
