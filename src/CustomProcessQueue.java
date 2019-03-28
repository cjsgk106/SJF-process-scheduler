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
 * This class implements the WaitingQueueADT interface of CustomProcesses
 * @author Gerrard Kim
 *
 */
public class CustomProcessQueue implements WaitingQueueADT<CustomProcess> {
  private static final int INITIAL_CAPACITY = 20; // the initial capacity of the heap
  private CustomProcess[] heap; // array-based min heap storing the data. This is an oversize 
                                // array
  private int size; // number of CustomProcesses present in this CustomProcessQueue
    
  /*
   * Constructor that creates a CustomProcessQueue object
   * 
   */
  public CustomProcessQueue() {
    heap = new CustomProcess[INITIAL_CAPACITY]; // heap with the size of the initial capacity
    this.size = 0;
  }
    
  /*
   * Bubbles up the element at the given index in the heap
   * @param the specific index of the heap
   * 
   */
  private void minHeapPercolateUp(int index) {
    int parentIndex = 1; // the parent index of the specified index
        
    while (index > 1 && heap[index] != null) {
      parentIndex = (int) ((double)(index - 1) / 2 + 0.5); // the function for the parent index
      
      if (heap[index].compareTo(heap[parentIndex]) >= 0) { // if the index is lower priority, done
        return;
      } else {
        CustomProcess[] temp = new CustomProcess[1];
        temp[0] = heap[index];
        heap[index] = heap[parentIndex];
        heap[parentIndex] = temp[0];    // swap heap[index] and heap[parentIndex]
        index = parentIndex;
      }
    }
        
  }
    
  /*
   * Bubbles down the element of the given index in the heap
   * @param the specific index of the heap
   * 
   */
  private void minHeapPercolateDown(int index) {
    int childIndex = 2 * index; // the function for the child index
    int time = heap[index].getBurstTime(); // the burstTime of the heap with the index
    int id = heap[index].getProcessId();  // the PROCESS_ID of the heap with the index
        
    while (childIndex < heap.length) { 
      int minTime = time; // the minimum burstTime
      int minIndex = -1; // the minimum index
      int minID = id; // the minimum PROCESS_ID
    
      for (int i = 0; i < 2 && i + childIndex < heap.length; ++i) { // gets the first and second 
                                                                    // of the child
        if (heap[i+childIndex] != null) {
          if (heap[i+childIndex].getBurstTime() < minTime) { // if less than the minTime,
            minTime = heap[i+childIndex].getBurstTime();   // assign it to be the minTime
            minID = heap[i+childIndex].getProcessId();
            minIndex = i + childIndex;
          }
          else if (heap[i+childIndex].getBurstTime() == minTime) {
            if (heap[i+childIndex].getProcessId() < minID) { // when the burstTime is same
              minTime = heap[i+childIndex].getBurstTime(); // if less than the minID,
              minID = heap[i+childIndex].getProcessId();   // let it be the higher priority
              minIndex = i + childIndex;
            }
          }
        }
      }
            
      if (minTime == time) { // if minTime is the time, done
        return;
      } else {
        CustomProcess[] temp = new CustomProcess[1]; 
        temp[0] = heap[index];
        heap[index] = heap[minIndex]; // swap heap[index] and heap[minIndex]
        heap[minIndex] = temp[0];
        index = minIndex;
        childIndex = 2 * index;
      }
    }
  }

  /*
   * Inserts a newObject in the priority queue
   * @param the specific CustomProcess
   * @Override
   * 
   */   
  public void enqueue(CustomProcess newObject) {
    if (size == heap.length - 1) { // if its size is full, double the size
      CustomProcess[] newHeap = new CustomProcess[2*heap.length];
      heap = newHeap;
    }
    heap[size+1] = newObject; // the root node is the entry at index 1 in the heap
    minHeapPercolateUp(size+1); // calls minHeapPercolateUp with the index
    size++; // increment the size
  }

  /*
   * Removes and returns the item with the highest priority
   * @Override
   */
  public CustomProcess dequeue() {
    if (size > 0) { 
      CustomProcess top = heap[1]; 
      heap[1] = heap[size]; // removes the highest priority and assign the last one to be the first
      heap[size] = null;
      size--;

      if (size > 0) {
        minHeapPercolateDown(1); // calls minHeapPercolateDown with the first one
      }
      return top; // returns the saved the highest priority item
    }
    return null;
  }

  /*
   * Returns without removing the item with the highest priority
   * @Override
   */
  public CustomProcess peek() {
    if (size > 0) {
      CustomProcess top = heap[1];
      return top;           
    }
    return null;
  }

  /*
   * Returns size of the waiting queue
   * @Override
   */
  public int size() {        
    return this.size;
  }

  /*
   * Checks if the waiting queue is empty
   * @Override
   */
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }
  
}