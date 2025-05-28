
# CMPE 261 Project 3: Java Threading

## Project Overview

This project is a Java application to demonstrate multithreading concepts. The project requires implementing various threading mechanisms, synchronization techniques, and solving practical problems using Java threads.


## Running Instructions

If you want to run the examples in an IDE, such as Eclipse, you should
be able to copy-and-paste the entire contents of any one of the chapter folders
into a project in the IDE, and then run the programs.


## Sample Inputs and Expected Outputs

### 1. Multi-threaded Number Processing:
```
Even Sum: 250500
Odd Sum: 250000
```
### 2. Wait and Notify Mechanism for Task Coordination
```
Thread 1: 1
Thread 2: 2
Thread 1: 3
Thread 2: 4
Thread 1: 5
Thread 2: 6
Thread 1: 7
Thread 2: 8
Thread 1: 9
Thread 2: 10
Thread 1: 11
Thread 2: 12
Thread 1: 13
Thread 2: 14
Thread 1: 15
Thread 2: 16
Thread 1: 17
Thread 2: 18
Thread 1: 19
Thread 2: 20

```
### 3. Matrix Multiplication with Threads
```
Enter Matrix A:
1 2
3 4
Enter Matrix B:
5 6
7 8
Result:
19 22 
43 50 
```

### 4. Thread-safe Resource Sharing
```
Thread 1 incremented the counter.
Thread 2 incremented the counter.
Thread 3 incremented the counter.
Final Counter Value: 3000
```

### 5. Concurrent File Search
```
Keyword "thread" found in file2.txt at lines: 1
Keyword "thread" found in file1.txt at lines: 2, 5
```

### 6. Thread-safe Bank Account System
```
Deposited: $1000.0
Withdrawn: $700.0
There is no $500.0 in this balance to withdraw.
Final Balance: $300.0
```