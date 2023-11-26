package frc.trigon.robot.music;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.List;

public class PlayFromMidiFileCommand extends Command {
    private final FalconsPlayer falconsPlayer = FalconsPlayer.getInstance();
    private final MidiFile midiFile;
    private double startTimestamp = 0;

    public PlayFromMidiFileCommand(Songs song) {
        this(new MidiFile(song.getPath(), song.trackIndex));
    }

    public PlayFromMidiFileCommand(MidiFile midiFile) {
        this.midiFile = midiFile;
        addRequirements(falconsPlayer);
    }

    @Override
    public void initialize() {
        startTimestamp = Timer.getFPGATimestamp();
        midiFile.resetTrack();
    }

    @Override
    public void execute() {
        final List<Double> notes = midiFile.getFrequencies(Timer.getFPGATimestamp() - startTimestamp);
        falconsPlayer.setFrequencies(notes);
    }

    @Override
    public void end(boolean interrupted) {
        falconsPlayer.stop();
    }

    @Override
    public boolean isFinished() {
        return midiFile.getLengthSeconds() <= Timer.getFPGATimestamp() - startTimestamp;
    }
}
