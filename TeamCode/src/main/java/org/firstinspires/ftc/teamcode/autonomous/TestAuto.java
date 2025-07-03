package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.extraneous.ActionSchedular;
import org.firstinspires.ftc.teamcode.extraneous.DetermineBarnacle;

import java.util.function.DoubleSupplier;

@Config
@Autonomous(name = "Testing Vision values for auto")
public class TestAuto extends MasterAuto {
    double minArea, left, right;

    ActionSchedular actionSchedular;

    DetermineBarnacle determineBarnacle;
    Pose2d sampleStartPose = new Pose2d(-35, -63, Math.toRadians(90));
    Pose2d specimenStartPose;

    TestAuto() {
        actionSchedular = new ActionSchedular();
    }

    @Override
    protected void onInit() {
        determineBarnacle = new DetermineBarnacle(minArea, left, right, sampleStartPose);
    }

    @Override
    protected Action onRun() {
        return null;
    }
}
