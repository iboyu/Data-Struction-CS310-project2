// TO DO: add your implementation and JavaDocs.

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * This class is used to realize interpreter functions. First, it needs to read
 * text files from some location and record every instructions in a list.
 * Second, evaluate the instructions part by part and translate instructions in
 * order to realize the basic calculate, if-loop, jump functions.
 * 
 * @author RongLian Yuan
 *
 */
public class Interpreter {

    // We will test your implementation by running "java Interpreter [inputFile]" and checking your output
    // which should be one or more integer values separated by a single space (the outcome(s) of the "print" instruction)
    // (see the "Testing" section in the project description document)

    // Below are hints that you might want to follow

    // Phase1:
    // create an attribute of type Stack<Integer>

    // Phase2:
    // create an attribute of type HashMap<Integer, Integer> to map the index of a
    // variable to its value
    // create an attribute of type HashMap<Integer, Node<Instruction>> to map the
    // offset of an instruction to its corresponding node in the LList

    // public static LList<Instruction> readFile(String filename) throws IOException
    //
    // parse the instructions in fileName and store them in a LList<Instruction>
    // (each as a Node<Instruction>)
    // return the resulting LList<Instruction>
    // In the list, the instructions must follow the same order as in the input file
    //
    // (Note: Do not use "dummy nodes" since this is Java and not C)
    /**
     * This method is to read every instructions from the text and record them in a
     * list. In order to realize that function, it needs to use scanner function. If
     * the content has next content (next is not null), continue record every
     * instruction to the list.
     * 
     * @param filename the location of the text
     * @return the list with all instructions recorded
     * @throws IOException If the file location does not exist or if the file does
     *                     not exist or the location is not correct. etc, throw to
     *                     the exception.
     */
    public static LList<Instruction> readFile(String filename) throws IOException {

        File myFileName = new File(filename);
        Scanner s = new Scanner(myFileName);

        LList<Instruction> myList = new LList<Instruction>();

        while (s.hasNext()) {
            String a = s.nextLine();
            Instruction myInstruction = new Instruction(a);
            Node<Instruction> newNode = new Node<Instruction>(myInstruction);
            myList.insertLast(newNode);

        }

        s.close();

        return myList;
    }

