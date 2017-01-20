package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.hdwr.QWERTY;
import org.firstinspires.ftc.teamcode.util.Color;
import org.firstinspires.ftc.teamcode.util.Direction;
import org.firstinspires.ftc.teamcode.util.Stop;

@Autonomous(name = "Blue Team Alliance", group = "Autonomous")
public class BlueAuto extends OpMode {
    private QWERTY qwerty;
    private int state;

    @Override
    public void init() {
        this.qwerty = new QWERTY(hardwareMap);
        qwerty.setSpeed(0.35);
        state = 0;
    }
    @Override
    public void loop() {
        telemetry.addData("State:",state);
        telemetry.addData("Position:",qwerty.debug("Position"));
        telemetry.addData("Heading:", qwerty.debug("Heading"));
        telemetry.addData("Color:", qwerty.debug("Color"));
        telemetry.addData("Left Color:", qwerty.debug("ColorRawLeft"));
        telemetry.addData("Right Color:", qwerty.debug("ColorRawRight"));
        switch (state) {
            case 0:
                qwerty.pushCoord(25,0);
                qwerty.pushCoord(100,-60);
                state++;
                break;
            case 1:
                if(qwerty.iterateGTG(Direction.FORWARD))
                    state++;
            case 2:
                if (qwerty.iterateLineSeek())
                    state++;
                break;
            case 3:
                qwerty.iterateLineFollow();
                if (qwerty.iterateWallSeek())
                    state++;
                break;
            case 4:
                if(qwerty.iteratePushButton(Color.BLUE))
                    state++;
                break;
            case 5:
                qwerty.pushCoord(130,-60);
                state++;
                break;
            case 6:
                if(qwerty.iterateGTG(Direction.REVERSE))
                    state++;
                break;
            default:
                qwerty.stop();
                stop();
        }
    }
}