package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Mecanum {
    
    public DcMotor FL; //Motor 0
    public DcMotor BL; //Motor 1
    public DcMotor BR; //Motor 2
    public DcMotor FR; //Motor 3
    
    HardwareMap hwmap;
    
    public Mecanum()
    {
        FL = null;
        BL = null;
        BR = null;
        FR = null;
        hwmap = null;
    }
    
    public void init(HardwareMap h)
    {
        hwmap = h;

        FL = hwmap.get(DcMotor.class, "LM0");
        BL = hwmap.get(DcMotor.class, "LM1");
        BR = hwmap.get(DcMotor.class, "RM2");
        FR = hwmap.get(DcMotor.class, "RM3");

        FL.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
        FR.setPower(0);

        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.FORWARD);
        FR.setDirection(DcMotor.Direction.FORWARD);

    }


    public void freeze()
    {
        FL.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
        FR.setPower(0);
    }
    
}