    // public void evaluateInstructions(LList<Instruction> list)
    //
    // traverse and evaluate the list of instructions
    //
    // Hints:
    // depending on the instruction at hand you might need to
    // a) push a value on the stack
    // b) fetch the value from HashMap<Integer, Integer> then push it on the stack
    // c) pop a value off the stack and store it in HashMap<Integer, Integer>
    // d) to pop the stack and store the value in HashMap<Integer, Integer>
    // e) etc.
    //
    // HashMap<Integer, Node<Instruction>> is only needed when evaluating the
    // following instructions:
    // goto location, if_icmpeq location, if_icmpne location, if_icmpge location,
    // if_icmpgt location,
    // if_icmple location, if_icmplt location, ifne location
    /**
     * This is the main method to realize the Interpreter function. First, we need
     * to go through every instruction and separate two parts: the very beginning
     * integer off set and the remaining instruction. Create a hash map to record
     * every off set and the remaining instruction. Second, use split to separate he
     * remaining part of the instruction. It has 32 different combination.Analyze
     * each them by setting while-loop, if-loop and equal method. One of them is
     * about "istore" and "iload" etc. In order to realize this function, we need to
     * create another hash map and store them with key and value(key and values are
     * the remaining of the instruction) Third, according the instruction, do
     * calculation, jump, store etc.For the calculate part, I create a private
     * method in order to make the code shorter and easy to read. Finally, if the
     * instruction reached "return", or reached "print",or reached the end, end the
     * program and print the result.
     * 
     * @param list this list composed with all instructions
     */
    public void evaluateInstructions(LList<Instruction> list) {

        Stack<Integer> myStack = new Stack<Integer>();

        HashMap<Integer, Integer> myNum = new HashMap<Integer, Integer>();
        HashMap<Integer, Node<Instruction>> myInstruction = new HashMap<Integer, Node<Instruction>>();

        Node<Instruction> firstNode = list.getFirst();

        int order = firstNode.getValue().getOffset();

        while (firstNode != null) {
            myInstruction.put(order, firstNode);
            if (firstNode.getNext() != null) {
                firstNode = firstNode.getNext();
                order = firstNode.getValue().getOffset();
            }
            if (firstNode.getNext() == null) {
                order = firstNode.getValue().getOffset();
                myInstruction.put(order, firstNode);
                firstNode = firstNode.getNext();
                break;
            }
        }

        firstNode = list.getFirst();
        String[] array = firstNode.getValue().getOpcode().split("_"); // array = {inconst, 1}
        String instructions = array[0];// inconst

        while (firstNode != null) {

            if (array.length >= 2) {

                if (instructions.equals("if")) {

                    String comContent = array[1];
                    if (comContent.equals("icmpeq")) {
                        int a = myStack.pop();
                        int b = myStack.pop();
                        int c = firstNode.getValue().getParam1();
                        if (b == a) {
                            firstNode = myInstruction.get(c);
                            array = firstNode.getValue().getOpcode().split("_");
                            instructions = array[0];
                            continue;
                        }
                    }
                    if (comContent.equals("icmpne")) {
                        int a = myStack.pop();
                        int b = myStack.pop();
                        int c = firstNode.getValue().getParam1();
                        if (b != a) {
                            firstNode = myInstruction.get(c);
                            array = firstNode.getValue().getOpcode().split("_");
                            instructions = array[0];
                            continue;
                        }
                    }
                    if (comContent.equals("icmpge")) {
                        int a = myStack.pop();
                        int b = myStack.pop();
                        int c = firstNode.getValue().getParam1();
                        if (b >= a) {
                            firstNode = myInstruction.get(c);
                            array = firstNode.getValue().getOpcode().split("_");
                            instructions = array[0];
                            continue;
                        }
                    }
                    if (comContent.equals("icmpgt")) {
                        int a = myStack.pop();
                        int b = myStack.pop();
                        int c = firstNode.getValue().getParam1();
                        if (b > a) {
                            firstNode = myInstruction.get(c);
                            array = firstNode.getValue().getOpcode().split("_");
                            instructions = array[0];
                            continue;
                        }
                    }
                    if (comContent.equals("icmple")) {
                        int a = myStack.pop();
                        int b = myStack.pop();
                        int c = firstNode.getValue().getParam1();
                        if (b <= a) {
                            firstNode = myInstruction.get(c);
                            array = firstNode.getValue().getOpcode().split("_");
                            instructions = array[0];
                            continue;

                        }
                    }
                    if (comContent.equals("icmplt")) {
                        int a = myStack.pop();
                        int b = myStack.pop();
                        int c = firstNode.getValue().getParam1();
                        if (b < a) {
                            firstNode = myInstruction.get(c);
                            array = firstNode.getValue().getOpcode().split("_");
                            instructions = array[0];
                            continue;
                        }
                    }

                }

                else {

                    int numbers = Integer.parseInt(array[1]);// 1

                    if (instructions.equals("iconst")) {
                        myStack.push(numbers);
                    }

                    if (instructions.equals("istore")) {
                        int num = myStack.pop();
                        myNum.put(numbers, num);
                    }
                    if (instructions.equals("iload")) {
                        myStack.push(myNum.get(numbers));
                    }

                }
            }

            if (array.length == 1) {

                if (instructions.equals("bipush")) {
                    int numbers = firstNode.getValue().getParam1();
                    myStack.push(numbers);

                }

                if (instructions.equals("iinc")) {
                    int num1 = firstNode.getValue().getParam1();
                    int num2 = firstNode.getValue().getParam2();
                    int num3 = myNum.get(num1) + num2;
                    myNum.put(num1, num3);
                }

                if (instructions.equals("iadd") || instructions.equals("imul") || instructions.equals("idiv")
                        || instructions.equals("isub") || instructions.equals("irem")) {
                    int a = myStack.pop();
                    int b = myStack.pop();
                    int c = calculate(a, b, instructions);
                    myStack.push(c);

                }

                if (instructions.equals("print")) {
                    System.out.print(myStack.pop());
                    System.out.print(" ");

                }

                if (instructions.equals("goto")) {
                    int d = firstNode.getValue().getParam1();
                    firstNode = myInstruction.get(d);
                    array = firstNode.getValue().getOpcode().split("_");
                    instructions = array[0];
                    continue;
                }

                if (instructions.equals("ifne")) {
                    int a = myStack.pop();
                    int b = firstNode.getValue().getParam1();
                    if (a != 0) {
                        firstNode = myInstruction.get(b);
                        array = firstNode.getValue().getOpcode().split("_");
                        instructions = array[0];
                        continue;
                    }
                }

                if (instructions.equals("istore")) {
                    int num = myStack.pop();
                    int numbers = firstNode.getValue().getParam1();
                    myNum.put(numbers, num);
                }

                if (instructions.equals("iload")) {
                    int numbers = firstNode.getValue().getParam1();
                    myStack.push(myNum.get(numbers));
                }

            }

            if (firstNode.getNext() != null) {
                firstNode = firstNode.getNext();
                array = firstNode.getValue().getOpcode().split("_"); // array = {inconst, 1}
                instructions = array[0];// inconst
            }
            if (firstNode.getNext() == null) {
                break;
            }
        }

    }

    /**
     * This is the private method I create to make sure the body code sorts of
     * shorter and easy to read.
     * 
     * @param a        pop the first number of the stack
     * @param b        pop the second number of the stack
     * @param operator get the match the operator to see if they are
     *                 add,mul,div,isub,irem.
     * @return the result of the calculation.
     */
    private int calculate(int a, int b, String operator) {
        int result = 0;
        switch (operator) {
            case "iadd":
                result = b + a;
                break;
            case "imul":
                result = b * a;
                break;
            case "idiv":
                result = b / a;
                break;
            case "isub":
                result = b - a;
                break;
            case "irem":
                result = b % a;
                break;
            default:
                break;
        }

        return result;
    }

    /**
     * This class is used to test the class.
     * 
     * @param args main method
     * @throws IOException throw the exception
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java Interpreter [filename]");
            System.exit(0);
        }

        try {
            LList<Instruction> input = readFile(args[0]);
            new Interpreter().evaluateInstructions(input);
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        /*
         * // to test the readFile() method, do something similar to below try {
         * LList<Instruction> input1 = readFile("..\\Inputs_part1\\Input0.txt"); if
         * (input1.listToString().
         * equals("0: iconst_1  1: iconst_2  2: iadd  3: print  6: return")) {
         * System.out.println("Yay1"); } } catch(IOException e) {
         * System.out.println(e.toString()); e.printStackTrace(); }
         */

    }
}