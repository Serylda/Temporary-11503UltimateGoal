package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DrivetrainHardware {
    
    public DcMotor FL; //Motor 0
    public DcMotor BL; //Motor 1
    public DcMotor BR; //Motor 2
    public DcMotor FR; //Motor 3
    public DcMotor FlyWheel1; //Motor 4
    public DcMotor FlyWheel2; //Motor 5
    public DcMotor Arm; //Motor 6
    public DcMotor Intake; //Motor 7
    
    HardwareMap hardwareMap;
    
    public DrivetrainHardware()
    {
        FL = null;
        BL = null;
        BR = null;
        FR = null;
        FlyWheel1 = null;
        FlyWheel2 = null;
        Arm = null;
        Intake = null;
        hardwareMap = null;
    }
    
    public void init(HardwareMap h)
    {
        hardwareMap = h;

        FL = hardwareMap.get(DcMotor.class, "LM0");
        BL = hardwareMap.get(DcMotor.class, "LM1");
        BR = hardwareMap.get(DcMotor.class, "RM2");
        FR = hardwareMap.get(DcMotor.class, "RM3");
        //FlyWheel1 = hardwareMap.get(DcMotor.class, "FlyWheel1");
        //FlyWheel2 = hardwareMap.get(DcMotor.class, "FlyWheel2");
        //Arm = hardwareMap.get(DcMotor.class, "Arm");
        //Intake = hardwareMap.get(DcMotor.class, "Intake");

        freeze();

        FL.setDirection(DcMotor.Direction.FORWARD);
        BL.setDirection(DcMotor.Direction.FORWARD);
        BR.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.REVERSE);
        //FlyWheel1.setDirection(DcMotor.Direction.FORWARD);
        //FlyWheel2.setDirection(DcMotor.Direction.FORWARD);

    }


    public void freeze()
    {
        FL.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
        FR.setPower(0);
        //FlyWheel1.setPower(0);
        //FlyWheel2.setPower(0);
        //Arm.setPower(0);
        //Intake.setPower(0);
    }
    
}
