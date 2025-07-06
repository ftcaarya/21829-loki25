package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;

import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

public class MeepMeepTesting extends MeepMeep {
    private static final int fps = 60;

    public MeepMeepTesting(int windowSize) {
        super(windowSize, fps);
    }

    public static void main(String[] args) {
        MeepMeepTesting meepMeepTesting = new MeepMeepTesting(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeepTesting)
                .setDimensions(14, 15)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(80, 80, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        // red left side
//        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-35, -63, Math.toRadians(90)))
                //drop preloaded
//                .setReversed(false)
//                .splineToLinearHeading(new Pose2d(-52, -52 , Math.toRadians(45)), -Math.PI)
//                .strafeToLinearHeading(new Vector2d(-54, -45), Math.toRadians(90))

                /*
                    left spike barnacle detected
                 */
//                .turnTo(Math.toRadians(65))
//                .setTangent(0)
//                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), -Math.PI)
//                .setTangent(0)
//                .splineToLinearHeading(new Pose2d(-56, -44, Math.toRadians(95)), -Math.toRadians(180))
//                .setTangent(0)
//                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), -Math.PI)
//                .setTangent(Math.toRadians(0))
//                .splineToLinearHeading(new Pose2d(-45, -20, Math.toRadians(90)), Math.toRadians(90))
//                .setTangent(Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(-28, -9, Math.toRadians(0)), Math.toRadians(0))
//                .setReversed(true)
//                .splineToLinearHeading(new Pose2d(-45, -20, Math.toRadians(90)), Math.toRadians(-90))
//                .setTangent(Math.toRadians(-90))
//                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(180))

                /*
                middle spike barnacle detected
                 */

//                                .turnTo(Math.toRadians(65))
//                        .setTangent(180 + 65)
//                                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), -Math.PI)
//                                .setTangent(0)
//                                .splineToLinearHeading(new Pose2d(-56, -44, Math.toRadians(130)), -Math.toRadians(180))
//                        .setReversed(true)
//                                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(-90))
//                                .setTangent(Math.toRadians(0))
//                                .splineToLinearHeading(new Pose2d(-40, -20, Math.toRadians(90)), Math.toRadians(90))
//                                .setTangent(Math.toRadians(90))
//                                .splineToLinearHeading(new Pose2d(-28, -9, Math.toRadians(0)), Math.toRadians(0))
//                                .setTangent(Math.PI)
//                                .splineToLinearHeading(new Pose2d(-40, -20, Math.toRadians(90)), Math.toRadians(-90))
//                                .setTangent(Math.toRadians(-90))
//                                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(180))



                /*
                    right spike barnacle detected
                 */
//                        .turnTo(Math.toRadians(100))
//                .setTangent(Math.toRadians(180 + 120))
//                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(180 + 120))
//                        .setTangent((Math.PI - Math.atan((18/14.5))))
//                        .splineToLinearHeading(new Pose2d(-54, -44, (Math.PI - Math.atan((18/14.5)))), (Math.PI - Math.atan((18/14.5))))
//                .setTangent(Math.toRadians(180 + 120))
//                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(180 + 120))
//                .setTangent(Math.toRadians(180))
//                .splineToSplineHeading(new Pose2d(-59, -50, Math.toRadians(90)), Math.toRadians(180))
//                .setTangent(Math.toRadians(90))
//                .splineToConstantHeading(new Vector2d(-47, -6), Math.toRadians(0))
//                .splineToLinearHeading(new Pose2d(-30, -9, Math.toRadians(0)), Math.toRadians(0))
//                .setTangent(Math.toRadians(180))
//                .splineToLinearHeading(new Pose2d(-47, -6, Math.toRadians(90)), Math.toRadians(180))
//                        .setTangent(Math.toRadians(180))
//                                .splineToConstantHeading(new Vector2d(-59, -50), Math.toRadians(270))
//                        .setTangent(0)
//                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(270))
//                .setTangent(Math.toRadians(0))
//                .splineToLinearHeading(new Pose2d(-30, -55, Math.toRadians(0)), Math.toRadians(0))
//

//                .build());


//      red right side!!!
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(10, -63, Math.toRadians(270)))
//                //drop preloaded
                        .setTangent(Math.toRadians(110))
                .splineToConstantHeading(new Vector2d(0, -35), Math.toRadians(110))
                                .setTangent(Math.toRadians(300))
                .splineToLinearHeading(new Pose2d(55, -45, Math.toRadians(90)), Math.toRadians(45))


                /*
                    left spike barnacle detected
                 */
//                                .turnTo(Math.toRadians(80))
//                                .setTangent(Math.toRadians(290))
//                                .splineToLinearHeading(new Pose2d(59, -52, Math.toRadians(100)), Math.toRadians(290))
//                                .setTangent(Math.PI/2)
//                                .splineToLinearHeading(new Pose2d(59, -42, Math.toRadians(60)), Math.toRadians(90))
//                                .setTangent(Math.toRadians(180))
//                                .splineToLinearHeading(new Pose2d(57, -62, Math.toRadians(-270)), Math.toRadians(270))
//                                .setTangent(Math.toRadians(150))
//                                .splineToLinearHeading(new Pose2d(-5, -35, Math.toRadians(270)), Math.toRadians(100))
//                                .setTangent(Math.toRadians(320))
//                                .splineToConstantHeading(new Vector2d(40, -62), Math.toRadians(270))
//                                .setTangent(Math.toRadians(320-180))
//                                .splineToConstantHeading(new Vector2d(3, -35), Math.toRadians(320-180))
//                                .setTangent(Math.toRadians(320))
//                                .splineToConstantHeading(new Vector2d(40, -62), Math.toRadians(270))
//                                .setTangent(Math.toRadians(150))
//                                .splineToConstantHeading(new Vector2d(-3, -35), Math.toRadians(150))
//                                .setTangent(Math.toRadians(230))
//                                .splineToLinearHeading(new Pose2d(-37, -35, Math.toRadians(180)), Math.toRadians(180))



                /*
                    middle spike barnacle detected
                 */
//                                .turnTo(Math.toRadians(110))
//                                .setTangent(Math.toRadians(270))
//                                .splineToLinearHeading(new Pose2d(54, -55, Math.toRadians(60)), Math.toRadians(270))
//                                .setTangent(Math.toRadians(60))
//                                .splineToConstantHeading(new Vector2d(59, -42), Math.toRadians(60))
//                                .setTangent(Math.toRadians(180 + 60))
//                                .splineToLinearHeading(new Pose2d(54, -62, Math.toRadians(90)), Math.toRadians(270))
//                                .setTangent(Math.toRadians(140))
//                                .splineToLinearHeading(new Pose2d(-5, -35, Math.toRadians(270)), Math.toRadians(100))
//                                .setTangent(Math.toRadians(320))
//                                .splineToConstantHeading(new Vector2d(40, -62), Math.toRadians(270))
//                                .setTangent(Math.toRadians(320-180))
//                                .splineToConstantHeading(new Vector2d(3, -35), Math.toRadians(320-180))
//                                .setTangent(Math.toRadians(320))
//                                .splineToConstantHeading(new Vector2d(40, -62), Math.toRadians(270))
//                                .setTangent(Math.toRadians(150))
//                                .splineToConstantHeading(new Vector2d(-3, -35), Math.toRadians(150))
//                                .setTangent(Math.toRadians(230))
//                                .splineToLinearHeading(new Pose2d(-20, -35, Math.toRadians(180)), Math.toRadians(180))

                /*
                    right spike barnacle detected
                 */
//                .turnTo(Math.toRadians(80))
//                .setTangent(Math.toRadians(270))
//                .splineToLinearHeading(new Pose2d(55, -55, Math.toRadians(105)), Math.toRadians(270))
//                .setTangent(Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(55, -45, Math.toRadians(105)), Math.toRadians(90))
//                .setTangent(Math.toRadians(270))
//                .splineToLinearHeading(new Pose2d(55, -59, Math.toRadians(90)), Math.toRadians(270))
//                .setTangent(Math.toRadians(140))
//                .splineToLinearHeading(new Pose2d(-5, -35, Math.toRadians(270)), Math.toRadians(100))
//                .setTangent(Math.toRadians(320))
//                .splineToConstantHeading(new Vector2d(40, -62), Math.toRadians(270))
//                .setTangent(Math.toRadians(320-180))
//                .splineToConstantHeading(new Vector2d(3, -35), Math.toRadians(320-180))
//                .setTangent(Math.toRadians(320))
//                .splineToConstantHeading(new Vector2d(40, -62), Math.toRadians(270))
//                .setTangent(Math.toRadians(150))
//                .splineToConstantHeading(new Vector2d(-3, -35), Math.toRadians(150))
//



                .build());







        meepMeepTesting.setBackground(MeepMeepTesting.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }


}