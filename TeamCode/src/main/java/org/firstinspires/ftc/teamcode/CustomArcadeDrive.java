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

@TeleOp(name="Arcade", group="Arcade")
//@Disabled
public class CustomArcadeDrive extends LinearOpMode {

    Mecanum mDrive = new Mecanum();
    
    static final double power = 1;

    @Override
    public void runOpMode()
    {

        mDrive.init(hardwareMap);
        waitForStart();

        while (opModeIsActive())
        {
            if (gamepad1.dpad_up)
            {
                mDrive.LF.setPower(power);
                mDrive.LB.setPower(power);
                mDrive.RB.setPower(power);
                mDrive.RF.setPower(power);
            }
            else if (gamepad1.dpad_down)
            {
                mDrive.LF.setPower(-power);
                mDrive.LB.setPower(-power);
                mDrive.RB.setPower(-power);
                mDrive.RF.setPower(-power);
            }
            else if (gamepad1.dpad_left)
            {
                mDrive.LF.setPower(-power);
                mDrive.LB.setPower(power);
                mDrive.RF.setPower(power);
                mDrive.RB.setPower(-power);
            }
            else if (gamepad1.dpad_right)
            {
                mDrive.LF.setPower(power);
                mDrive.LB.setPower(-power);
                mDrive.RF.setPower(-power);
                mDrive.RB.setPower(power);
            }
            else if(gamepad1.left_bumper)
            {
                mDrive.LF.setPower(-power / 2);
                mDrive.LB.setPower(-power / 2);
                mDrive.RF.setPower(power / 2);
                mDrive.RB.setPower(power / 2);
            }
            else if(gamepad1.right_bumper)
            {
                mDrive.LF.setPower(power / 2);
                mDrive.LB.setPower(power / 2);
                mDrive.RF.setPower(-power / 2);
                mDrive.RB.setPower(-power / 2);
            }
            else
            {
                freeze();
            }

        }
    }

    public void freeze()
    {
        mDrive.LF.setPower(0);
        mDrive.LB.setPower(0);
        mDrive.RB.setPower(0);
        mDrive.RF.setPower(0);
    }
}
