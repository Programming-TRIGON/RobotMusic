package frc.trigon.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.trigon.robot.music.FalconsPlayer;
import frc.trigon.robot.music.PlayFromMidiFileCommand;
import frc.trigon.robot.music.Songs;

public class RobotContainer {
    private final FalconsPlayer falconsPlayer = FalconsPlayer.getInstance();
    private final CommandXboxController controller = Constants.CONTROLLER;

    public RobotContainer() {
        bindCommands();
    }

    private void bindCommands() {
        falconsPlayer.setDefaultCommand(falconsPlayer.getStopPlayingCommand());

        controller.y().toggleOnTrue(new PlayFromMidiFileCommand(Songs.Revenge));
        controller.x().toggleOnTrue(new PlayFromMidiFileCommand(Songs.Undertale_Megalovania));
        controller.a().toggleOnTrue(new PlayFromMidiFileCommand(Songs.The_Entertainer));
        controller.b().toggleOnTrue(new PlayFromMidiFileCommand(Songs.Sonata14));
        controller.leftBumper().toggleOnTrue(new PlayFromMidiFileCommand(Songs.Turkish_March_Mozart));
        controller.rightBumper().toggleOnTrue(new PlayFromMidiFileCommand(Songs.Crazy_Frog));
    }
}
