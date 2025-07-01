package org.firstinspires.ftc.teamcode.extraneous;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.internal.opmode.BlocksClassFilter;
import org.firstinspires.ftc.teamcode.extraneous.hardware.testing;
import org.firstinspires.ftc.teamcode.vision.EnhancedColorDetectionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class AllMechs {
    public PIDController controller_left;
    public  PIDController controller_right;

    public MultipleTelemetry telemetry;

    public Servo claw, rotate, wrist_left, wrist_right, arm_left, arm_right, hor_left, hor_right, hold, pooper;

    public static final double hor_left_extend = 0.53;
    public static final double hor_left_retract = 0.25;

    public static final double hor_right_extend = 47;
    public static final double hor_right_retract = .75;

    public static final double POOPER_BLOCK = 1;
    public static final double POOPER_PASS = .4;

    public static final double CLAW_OPEN = 1;
    public static final double CLAW_CLOSE = 0.25;

    public static double wrist_left_down = 1;
    public static double wrist_left_up = 0;

    public static double wrist_right_down = 0;
    public static double wrist_right_up = 1;
    public static double intake_left_down = 0;
    public static double intake_left_up = 1;

    public static double intake_right_down = 0;
    public static double intake_right_up = .5;

    public static final double arm_left_up = .5;
    public static final double arm_left_down = .5;

    public static final double arm_right_up = .5;
    public static final double arm_right_down = .5;

    public static final double rotate_hor = 0.22;
    public static final double rotate_vert = 0.55;

    public static double intake_up = 1;

    public static double p = 0, i = 0, d = 0;
    public static double f = 0;
    public static int target = 0;
    public final double ticks_in_degree = 700/180.0;

    public DcMotor frontLeft, rearLeft, rearRight, frontRight;
    public ColorSensor colorSensor;
    public Gamepad testGamepad;
    public IMU imu;

    public DcMotorEx vert_left, vert_right;
    public ElapsedTime vertTimer;

    public DcMotor intake;

    public VisionPortal visionPortal;
    public EnhancedColorDetectionProcessor colourMassDetectionProcessor;

    public OpenCvCamera camera;
    // 640, 360
    public static final int CAMERA_WIDTH = 1280, CAMERA_HEIGHT = 360;

    //change this later
    public static final double objectWidthRealWorld = 3.5;
    public static final double focalLength = 200 * 8.5 / 3.5;

    double cX = 0;
    double cY = 0;
    static double width = 0;

    public static double output;

    public static int vertTarget;
    public static double lastVertError = 0;
    public static double pv = 0, iv = 0, dv = 0, fv = 0, integralSumVert = 0;



    public AllMechs(HardwareMap hardwareMap, int left, int right) {
        claw = hardwareMap.get(Servo.class, "claw");
        rotate = hardwareMap.get(Servo.class, "rotate");

        wrist_left = hardwareMap.get(Servo.class, "wrist left");
        wrist_right = hardwareMap.get(Servo.class, "wrist right");

        arm_left = hardwareMap.get(Servo.class, "arm left");
        arm_right = hardwareMap.get(Servo.class, "arm right");

        hor_left = hardwareMap.get(Servo.class, "hor left");
        hor_right = hardwareMap.get(Servo.class, "hor right");

        pooper = hardwareMap.get(Servo.class, "pooper");

        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        hold = hardwareMap.get(Servo.class, "hold");


        frontRight = hardwareMap.get(DcMotor.class, "front right");
        rearRight = hardwareMap.get(DcMotor.class, "rear right");
        frontLeft = hardwareMap.get(DcMotor.class, "front left");
        rearLeft = hardwareMap.get(DcMotor.class, "rear left");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        vert_left = hardwareMap.get(DcMotorEx.class, "left elevator");
        vert_right = hardwareMap.get(DcMotorEx.class, "right elevator");

        vert_right.setDirection(DcMotorSimple.Direction.REVERSE);
        vert_left.setDirection(DcMotorSimple.Direction.REVERSE);

        vert_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        vert_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        vert_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        vert_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        colorSensor = hardwareMap.get(ColorSensor.class, "color sensor");

        vertTimer = new ElapsedTime();

//        colorSensor = hardwareMap.get(ColorSensor.class, "color sensor");

        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        pooper = hardwareMap.get(Servo.class, "pooper");


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()
        );

        camera = OpenCvCameraFactory.getInstance().createWebcam(
                hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId
        );
        controller_left = new PIDController(p, i, d);
        controller_right = new PIDController(p, i, d);

//        IMU imu = hardwareMap.get(IMU.class, "imu");
//        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
//                RevHubOrientationOnRobot.LogoFacingDirection.UP,
//                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
//
//        imu.initialize(parameters);

        double lowerH = 150; // the lower hsv threshold for your detection
        double upperH = 180; // the upper hsv threshold for your detection
        double minArea = 100; // the minimum area for the detection to consider for your prop

        colourMassDetectionProcessor = new EnhancedColorDetectionProcessor(
                lowerH,
                upperH,
                () -> minArea,
                () -> left, // the left dividing line, in this case the left third of the frame
                () -> right, // the left dividing line, in this case the right third of the frame
                EnhancedColorDetectionProcessor.StartPositions.SAMPLE
        );

        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .build();
    }



    public Action setVertTarget(int target) {
        return new InstantAction(() -> vertTarget = target);
    }

    public class UpdateVertPID implements Action {


        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            controller_left.setPID(p, i, d);
            controller_right.setPID(p, i, d);

            int leftPos = vert_left.getCurrentPosition();
            int rightPos = vert_right.getCurrentPosition();
            double pid_left = controller_left.calculate(leftPos, target);
            double pid_right = controller_right.calculate(rightPos, target);

            double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

            double power_left = pid_left + ff;
            double power_right = pid_right + ff;

            vert_left.setPower(power_left);
            vert_right.setPower(power_right);

            telemetry.addData("Right Pos", rightPos);
            telemetry.addData("Left Pos", leftPos);
            telemetry.addData("Target", target);
            telemetry.addData("Left Power", power_left);
            telemetry.addData("Right Power", power_right);
            telemetry.update();

            return true;
        }
    }

    public Action updateVertPID() {
        return new UpdateVertPID();
    }


    public Action intakeDown() {
        return new InstantAction(() -> hold.setPosition(.8));
    }

    public Action intakeUp() {
        return new InstantAction(() -> hold.setPosition(.3));
    }

    public Action checkColorRed(Gamepad gamepad) {
        return p -> {
            hold.setPosition(.8);

            if (colorSensor.red() > colorSensor.green() + 50 && colorSensor.red() > colorSensor.blue() + 50) {
                pooper.setPosition(POOPER_BLOCK);
                intake.setPower(0);
                return false;
            } else if ((colorSensor.green() > colorSensor.blue()) && (colorSensor.red() > colorSensor.blue())) {
                pooper.setPosition(POOPER_BLOCK);
                intake.setPower(0);
                return false;
            } else if (colorSensor.blue() > colorSensor.green() + 50 && colorSensor.blue() > colorSensor.red() + 50) {
                pooper.setPosition(POOPER_PASS);
                intake.setPower(.65);
                return true;
            } else if (gamepad.square) {
                pooper.setPosition(POOPER_BLOCK);
                hold.setPosition(.3);
                intake.setPower(0);
                return false;
            } else {
                pooper.setPosition(POOPER_BLOCK);
                intake.setPower(.6);
                return true;
            }
        };
    }

