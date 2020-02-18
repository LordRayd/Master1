CFLAGS=-std=gnu99 -O3 -mavx2 -mfma #-fopt-info-vec-all 
steps=50
prog= nbody

test: $(prog)
	./$(prog) $(steps)

$(prog) : $(prog).c
	gcc $(CFLAGS) -o $(prog) $(prog).c -lm
	
clean:
	rm -f $(prog)
	

	