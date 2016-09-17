all:	brainfuck clean

brainfuck:
	javac Brainfuck.java
	java -cp . Brainfuck

clean:
	rm *.class && rm *~
