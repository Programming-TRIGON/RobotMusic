package frc.trigon.robot.music;

import com.ctre.phoenix6.controls.MusicTone;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.Constants;

import java.util.List;

public class FalconsPlayer extends SubsystemBase {
    private static final FalconsPlayer INSTANCE = new FalconsPlayer();
    private final TalonFX[] falcons = Constants.FALCONS;

    public static FalconsPlayer getInstance() {
        return INSTANCE;
    }

    private FalconsPlayer() {
    }

    public Command getStopPlayingCommand() {
        return new StartEndCommand(
                this::stop,
                () -> {
                },
                this
        );
    }

    void setFrequencies(List<Double> frequencies) {
        if (frequencies.isEmpty()) {
            stop();
            return;
        }

        int offset = 0;

        for (int i = 0; i < falcons.length; i++) {
            int offsettedIndex = i - offset;
            if (offsettedIndex >= frequencies.size()) {
                offset = i;
                offsettedIndex = 0;
            }

            final double frequency = frequencies.get(offsettedIndex);
            falcons[i].setControl(new MusicTone(frequency));
        }
    }

    void stop() {
        for (TalonFX currentFalcon : falcons)
            currentFalcon.stopMotor();
    }
}
