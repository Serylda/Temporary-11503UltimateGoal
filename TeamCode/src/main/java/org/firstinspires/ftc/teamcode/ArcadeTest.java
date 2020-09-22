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

@TeleOp(name="Test", group="Arcade")
//@Disabled
public class ArcadeTest extends LinearOpMode {

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
                mDrive.FL.setPower(power);
                mDrive.BL.setPower(power);
                mDrive.BR.setPower(power);
                mDrive.FR.setPower(power);
            }
            else if (gamepad1.dpad_down)
            {
                mDrive.FL.setPower(-power);
                mDrive.BL.setPower(-power);
                mDrive.BR.setPower(-power);
                mDrive.FR.setPower(-power);
            }
            else if (gamepad1.dpad_left)
            {
                mDrive.FL.setPower(-power);
                mDrive.BL.setPower(power);
                mDrive.FR.setPower(power);
                mDrive.BR.setPower(-power);
            }
            else if (gamepad1.dpad_right)
            {
                mDrive.FL.setPower(power);
                mDrive.BL.setPower(-power);
                mDrive.FR.setPower(-power);
                mDrive.BR.setPower(power);
            }
            else if(gamepad1.left_bumper)
            {
                mDrive.FL.setPower(-power / 2);
                mDrive.BL.setPower(-power / 2);
                mDrive.FR.setPower(power / 2);
                mDrive.BR.setPower(power / 2);
            }
            else if(gamepad1.right_bumper)
            {
                mDrive.FL.setPower(power / 2);
                mDrive.BL.setPower(power / 2);
                mDrive.FR.setPower(-power / 2);
                mDrive.BR.setPower(-power / 2);
            }
            else
            {
                freeze();
            }

        }
    }

    public void freeze()
    {
        mDrive.FL.setPower(0);
        mDrive.BL.setPower(0);
        mDrive.BR.setPower(0);
        mDrive.FR.setPower(0);
    }
}
