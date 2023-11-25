# RobotMusic
This code is used to make your falcons play some music!
# Playing a song
You'll first need to have a specific song's MIDI file to play it.  
After that, you need to put it inside the `deploy` folder and create a new enum element with the name of the file and the track of the file you want to play.  
Now, you can create a new `PlayFromMidiFileCommand` command, that will play the midi file when you schedule it!  
Change the `FALCONS` array in the constants to match the falcons you want to use. 
Note that this project is configured to team 5990 in the `wpilib_preferences.json`.  
# Preconfigured songs
This project comes with a couple of songs preconfigured songs, that are bound to controller buttons, which are the following:  
* Never Gonna Give You Up (Imaging getting rickrolled by a robot LOL)  
* The Entertainer  
* The 3rd Movement of Moonlight Sonata (Little Jonathan was too hard)  
* Turkish March  
* Megalovania  
* JoJo theme song  
* A part of Tornado Of Souls  
* Rush E  
* Crazy Frog  
* Dancing Queen  
* Revenge   
# Disclamer
You may modify this project as you wish! We only created this to have fun, and we encourage anyone to use it, fork it, recreate it, and have fun with it!  
The only files in this project that are playing music are the files in the `music` folder and some of the constants inside the `Constants` file. The rest is just some example usage.  
