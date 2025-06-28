package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.extraneous.ActionSchedular;
import org.firstinspires.ftc.teamcode.extraneous.DetermineBarnacle;

@Autonomous(name = "Sample Side Auto")
public class SampleAuto extends MasterAuto {
    ActionSchedular actionSchedular;

    SampleAuto() {
        actionSchedular = new ActionSchedular();
    }

    protected Action onRun() {
        TrajectoryActionBuilder builder = drive.actionBuilder(new Pose2d(-35, -63, Math.toRadians(90)));

        builder = wholeSequence(builder);


        return builder.build();
    }

    private TrajectoryActionBuilder wholeSequence(TrajectoryActionBuilder builder) {
        builder = builder

                .setReversed(false)
                .splineToLinearHeading(new Pose2d(-52, -52 , Math.toRadians(45)), -Math.PI)
                // deposit the sample that is with the robot
                .strafeToLinearHeading(new Vector2d(-54, -45), Math.toRadians(90))
                // Add the Anonymous Action for the Vision.
                .stopAndAdd(
                        new SequentialAction(
                                new InstantAction(DetermineBarnacle::generateTargetTrajectory),
                                telemetryPacket -> {
                                    actionSchedular.run();
                                    return DetermineBarnacle.getTargetSampleTrajectory().run(telemetryPacket);
                                }
                        )
                );

        return builder;
    }




}
