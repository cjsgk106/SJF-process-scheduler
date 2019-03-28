//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: SJF Process Scheduler
// Files: UTF-8
// Course: CS 300, Fall 18
//
// Author: Gerrard Kim
// Email: hkim624@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class tests 4 public methods from CustomProcess and CustomProcessQueue class and enqueue
 * and dequeue methods from CustomProcessQueue class
 * @author Gerrard Kim
 *
 */
public class ProcessSchedulerTests {

  /*
   * This tests getProcessId() method from CustomProcess class
   * @return true if b's processID is 2, false otherwise
   * 
   */
  public static boolean testGetProcessId() {
    CustomProcess a = new CustomProcess(3);
    CustomProcess b = new CustomProcess(5);
      
    if (b.getProcessId() != 2) {
      return false;
    }
    return true;
  }
  
  /*
   * This tests compareTo() method from CustomProcess class
   * @return true if a is the higher priority, false otherwise
   * 
   */
  public static boolean testCompareTo() {
    CustomProcess a = new CustomProcess(1);
    CustomProcess b = new CustomProcess(1); // checks when the burst times are the same
      
    if (a.compareTo(b) >= 0) { 
      return false;
    }
    return true;
  }
  
  /*
   * This tests size() method from CustomProcessQueue class
   * @return true if the size is 3, false otherwise
   * 
   */
  public static boolean testSize() {
    CustomProcessQueue sc = new CustomProcessQueue();
    sc.enqueue(new CustomProcess(3));
    sc.enqueue(new CustomProcess(1));
    sc.enqueue(new CustomProcess(5));
      
    if (sc.size() != 3) {
      return false;
    }
    return true;
  }
  
  /*
   * This tests isEmpty() method from CustomProcessQueue class
   * @return true if it is empty, false otherwise
   * 
   */
  public static boolean testIsEmpty() {
    CustomProcessQueue sc = new CustomProcessQueue();
    sc.enqueue(new CustomProcess(3));
    sc.enqueue(new CustomProcess(1));
    sc.enqueue(new CustomProcess(5));
    int size = sc.size();
    
    for (int i = 0; i < size; ++i) {
      sc.dequeue();    
    }

    if (!sc.isEmpty()) {
      return false;
    }
    return true;
  }
  
  /*
   * This tests the correctness of the enqueue operation from CustomProcessQueue class
   * @return true if the highest priority burst time is 1, false otherwise
   * 
   */
  public static boolean testEnqueueCustomProcessQueue(){
    CustomProcessQueue sc = new CustomProcessQueue();
    sc.enqueue(new CustomProcess(3));
    sc.enqueue(new CustomProcess(1));
    sc.enqueue(new CustomProcess(5));
    
    if (sc.peek().getBurstTime() != 1) {
      return false;
    }
    return true;
  }
    
  /*
   * This tests the correctness of the dequeue operation from CustomProcessQueue class
   * @return true if the highest priority burst time is 3, false otherwise
   * 
   */
  public static boolean testDequeueCustomProcessQueue(){
    CustomProcessQueue sc = new CustomProcessQueue();
    sc.enqueue(new CustomProcess(3));
    sc.enqueue(new CustomProcess(1));
    sc.enqueue(new CustomProcess(5));
    sc.dequeue();
        
    if (sc.peek().getBurstTime() != 3) {
      return false;
    }
    return true;
  } 

  /*
   * This main method runs all the test methods above to check functionality of the program
   * @param args
   * 
   */
  public static void main(String[] args) {                     
    int fails = 0; // number of failure tests
        
    if (!testGetProcessId()) {
      System.out.println("testGetProcessId failed");
      fails++;
    }
    if (!testCompareTo()) {
      System.out.println("testCompareTo failed");
      fails++;
    }
    if (!testSize()) {
      System.out.println("testSize failed");
      fails++;
    }
    if (!testIsEmpty()) {
      System.out.println("testIsEmpty failed");
      fails++;
    }
    if (!testEnqueueCustomProcessQueue()) {
      System.out.println("testEnqueueCustomProcessQueue failed");
      fails++;
    }
    if (!testDequeueCustomProcessQueue()) {
      System.out.println("testDequeueCustomProcessQueue failed");
      fails++;
    }        
    if (fails == 0) {
      System.out.println("All tests passed!");
    }
  }   

}