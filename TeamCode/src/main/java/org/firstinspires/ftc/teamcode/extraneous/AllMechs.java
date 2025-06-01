package org.firstinspires.ftc.teamcode.extraneous;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.vision.EnhancedColorDetectionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class AllMechs {
    public DcMotor frontLeft, rearLeft, rearRight, frontRight;
    public ColorSensor colorSensor;
    public Gamepad testGamepad;
    public IMU imu;

    public DcMotor intake;
    public Servo pooper;

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



    public AllMechs(HardwareMap hardwareMap, int left, int right) {
        frontLeft = hardwareMap.get(DcMotor.class, "left front");
        rearLeft = hardwareMap.get(DcMotor.class, "left rear");
        rearRight = hardwareMap.get(DcMotor.class, "right rear");
        frontRight = hardwareMap.get(DcMotor.class, "right front");

        // Change this
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        rearRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);

        colorSensor = hardwareMap.get(ColorSensor.class, "color sensor");

        intake = hardwareMap.get(DcMotor.class, "intake motor");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        pooper = hardwareMap.get(Servo.class, "pooper");


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()
        );

        camera = OpenCvCameraFactory.getInstance().createWebcam(
                hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId
        );

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
                () -> right // the left dividing line, in this case the right third of the frame
        );

        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .build();
    }

    public class CheckColorRed implements Action {


        double redColor = (double) colorSensor.red() / 2;
        double greenColor = (double) colorSensor.green() / 2;
        double blueColor = (double) colorSensor.blue() / 2;

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (colorSensor.red() > colorSensor.green() + 50 && colorSensor.red() > colorSensor.blue() + 50) {
                pooper.setPosition(0);
                intake.setPower(0);
                return false;
            } else if (greenColor > blueColor && redColor > blueColor) {
                pooper.setPosition(0);
                intake.setPower(0);
                return false;
            } else {
                pooper.setPosition(1);
                intake.setPower(1);
                return true;
            }

        }
    }

    public Action checkColorRed() {
        return new CheckColorRed();
    }

    public Action checkColor() {
        double redColor = (double) colorSensor.red() / 2;
        double greenColor = (double) colorSensor.green() / 2;
        double blueColor = (double) colorSensor.blue() / 2;


        if (colorSensor.red() > colorSensor.green() + 50 && colorSensor.red() > colorSensor.blue() + 50) {
            return new ParallelAction(
                    new InstantAction(() -> testGamepad.rumbleBlips(1)),
                    new InstantAction(() ->  testGamepad.setLedColor(255, 0, 0, 5000))
            );
        } else if (greenColor > blueColor && redColor > blueColor) {
            return new ParallelAction(
                    new InstantAction(() -> testGamepad.rumbleBlips(2)),
                    new InstantAction(() -> testGamepad.setLedColor(230, 230, 0, 5000))
            );

        } else if (colorSensor.blue() > colorSensor.red() + 50 && blueColor > greenColor) {
            return new ParallelAction(
                    new InstantAction(() -> testGamepad.rumbleBlips(3)),
                    new InstantAction(() -> testGamepad.setLedColor(0, 0, 225, 5000))
            );
        } else {
            return new InstantAction(() -> testGamepad.stopRumble());
        }
    }


}
