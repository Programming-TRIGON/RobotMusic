package frc.trigon.robot.music;

import frc.trigon.robot.Constants;

public enum Songs {
    Never_Gonna_Give_You_Up(0),
    The_Entertainer(0),
    Moonlight_Sonata_3(0),
    Turkish_March(0),
    Undertale_Megalovania(1),
    JoJo(1),
    Tornado_Of_Souls(1),
    Rush_E(0),
    Crazy_Frog(3),
    Dancing_Queen(0),
    Revenge(1);

    final int trackIndex;

    Songs(int trackIndex) {
        this.trackIndex = trackIndex;
    }

    public String getPath() {
        return Constants.DEPLOY_PATH + name() + Constants.MIDI_FILE_EXTENSION;
    }

}
