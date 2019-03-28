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
import java.util.Scanner;

/**
 * This class represents the data type for the main scheduler for the processes
 * @author Gerrard Kim
 *
 */
public class ProcessScheduler {
  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private CustomProcessQueue queue; // this processing unit's custom process queue
  
  /*
   * Constructor that creates a ProcessScheduler object
   */
  public ProcessScheduler() {
    this.currentTime = 0; // initializes to 0
    this.numProcessesRun = 0; // initializes to 0
    queue = new CustomProcessQueue();
  }
  
  /*
   * Enqueues the given process in the CustomProcessQueue queue
   * @param the specific CustomProcess
   * 
   */
  public void scheduleProcess(CustomProcess process) {
    queue.enqueue(process); // enqueues the process in the queue
  }
  
  /*
   * Runs the ready processes already scheduled in the queue
   * 
   */
  public String run() {
    String log = ""; // the log of the processes 
    
    if (queue.size() < 2) { // either process or processes depending on the size
      log += "Starting " + queue.size() + " process\n\n";
    } else {
      log += "Starting " + queue.size() + " processes\n\n";        
    }
    
    while (!queue.isEmpty()) { 
      log += "Time " + currentTime + " : Process ID " + queue.peek().getProcessId() 
          + " Starting.\n";
      currentTime += queue.peek().getBurstTime(); // increment the currentTime with each burstTime
      log += "Time " + currentTime + " : Process ID " + queue.dequeue().getProcessId() 
          + " Completed.\n";
      numProcessesRun++; // increment the number of processes run
    }
    
    log += "\nTime " + currentTime + ": All scheduled processes completed.\n";
    return log; // return the log of the whole processes      
  }
  
  /**
   * This main method serves as the driver of the application
   * @param args
   * @exception NumberFormatException
   * if userInput burst time is not an integer
   * @exception NullPointerException
   * if the input is not a valid command
   * @exception IndexOutOfBoundsException
   * if the array index is out of bounds
   */
  public static void main(String[] args) { 
    Scanner scnr = new Scanner(System.in);
    boolean isBoolean = false;
    String userInput = "";
    String firstLetter = ""; 
    int processID = 0; // processID for tracing the number
    ProcessScheduler ps = new ProcessScheduler(); // the ProcessScheduler object
    System.out.println("==========   Welcome to the SJF Process Scheduler App   ========");
    System.out.println("");
        
    do {  
               
      System.out.println("Enter command:\n" + "[schedule <burstTime>] or [s <burstTime>]");
      System.out.println("[run] or [r]\n" + "[quit] or [q]");
      System.out.println("");
      
      try {
        userInput = scnr.nextLine().toLowerCase(); // negating the capital letters of userInput
        firstLetter = userInput.substring(0, 1); // the first letter of userInput                  
        
        switch (firstLetter) {
          case "s":
            try {               
              String [] arrOfstr = userInput.split(" ", 2); // Split 2 arguments into one each
              if (!(arrOfstr[0].equals("schedule") || arrOfstr[0].equals("s"))) { 
                System.out.println("WARNING: Please enter a valid command!\n");                   
                break; // both "schedule" and "s" are proper commands
              }
                  
              int burstTime = Integer.parseInt(arrOfstr[1]);
              if (burstTime < 1) { // checks if the burst time is positive integer
                System.out.println("WARNING: burst time MUST be greater than 0!\n");
                break;
              }                            
              
              ps.scheduleProcess(new CustomProcess(burstTime)); // enqueues the CustomProcess
              processID++; // increment the processID
              System.out.println("Process ID " + processID + " scheduled. Burst Time = "
                  + burstTime + "\n");
                  
            } catch (NumberFormatException e) {
              System.out.println("WARNING: burst time MUST be an integer!\n");
              break;
            } catch (NullPointerException e) {
              System.out.println("WARNING: Please enter a valid command!\n");
              break;
            } catch (IndexOutOfBoundsException e) {
              System.out.println("WARNING: Please enter a valid command!\n");
              break;
            }                
            break;
          case "r":
            if (!(userInput.equals("run") || userInput.equals("r"))) {
              System.out.println("WARNING: Please enter a valid command!\n");
              break;
            } // both "run" and "r" are proper commands
            System.out.println(ps.run()); // calls run() to print the log             
            break;
          case "q":
            if (!(userInput.equals("quit") || userInput.equals("q"))) {
              System.out.println("WARNING: Please enter a valid command!\n");
              break;
            } // both "quit" and "q" are proper commands
            System.out.println(ps.numProcessesRun + " processes run in " + ps.currentTime 
                + " units of time!\n" + "Thank you for using our scheduler!\n" + "Goodbye!\n");
            isBoolean = true; // exit the while loop
            break;
          default:
            System.out.println("WARNING: Please enter a valid command!\n");
            break; // the other inputs are all invalid commands
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("WARNING: Please enter a valid command!\n");                    
      }
        
    } while (!isBoolean);
 
    scnr.close();
  }
}
