/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="turnTest", group="Arcade")
//@Disabled
public class ShooterTest extends LinearOpMode {

    DrivetrainHardware mDrive = new DrivetrainHardware();

    static final double power = 1;

    @Override
    public void runOpMode()
    {

        mDrive.init(hardwareMap);
        waitForStart();

        while (opModeIsActive())
        {
        }
    }


    public void doDrive(double x, double y, double t) {

        double magnitude = Math.sqrt(x * x + y * y);
        double distance = Math.atan2(y, x);
        double turn = t;


        //calculate power with angle and magnitude

        double backLeft = magnitude * Math.sin(distance - Math.PI / 4) + turn;
        double backRight = magnitude * Math.sin(distance + Math.PI / 4) - turn;
        double frontLeft = magnitude * Math.sin(distance + Math.PI / 4) + turn;
        double frontRight = magnitude * Math.sin(distance - Math.PI / 4) - turn;

//in case the power to the motors gets over 1(as 1 is the maximum motor value, and in order to strafe diagonally, wheels have to move at different speeds), we divide
//them all by the highest value. This keeps them under 1, but in respect with each other

        if (magnitude != 0) {
            double divisor = 0;
            divisor = Math.max(Math.abs(backLeft), Math.abs(backRight));
            divisor = Math.max(divisor, Math.abs(frontLeft));
            divisor = Math.max(divisor, Math.abs(frontRight));

            telemetry.addData("divisor: ", divisor);

            backLeft = magnitude * (backLeft / divisor);
            backRight = magnitude * (backRight / divisor);
            frontLeft = magnitude * (frontLeft / divisor);
            frontRight = magnitude * (frontRight / divisor);
        }

        telemetry.addData("Magnitude: ", magnitude);
        telemetry.addData("turn: ", turn);
        telemetry.addData("backLeft: ", backLeft);
        telemetry.addData("backRight: ", backRight);
        telemetry.addData("frontLeft: ", frontLeft);
        telemetry.addData("frontRight: ", frontRight);
        telemetry.update();
        mDrive.BL.setPower(backRight);
        mDrive.BR.setPower(backLeft);
        mDrive.FL.setPower(frontRight);
        mDrive.FR.setPower(frontLeft);
    }

    public void turn5()
    {
        mDrive.FL.setPower(0.3);
        mDrive.BL.setPower(0.3);
        mDrive.FR.setPower(-0.3);
        mDrive.BR.setPower(-0.3);
        sleep(150);
        mDrive.FL.setPower(0);
        mDrive.BL.setPower(0);
        mDrive.FR.setPower(0);
        mDrive.BR.setPower(0);
    }


}
