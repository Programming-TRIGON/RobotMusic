package frc.trigon.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Constants {
    public static final String DEPLOY_PATH = Filesystem.getDeployDirectory().getAbsolutePath() + "/";
    public static final String MIDI_FILE_EXTENSION = ".mid";

    private static final int CONTROLLER_ID = 0;
    public static final CommandXboxController CONTROLLER = new CommandXboxController(CONTROLLER_ID);
    public static final TalonFX[] FALCONS = {
            new TalonFX(1), new TalonFX(2), new TalonFX(3), new TalonFX(4),
            new TalonFX(5), new TalonFX(6), new TalonFX(7), new TalonFX(8)
    };
}