//    public class CheckColorRed implements Action {
//
//        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
//
//            hold.setPosition(.8);
//
//            if (colorSensor.red() > colorSensor.green() + 50 && colorSensor.red() > colorSensor.blue() + 50) {
//                pooper.setPosition(POOPER_BLOCK);
//                intake.setPower(0);
//                return false;
//            } else if ((colorSensor.green() > colorSensor.blue()) && (colorSensor.red() > colorSensor.blue())) {
//                pooper.setPosition(POOPER_BLOCK);
//                intake.setPower(0);
//                return false;
//            } else if (colorSensor.blue() > colorSensor.green() + 50 && colorSensor.blue() > colorSensor.red() + 50) {
//                pooper.setPosition(POOPER_PASS);
//                intake.setPower(.65);
//                return true;
//            } else if (gamepad1.square) {
//                pooper.setPosition(POOPER_BLOCK);
//                hold.setPosition(.3);
//                intake.setPower(0);
//                return false;
//            } else {
//                pooper.setPosition(POOPER_BLOCK);
//                intake.setPower(.6);
//                return true;
//            }
//
//        }
//
//    }
//
//    public Action checkColorRed() {
//        return new CheckColorRed();
//    }

    public Action stopIntake() {
        return new ParallelAction(
                new InstantAction(() -> pooper.setPosition(POOPER_BLOCK)),
                new InstantAction(() -> intake.setPower(0))
        );
    }

    public Action armUp() {
        return new ParallelAction(
                new InstantAction(() -> arm_left.setPosition(arm_left_up)),
                new InstantAction(() -> arm_right.setPosition(arm_right_up))
        );
    }

}
