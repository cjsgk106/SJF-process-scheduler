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
 * Waiting Queue Abstract Data Type<br>
 * CS 300 P10 Assignment
 * 
 * This interface is ADT that represents the pattern for ready processes waiting list
 * @author mouna
 * 
 * @param <T extends Comparable<T>> the type of objects to be stored in the queue
 *
 */
public interface WaitingQueueADT<T extends Comparable<T>> {
  /*
   * Inserts a newObject in the priority queue
   * @param an element to be added
   * 
   */
  public void enqueue(T newObject);
    
  /*
   * Removes and returns the item with the highest priority
   * 
   */
  public T dequeue(); 
   
  /*
   * Returns without removing the item with the highest priority
   * 
   */
  public T peek(); 
   
  /*
   * Returns size of the waiting queue
   * 
   */
  public int size(); 
   
  /*
   * Checks if the waiting queue is empty
   * 
   */
  public boolean isEmpty(); 
}
