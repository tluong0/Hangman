# Hangman
Create a simple hangman game.
Hangman is played as follows:
1. One player chooses a secret word, then writes out a number of dashes equal to the word length.
2. The other players begin guessing letters. If a player guesses a letter that's in the word, the first player reveals all instances of that letter in the word. Otherwise, the guess is incorrect.
3. The game ends either when all the letters in the word have been revealed or when the guessers have run out of guesses.

In my game, I write the program with an idea that as you play hangman, if you guess wrong, you will draw a head then an arm and everything until you hang the man. Thus, when I prompt user for number of missed guesses, it’s actually the number of body parts they are allowed to hang before they lose. 
