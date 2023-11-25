package frc.trigon.robot.music;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MidiFile extends File {
    private final double ticksPerSecond;
    private final List<Double> frequenciesCache = new ArrayList<>();
    private final int trackIndex;
    private Sequence sequence;
    private Track track;

    public MidiFile(String pathname, int trackIndex) {
        super(pathname);
        this.trackIndex = trackIndex;
        try {
            sequence = MidiSystem.getSequence(this);
            track = sequence.getTracks().clone()[trackIndex];
            ticksPerSecond = sequence.getTickLength() / (sequence.getMicrosecondLength() / 1000000.0);
        } catch (InvalidMidiDataException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetTrack() {
        try {
            sequence = MidiSystem.getSequence(this);
        } catch (InvalidMidiDataException | IOException e) {
            throw new RuntimeException(e);
        }
        track = sequence.getTracks().clone()[trackIndex];
        frequenciesCache.clear();
    }

    public double getLengthSeconds() {
        return sequence.getMicrosecondLength() / 1000000.0;
    }

    public List<Double> getFrequencies(double timestampSeconds) {
        final long timestampTicks = secondsToTicks(timestampSeconds);

        for (int i = 0; i < track.size(); i++) {
            final MidiEvent currentEvent = track.get(i);
            if (currentEvent.getTick() > timestampTicks) {
                removeAllEventsBeforeIndex(i);
                break;
            }
            if (isNotSoundEvent(currentEvent))
                continue;

            if (isOn(currentEvent))
                frequenciesCache.add(getFrequency(currentEvent));
            else
                frequenciesCache.remove(getFrequency(currentEvent));
        }

        return frequenciesCache;
    }

    private void removeAllEventsBeforeIndex(int index) {
        for (int i = 0; i < index; i++)
            track.remove(track.get(0));
    }

    private double getFrequency(MidiEvent midiEvent) {
        if (midiEvent.getMessage() instanceof ShortMessage shortMessage)
            return getNoteFrequency(shortMessage.getData1());

        return 0;
    }

    private double getNoteFrequency(int note) {
        return 440.0 * Math.pow(2.0, (note - 69) / 12.0);
    }

    private boolean isOn(MidiEvent midiEvent) {
        if (midiEvent.getMessage() instanceof ShortMessage shortMessage)
            return shortMessage.getCommand() == ShortMessage.NOTE_ON && shortMessage.getData2() != 0;
        return false;
    }

    private boolean isNotSoundEvent(MidiEvent midiEvent) {
        if (midiEvent.getMessage() instanceof ShortMessage shortMessage)
            return shortMessage.getCommand() != ShortMessage.NOTE_ON && shortMessage.getCommand() != ShortMessage.NOTE_OFF;
        return false;
    }

    private long secondsToTicks(double seconds) {
        return (long) (seconds * ticksPerSecond);
    }
}
