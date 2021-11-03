package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class GearHoundsTestingCode extends OpMode {
    private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;

    @Override
    public void init() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");


        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addLine("Initialization Complete");
        telemetry.addLine("Press Play to Start");
        telemetry.update();
    }

    @Override
    public void loop() {
        /*telemetry.clearAll();

        telemetry.update();*/

        double px = gamepad1.left_stick_x;
        if (Math.abs(px) < 0.05) px = 0;
        double py = -gamepad1.left_stick_y;
        if (Math.abs(py) < 0.05) py = 0;
        double pa = -(gamepad1.right_stick_x*(.70));
        if (Math.abs(pa) < 0.05) pa = 0;
        double plf = -px + py - pa;
        double plb = px + py + -pa;
        double prf = -px + py + pa;
        double prb = px + py + pa;
        double max = Math.max(1.0, Math.abs(plf));
        max = Math.max(max, Math.abs(plb));
        max = Math.max(max, Math.abs(prf));
        max = Math.max(max, Math.abs(prb));
        plf /= max;
        plb /= max;
        prf /= max;
        prb /= max;
        leftFront.setPower(plf);
        leftBack.setPower(plb);
        rightFront.setPower(prf);
        rightBack.setPower(prb);
        while (gamepad1.right_trigger > 0.2)    {
            leftFront.setPower(1);
            leftBack.setPower(-1);
            rightFront.setPower(-1);
            leftBack.setPower(1);
        }

        while (gamepad1.left_trigger > 0.2)       {
            leftFront.setPower(-1);
            leftBack.setPower(1);
            rightFront.setPower(1);
            leftBack.setPower(-1);
        }

        while (gamepad1.right_bumper)    {
            leftFront.setPower(0.5);
            leftBack.setPower(-0.5);
            rightFront.setPower(-0.5);
            leftBack.setPower(0.5);
        }

        while (gamepad1.left_bumper)       {
            leftFront.setPower(-0.5);
            leftBack.setPower(0.5);
            rightFront.setPower(0.5);
            leftBack.setPower(-0.5);
        }

    }
}
