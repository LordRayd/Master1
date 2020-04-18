CFLAGS=-mavx2 -mfma

output= matmul
nb_loops = 5

all: $(output)
	./$(output) $(nb_loops)

$(output): $(output).c
	gcc $(CFLAGS) -o $(output) $(output).c
	
clean:
	rm -f $(output)

	