import java.util.*;
import java.io.*;

public class Brainfuck {
    private static final int MEMORY_SIZE = 30000;
    private int[] memory;
    private int memoryPointer;
    private String brainfuckCode;
    private Scanner in;

    public Brainfuck(String brainfuckCode) {
        this();
        this.brainfuckCode = brainfuckCode;
    }

    public Brainfuck() {
        initMemory((int) MEMORY_SIZE);
        this.in = new Scanner(System.in);
    }

    public void setBrainFuckCode(String brainfuckCode) {
        this.brainfuckCode = brainfuckCode;
    }

    public int[] getMemory() {
        return this.memory;
    }

    private void initMemory(int memoryLimit) {
        memoryPointer = 0;
        memory = new int[memoryLimit];
    }

    private void processOutput(Object o) {
        System.out.println(String.valueOf(o));
    }

    private void getCellInput() {
        memory[memoryPointer] = in.next().charAt(0);
    }

    private void displayCellContent() {
        System.out.print((char)(memory[memoryPointer]));
    }

    private void moveRight() {
        ++memoryPointer;
    }
    private void moveLeft() {
        --memoryPointer;
    }

    private void incrementCellContent() {
        ++memory[memoryPointer];
        if (memory[memoryPointer] > 255) {
            memory[memoryPointer] = 0;
        }
    }

    private void decrementCellContent() {
        --memory[memoryPointer];
        if (memory[memoryPointer] < 0) {
            memory[memoryPointer] = 255;
        }
    }

    public void evaluate() {
        int foundOpeningBracketAtIndex;
        Deque<Integer> queue = new LinkedList<>();
        assert brainfuckCode.length() >= 1;
        for (int i = 0; i < brainfuckCode.length(); ++i) {
            char command = brainfuckCode.charAt(i);
            if (command == ',') {
                getCellInput();
            } else if (command == '.') {
                displayCellContent();
            } else if (command == '>') {
                moveRight();
            } else if (command == '<') {
                moveLeft();
            } else if (command == '+') {
                incrementCellContent();
            } else if (command == '-') {
                decrementCellContent();
            } else if (command == '[') {
                foundOpeningBracketAtIndex = i;
                queue.offerFirst(foundOpeningBracketAtIndex);
            } else if (command == ']') {
                int pos = queue.peekFirst();
                if (memory[memoryPointer] > 0) {
                    i = pos;
                } else if (memory[memoryPointer] == 0){
                    queue.pollFirst();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Brainfuck bf = new Brainfuck();
        bf.setBrainFuckCode(bf.in.next());
        bf.evaluate();
    }
}
