
// TO DO: JavaDocs
import java.util.*;

/**
 * This instruction class is used to spilt all elements reading from the text
 * and it also deletes the special characters in order to easily coding in the
 * next steps.
 * 
 * @author
 *
 */
public class Instruction {
    /**
     * The offset means the number at the very beginning of each instruction.
     */
    private int offset;
    /**
     * The opcode means the second part of the instruction.
     */
    private String opcode;
    /**
     * put the parameters in a integer array list and it means the remaining integer
     * elements.
     */
    private ArrayList<Integer> parameters;

    /**
     * split the instruction and count how many parts does it have the offset is the
     * integer of the first part of the instruction the opcode is the second string
     * part of the instruction the parameters are the remaining integer parts if it
     * has There are 0 or 1 or 2 parameters in each instruction.
     * 
     * @param s the whole instruction contains offset,opcode and may has parameters
     */
    public Instruction(String s) {

        s = s.trim();
        parameters = new ArrayList<Integer>();

        String[] tokens = s.split("[:|,|\\s]+");
        int count = 1;
        for (String token : tokens) {
            String item = token.trim();
            if (item.length() == 0) {
                System.out.println("blank item");
                continue;
            }
            if (count == 1) {
                offset = Integer.valueOf(item);
            } else if (count == 2) {
                opcode = item;
            } else if (count == 3) {
                parameters.add(Integer.valueOf(item));
            } else if (count == 4) {
                parameters.add(Integer.valueOf(item));
            } else {
                throw new RuntimeException("Illegal format: " + item);
            }
            count++;
        }

    }

    /**
     * Describe the instructions part by part in String format.
     * 
     * @return a string description of the instruction's offset, opcode and
     *         parameters if it has
     */
    public String toString() {
        String s = offset + ": " + opcode + " ";
        if (parameters != null) {
            for (int param : parameters) {
                s += param + " ";
            }
        }
        return s;
    }

    /**
     * get the offset (first integer part of the instruction).
     * 
     * @return offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * get the opcode (second string part of the instruction).
     * 
     * @return opcode
     */
    public String getOpcode() {
        return opcode;
    }

    /**
     * get the number of the parameters(the remaining integer part of the
     * instruction) may have 0, 1, or 2.
     * 
     * @return the number of the parameter
     */
    public int getNumParameters() {
        return parameters.size();
    }

    /**
     * get the first integer parameter if it has.
     * 
     * @return the first parameter
     */
    public int getParam1() {
        if (getNumParameters() < 1) {
            throw new RuntimeException("instruction takes zero parameters");
        }
        return parameters.get(0);
    }

    /**
     * get the second integer parameter if it has.
     * 
     * @return the second parameter
     */
    public int getParam2() {
        if (getNumParameters() < 2) {
            throw new RuntimeException("instruction takes zero or one parameters");
        }
        return parameters.get(1);
    }

    /**
     * use main to test how it the instruction works and easy for us to understand
     * this class.
     * 
     * @param args main method.
     */
    public static void main(String args[]) {

        Instruction ins = new Instruction("0: iconst_2");
        if ((ins.getOffset() == 0) && (ins.getOpcode().equals("iconst_2")) && (ins.getNumParameters() == 0)) {
            System.out.println("Yay1");
        }

        ins = new Instruction("21 : bipush         6");
        if ((ins.getOffset() == 21) && (ins.getOpcode().equals("bipush")) && (ins.getNumParameters() == 1)
                && (ins.getParam1() == 6)) {
            System.out.println("Yay2");
        }

        ins = new Instruction("40:iinc 4, 1");
        if ((ins.getOffset() == 40) && (ins.getOpcode().equals("iinc")) && (ins.getNumParameters() == 2)
                && (ins.getParam1() == 4) && (ins.getParam2() == 1)) {
            System.out.println("Yay3");
        }
    }

}