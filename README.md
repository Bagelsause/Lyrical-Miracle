# Lyrical Miracle

A passion project inspired by a Computer Science assignment from 2016. The original assignment was to do something visual/generally-art related and mix it with Computer Science. I decided on a topic similar to this one, but eventually this came out as an almost "side effect". This project focuses on using a Markov model to analyze a variety of songs from a specific artist and genre and to create it's own song based on those inputs. It also tags both the inputs and outputs with Parts of Speech, so you can see how it changes through the Markov Model. Also, it is completely written in Java (except for Stanford-POSTagger and the Textfiles) as I wanted to understand how to use Swing a bit more.

## How to use and how it works

If you'd like to run the program, here are the steps for doing so!

1. Run the file titled "Gui.java" in eclipse (Not absolutely sure if it works well with other IDEs)
2. Input where you're going to be saving the output files
3. Input the name of a song and artist 
4. Click on "Check for song". It grabs the lyrics of the song, the top songs made by that artist, and the top 100 songs of that genre
5. Input the KGram Value (how big the kernel should be for the word generation) and how many characters it should return
6. Click on the "Run the program" button, and analyzes it using a Markov Model. (The markov model is used to calculate the probability of a word appearing after another word)
7. After the markov model is done, it ships it over to the Parts Of Speech tagger, which goes through and identifies Nouns, Verbs, ETC and counts them 
8. The final results and analysis are saved at the location the user specified beforehand, allowing them to see the inputs and output (if the "Save Analysis Files" button was selected)

## Questions

If you have any questions about how to use it/how to adapt it, feel free to let me know in the issues tab!
