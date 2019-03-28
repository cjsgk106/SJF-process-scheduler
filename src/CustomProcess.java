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
 * This class represents the type for the processes in the application
 * It implements the java.lang.Comparable<CustomProcess>
 * @author Gerrard Kim
 *
 */
public class CustomProcess implements java.lang.Comparable<CustomProcess> {

  private static int nextProcessId = 1; // stores the id to be assigned to the next process 
                                        // to be created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution
    
  /*
   * Constructor that creates a CustomProcess object with specific burstTime
   * @param time required by this process for CPU execution
   */
  public CustomProcess(int burstTime) {
    this.burstTime = burstTime; 
    this.PROCESS_ID = CustomProcess.nextProcessId;
    CustomProcess.nextProcessId++; // increment nextProcessId
  }    
    
  /*
   * Accesses the unique identifier for this process
   * 
   */
  public int getProcessId() {
    return this.PROCESS_ID;
  }
    
  /*
   * Accesses specific time required by this process for CPU execution
   * 
   */
  public int getBurstTime() {
    return this.burstTime;
  }

  /*
   * Compares this CustomProcess to another one
   * -1 represents higher priority over another one, 0 represents the same priority,
   * 1 represents lower priority over another one.
   * @Override
   */
  public int compareTo(CustomProcess other) {
    if (this.getBurstTime() < other.getBurstTime()) { // Shorter burstTime is higher priority 
      return -1;                                    
    }
    if (this.getBurstTime() == other.getBurstTime()) {      // when the same, lower PROCESS_ID
      if (this.getProcessId() < other.getProcessId()) {   // is higher priority
        return -1;
      } 
      else if (this.getProcessId() > other.getProcessId()) { 
        return 1;                                          
      }
      return 0;
    }
    if (this.getBurstTime() > other.getBurstTime()) { // Longer burstTime is lower priority
      return 1;
    }
    return 0;
  }

}