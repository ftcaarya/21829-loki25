package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting extends MeepMeep {
    private static final int fps = 60;

    public MeepMeepTesting(int windowSize) {
        super(windowSize, fps);
    }

    public static void main(String[] args) {
        MeepMeepTesting meepMeepTesting = new MeepMeepTesting(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeepTesting)
                .setDimensions(18, 18)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(80, 80, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        // red left side
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-35, -61, Math.toRadians(90)))
                //drop preloaded
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(-52, -52 , Math.toRadians(45)), -Math.PI)

                .strafeToLinearHeading(new Vector2d(-54, -45), Math.toRadians(90))

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
                .setTangent(Math.toRadians(180 + 120))
                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(180 + 120))
                        .setTangent((Math.PI - Math.atan((18/14.5))))
                        .splineToLinearHeading(new Pose2d(-54, -44, (Math.PI - Math.atan((18/14.5)))), (Math.PI - Math.atan((18/14.5))))
                .setTangent(Math.toRadians(180 + 120))
                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(180 + 120))
                .setTangent(Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-59, -50, Math.toRadians(90)), Math.toRadians(180))
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-47, -6), Math.toRadians(0))
//                .setTangent(0)
//                .splineToConstantHeading(new Vector2d(-54, -6), Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(-30, -9, Math.toRadians(0)), Math.toRadians(0))
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(-47, -6, Math.toRadians(90)), Math.toRadians(180))
                        .setTangent(Math.toRadians(180))
                                .splineToConstantHeading(new Vector2d(-59, -50), Math.toRadians(270))
                        .setTangent(0)
                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(45)), Math.toRadians(270))
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(-30, -55, Math.toRadians(0)), Math.toRadians(0))


                .build());

//        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(10, -66, Math.toRadians(270)))
//                //drop preloaded
//                .strafeToLinearHeading(new Vector2d(0, -31), Math.toRadians(270))
//                // get first sample
//                .setReversed(false)
//                .splineToSplineHeading(new Pose2d(33, -38, Math.toRadians(40)), Math.toRadians(45))
//                // drop first sample
//                .strafeToLinearHeading(new Vector2d(38, -40), Math.toRadians(-45))
//                // get second sample
//                .turnTo(Math.toRadians(35))
//                // drop second sample
//                .strafeToLinearHeading(new Vector2d(47, -40), Math.toRadians(270))
//                // pick specimen 1
//                .strafeToConstantHeading(new Vector2d(47, -47.5))
//                //drop specimen 1
//                .strafeToLinearHeading(new Vector2d(-5, -29), Math.toRadians(270))
//                //pick specimen 2
//                .setReversed(false)
//                .splineToLinearHeading(new Pose2d(47, -42, Math.toRadians(270)), Math.PI/9)
//                .strafeToConstantHeading(new Vector2d(47, -47.5), new TranslationalVelConstraint(20.0))
//                //drop specimen 2
//                .strafeToLinearHeading(new Vector2d(-9, -29), Math.toRadians(270))
//                .strafeToConstantHeading(new Vector2d(5, -28), new TranslationalVelConstraint(20.0))
//                .build());

        // red right side
//        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(10, -63, Math.toRadians(270)))
//                .strafeTo(new Vector2d(0, -34))
//                .setReversed(false)
//                .splineToLinearHeading(new Pose2d(48, -38, Math.toRadians(90)), Math.PI / 4)
//                .strafeToLinearHeading(new Vector2d(48, -55), Math.toRadians(90))
//                .setReversed(false)
//                .splineToLinearHeading(new Pose2d(40, -10, Math.toRadians(0)), Math.PI/2)
//                .strafeToLinearHeading(new Vector2d(58, -10), Math.toRadians(270))
//                .strafeToConstantHeading(new Vector2d(58, -55))
////                .strafeToConstantHeading(new Vector2d(58, -38))
////                .strafeToConstantHeading(new Vector2d(58, -55))
////                .setReversed(true)
////                .splineToSplineHeading(new Pose2d(50, -30, Math.toRadians(0)), Math.PI/2)
////                .splineToLinearHeading(new Pose2d(63, -10, Math.toRadians(-90)), Math.PI/6)
////                .strafeToConstantHeading(new Vector2d(62, -55))
//                .strafeToLinearHeading(new Vector2d(25, -58), Math.toRadians(0))
//                .strafeToLinearHeading(new Vector2d(40, -58), Math.toRadians(0))
//                .strafeToLinearHeading(new Vector2d(0, -34), Math.toRadians(-90))
//                .strafeToLinearHeading(new Vector2d(40, -58), Math.toRadians(0))
//                .strafeToLinearHeading(new Vector2d(0, -34), Math.toRadians(-90))
//                .strafeToLinearHeading(new Vector2d(40, -58), Math.toRadians(0))
//                .strafeToLinearHeading(new Vector2d(0, -34), Math.toRadians(-90))
//                .build());


        // testing
//        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(10, -63, Math.toRadians(270)))
//                .strafeToLinearHeading(new Vector2d(0,-36.5),Math.toRadians(270))
//                // next thing
//                .strafeTo(new Vector2d(20,-36.5))
//                .splineToSplineHeading(new Pose2d(46,-13,Math.toRadians(270)),Math.toRadians(0))
//                .strafeToLinearHeading(new Vector2d(46,-53),Math.toRadians(270))
//                .waitSeconds(0.5)
//                .strafeTo(new Vector2d(44,-13))
//                .splineToLinearHeading(new Pose2d(55,-11.5,Math.toRadians(270)),Math.toRadians(0))
//                .strafeToLinearHeading(new Vector2d(55,-53),Math.toRadians(270))
//                .strafeToLinearHeading(new Vector2d(55,-33),Math.toRadians(0))
//                // next thing
//                .strafeToLinearHeading(new Vector2d(36,-56.5),Math.toRadians(0))
//                //next thing
//                .strafeToLinearHeading(new Vector2d(0,-36.5),Math.toRadians(270))
//                // next thing
//                .strafeToLinearHeading(new Vector2d(36,-56.5),Math.toRadians(0))
//                // next thing
//                .strafeToLinearHeading(new Vector2d(0,-36.5),Math.toRadians(270))
//                // next thiing
//                .strafeToLinearHeading(new Vector2d(36,-56.5),Math.toRadians(0))
//                // wef
//                .strafeToLinearHeading(new Vector2d(0,-36.5),Math.toRadians(270))
//                // samplep get
//                .strafeToLinearHeading(new Vector2d(36,-56.5),Math.toRadians(0))
//                .build());







        meepMeepTesting.setBackground(MeepMeepTesting.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }


}