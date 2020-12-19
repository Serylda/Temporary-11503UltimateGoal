package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevSPARKMini;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

public class DrivetrainHardware {
    
    public DcMotor FL; //Motor 0 - LM0
    public DcMotor BL; //Motor 1 - LM1
    public DcMotor BR; //Motor 2 - RM2
    public DcMotor FR; //Motor 3 - RM3
    public DcMotor Pivot; //Motor 0
    public DcMotor FlyWheel1; //Motor 1
    public DcMotor FlyWheel2; //Motor 2
    public DcMotor Arm; //Motor 3

    public Servo ringHopper, claw;
    public CRServo intake1, intake2;
    /*
    public DistanceSensor distanceFrontLeft;
    public DistanceSensor distanceFrontRight;
    public DistanceSensor distanceBackLeft;
    public DistanceSensor distanceBackRight;
    */
    public RevBlinkinLedDriver blink;

    //public VoltageSensor voltageSensor;

    HardwareMap hardwareMap;
    
    public DrivetrainHardware()
    {
        FL = null;
        BL = null;
        BR = null;
        FR = null;
        FlyWheel1 = null;
        FlyWheel2 = null;
        Pivot = null;
        intake1 = null;
        intake2 = null;
        hardwareMap = null;
        ringHopper = null;
        Arm = null;
        claw = null;
        blink = null;
        /*distanceFrontRight = null;
        distanceFrontLeft = null;
        distanceBackRight = null;
        distanceBackLeft = null;
*/
    }
    
    public void init(HardwareMap h)
    {
        hardwareMap = h;

        BR = hardwareMap.get(DcMotor.class, "M0");
        FR = hardwareMap.get(DcMotor.class, "M1");
        FL = hardwareMap.get(DcMotor.class, "M2");
        BL = hardwareMap.get(DcMotor.class, "M3");
        FlyWheel1 = hardwareMap.get(DcMotor.class, "FW1");
        FlyWheel2 = hardwareMap.get(DcMotor.class, "FW2");
        Pivot = hardwareMap.get(DcMotor.class, "Pivot");
        Arm = hardwareMap.get(DcMotor.class, "Arm");

        ringHopper = hardwareMap.get(Servo.class, "hopper"); //1
        claw = hardwareMap.get(Servo.class, "claw");
        intake1 = hardwareMap.get(CRServo.class, "intake1");
        intake2 = hardwareMap.get(CRServo.class, "intake2");
        /*
        distanceFrontLeft = hardwareMap.get(DistanceSensor.class, "bus0");
        distanceFrontRight = hardwareMap.get(DistanceSensor.class, "bus1");
        distanceBackLeft = hardwareMap.get(DistanceSensor.class, "bus2");
        distanceBackRight = hardwareMap.get(DistanceSensor.class, "bus3");
        */
        //blink = hardwareMap.get(RevSPARKMini.class, "blink");
        blink = hardwareMap.get(RevBlinkinLedDriver.class, "blink");
        blink.setPattern(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_WHITE);
        //blink.;
        freeze();

        FL.setDirection(DcMotor.Direction.FORWARD);
        BL.setDirection(DcMotor.Direction.FORWARD);
        BR.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.REVERSE);
        FlyWheel1.setDirection(DcMotor.Direction.REVERSE);
        FlyWheel2.setDirection(DcMotor.Direction.REVERSE);
        Pivot.setDirection(DcMotor.Direction.FORWARD);
        Arm.setDirection(DcMotor.Direction.FORWARD);

    }


    public void freeze()
    {
        FL.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
        FR.setPower(0);
        FlyWheel1.setPower(0);
        FlyWheel2.setPower(0);
        Pivot.setPower(0);
        Arm.setPower(0);
    }

    public double getVoltage()
    {
        double result = Double.POSITIVE_INFINITY;
        for (VoltageSensor sensor : hardwareMap.voltageSensor) {
            double voltage = sensor.getVoltage();
            if (voltage > 0) {
                result = Math.min(result, voltage);
            }
        }
        return result;
    }

    public void resetEncoders()
    {
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public double getEncoderAvg() {
        double output = 0;
        int encoderCount = 0;
        boolean[] encoderIsNotPluggedIn = new boolean[4];

        if (Math.abs(BR.getCurrentPosition()) != 0) {
            encoderCount++;
            output += Math.abs(BR.getCurrentPosition());
        } else encoderIsNotPluggedIn[0] = true;

        if (Math.abs(FR.getCurrentPosition()) != 0) {
            encoderCount++;
            output += Math.abs(FR.getCurrentPosition());
        } else encoderIsNotPluggedIn[1] = true;

        if (Math.abs(FL.getCurrentPosition()) != 0) {
            encoderCount++;
            output += Math.abs(FL.getCurrentPosition());
        } else encoderIsNotPluggedIn[2] = true;

        if (Math.abs(BL.getCurrentPosition()) != 0) {
            encoderCount++;
            output += Math.abs(BL.getCurrentPosition());
        } else encoderIsNotPluggedIn[3] = true;

        if (encoderCount == 0)
            return 0;
        else
            return output/encoderCount;
    }
}
