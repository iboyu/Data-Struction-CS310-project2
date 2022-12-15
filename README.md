# Data-Structure-II-Java-project2
Mini Interpreter

Overview:
There are two major phases to this project:
Phase1: 
1) Provide a Linked List and Stack implementation. 
2) Use these data structures to implement a basic interpreter
for a very small subset of the Java Byte Code language.


Phase2: 
1) Provide a Hash Map implementation. 
2) Use the implemented data structures to extend your basic interpreter
to support a larger subset of the Java Byte Code language.

List of Instructions for Phase1 (13 instructions):
iconst_0 push the int value 0 onto the stack
iconst_1 push the int value 1 onto the stack
iconst_2 push the int value 2 onto the stack
iconst_3 push the int value 3 onto the stack
iconst_4 push the int value 4 onto the stack
iconst_5 push the int value 5 onto the stack
bipush byte push a "byte" value onto the stack (note how this instruction takes in one parameter)
iadd pop value1, pop value2, compute (value2 + value1), push result onto the stack
imul pop value1, pop value2, compute (value2 * value1), push result onto the stack
idiv pop value1, pop value2, compute (value2 / value1), push result onto the stack
isub pop value1, pop value2, compute (value2 - value1), push result onto the stack
irem pop value1, pop value2, compute (value2 % value1), push result onto the stack
print pop value off the stack and print it to screen

List of Additional Instructions for Phase2 (19 instructions):
// reading values from variables and pushing them onto stack
iload_0 push value from variable 0
iload_1 push value from variable 1
iload_2 push value from variable 2
iload_3 push value from variable 3
iload index push value from variable designated by "index"
// popping values from stack and storing them in variables
istore_0 pop value and store into variable 0
istore_1 pop value and store into variable 1
istore_2 pop value and store into variable 2
istore_3 pop value and store into variable 3
istore index pop value and store into variable designated by "index"
// incrementing a variable
iinc index, value increment variable designated by "index" by value (not need to push/pop)
// jumps and conditionals
goto location unconditionally jump to instruction "location"
if_icmpeq location pop value1 and value2, if equal, branch to instruction at "location"
if_icmpne location pop value1 and value2, if not equal, branch to "location"
if_icmpge location pop value1 and value2, if value2 is greater than or equal to value1, branch to "location"
if_icmpgt location pop value1 and value2, if value2 is greater than value1, branch to "location"
if_icmple location pop value1 and value2, if value2 is less than or equal than value1, branch to at "location"
if_icmplt location pop value1 and value2, if value2 is less than value1, branch to "location"
ifne location pop value, if value is not 0, branch to location


Implementation/Classes
This project will be built using a number of classes that we describe below. Template files are provided for each class in
the project package and these contain further comments and additional details.
• Note<T> (Node.java): Implements a node to be used in LList<T> below. This class is provided to you and you
should NOT change the file (except for the JavaDocs).
• LList<T> (LList.java): Implements a generic linked list. In this project, you will read the list of instructions from
a text file and store them in an instance of LList<T>. Specifically, you will store the list of instructions in an
instance of LList<Instruction>; the first instruction in the file must be first in the list. You will also need to use an
LList in your Stack and HashMap implementations.
• Instruction (Instruction.java): This class describes a single Java Byte Code instruction. This class is provided to
you and you should NOT change the file (except for the JavaDocs).
• Stack<T> (Stack.java): This class implements a generic stack using LList<T> and Node<T>. In this project you
will likely use a single instance of Stack to push and pop integer values.
• HashMap<K, V> (HashMap.java): This class implements a hash map following the separate chaining approach.
Your implementation must use an array of linked lists of type LList<Pair>, where Pair is provided to you. In this
project you might want to use two instances of HashMap<K, V>, one to map the index of a variable to its integer
value, and another to map the offset of an instruction to the node enclosing it (this is not a requirement; however,
you need to use your HashMap at least once in your project).
• Interpreter (Interpreter.java): Implements your interpreter, which is invoked as follows: java Interpreter
[InputFILE].
