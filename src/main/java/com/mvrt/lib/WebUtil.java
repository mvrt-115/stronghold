package com.mvrt.lib;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Utility methods for Bullboard.
 *
 * @author Siddharth Gollapudi
 */
public class WebUtil implements Runnable {
  public ArrayBlockingQueue<Runnable> bq;
  Thread thread;
  private boolean running = true;

  /**
   * Constructor of the class.
   * @param size - size for the ArrayBlockingQueue
   */
  public WebUtil(int size) {
    bq = new ArrayBlockingQueue<Runnable>(size);
  }

  /**
   * Start the thread upon which a process is running.
   * With low priority.
   */
  public void start() {
    running = true;
    if (thread == null || !thread.isAlive()) {
      thread = new Thread(this);
    }
    thread.setName("TaskQueue - " + this.toString());
    thread.setPriority(Thread.MIN_PRIORITY); // All tasks should go slow for now!
    thread.start();

  }

  /**
   * Stop the thread.
   */
  public void stop() {
    running = false;
  }

  /**
   * Run the current process at the head of the queue.
   */
  @Override
  public void run() {
    while (running) {
      Runnable runnable;
      try {
        runnable = bq.take();
        if (runnable != null) {
          runnable.run();
        }
      } catch (InterruptedException e) {
        System.err.println("Caught InterruptedException in task queue");
        e.printStackTrace();
      } catch (RuntimeException e) {
        System.err.println("Caught run time exception in task queue");
        e.printStackTrace();
      }
    }
  }

  /**
   * Add a process to a queue.
   * @param r the process
   * @return the queue with the added process.
   */
  public boolean addTask(Runnable r) {
    return bq.offer(r);
  }

}
