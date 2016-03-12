# Dining-Philosophers-problem
The Dining Philosophers problem was invented by E. W. Dijkstra, a concurrency pioneer, to clarify the notions of deadlock and starvation freedom. Imagine five philosophers who spend their lives just thinking and feasting. They sit around a circular table with five chairs. The table has a big plate of rice. However, there are only five chopsticks (in the original formulation forks) available (see Figure 1 of Chapter 1, Exercise 1 from the textbook). Each philosopher thinks. When he gets hungry, he sits down and picks up the two chopsticks that are closest to him. If a philosopher can pick up both chopsticks, he can eat for a while. After a philosopher finishes eating, he puts down the chopsticks and again starts to think.


Taken 5 threads, each for one philosopher and I used the concept of mutexes to solve this problem.

For the problem to be starvation free it is required to ensure that no philosophers wait more than specified time for both the chopsticks and no other adjacent philosophers acquires the chopsticks before him.

If any of the philosophers is hungry, it will simply try to acquire the lock and once the lock is obtained it will change its “state” and picks up both the chopsticks and start eating.
After the eating is done he’ll drop the chopsticks and unlocks the acquired lock.

To ensure that there is no starvation we have a function “StFree” which will check that neither of the neighbors is in “HUNGRY” or “EATING” state.

If any of the neighbors is in “HUNGRY” state, this philosopher will wait first and in this way starvation is prevented. 
