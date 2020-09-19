package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Mecanum {
    
    public DcMotor LF; //Motor 0
    public DcMotor LB; //Motor 1
    public DcMotor RB; //Motor 2
    public DcMotor RF; //Motor 3
    
    HardwareMap hwmap;
    
    public Mecanum()
    {
        LF = null;
        LB = null;
        RB = null;
        RF = null;
        hwmap = null;
    }
    
    public void init(HardwareMap h)
    {
        hwmap = h;

        LF = hwmap.get(DcMotor.class, "LM0");
        LB = hwmap.get(DcMotor.class, "LM1");
        RB = hwmap.get(DcMotor.class, "RM2");
        RF = hwmap.get(DcMotor.class, "RM3");

        LF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
        RF.setPower(0);

        LF.setDirection(DcMotor.Direction.REVERSE);
        LB.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.FORWARD);
        RF.setDirection(DcMotor.Direction.FORWARD);

    }


    public void freeze()
    {
        LF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
        RF.setPower(0);
    }
    
}
