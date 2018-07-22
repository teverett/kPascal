(*
	guess.pas

	Simple number guessing game in Pascal

	Written by Richard Marks <ccpsceo@gmail.com>
*)

program Guess;
uses
	crt;
const
	MAX_GUESSES = 5;
var
	myNumber, yourNumber, guesses : integer;
	guessing, playing : boolean;
	response : char;

(* main code *)
begin
	Randomize;
	playing := true;

	writeln('Guess');
	writeln('Written by Richard Marks <ccpsceo@gmail.com>');
	writeln('');
	writeln('Guess a number from 1 to 100 in 5 tries or less.');
	writeln('');
	
	(* main game loop - keep asking the user to play again after he or she wins or loses *)
	while playing do
		begin
		(* init game vars *)
		myNumber := Random(101);
		guessing := true;
		guesses := 1;
	
		(* game play - the actual game logic loop *)
		while guessing do
			begin
				(* if we run out of guesses *)
				if guesses >= MAX_GUESSES then
					begin
						writeln('Sorry, you did not guess my number fast enough! You lose!');
						guessing := false;
					end;
				(* if we have not run out of guesses *)
				if guesses < MAX_GUESSES then
					begin
						(* get the guess from the player *)
						writeln('');
						write('(', guesses, '/', MAX_GUESSES, ') What is your guess? ');
						read(yourNumber);
						(* if we guessed the number *)
						if myNumber = yourNumber then
							begin
								writeln('Hey you guessed my number in only ', guesses, ' tries! You win!');
								guessing := false;
							end
						else
							(* if we guessed too low *)
							if myNumber > yourNumber then
								begin
									writeln('Nope, sorry. ', yourNumber, ' is too low!');
								end
							else
								(* if we guessed too high *)
								if myNumber < yourNumber then
									begin
										writeln('Nope, sorry. ', yourNumber, ' is too high!');
									end;
					end;
				(* increment the guess counter *)
				guesses := guesses + 1;
			end;
			(* game is over, ask the user to play again *)
			playing := false;
			writeln('G A M E  O V E R');
			write('Would you like to play again? (Y or N) ');
			response := readkey;
			if upcase(response) = 'Y' then playing := true;
		end;
	(* game is totally over *)
	writeln('');
	writeln('Thanks for playing!');
	readln;
end.
