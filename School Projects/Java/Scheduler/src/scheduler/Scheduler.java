package scheduler;

// BEGIN CORRECTION
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Iterator;
// END CORRECTION

public class Scheduler /* BEGIN CORRECTION */ implements Iterable<User> /* END CORRECTION */ {
    // BEGIN CORRECTION
    private Map<String, User> users;
    private Map<JobId, Job> jobs;
    private TreeSet<User> sortedUsers;
    
    public Scheduler() {
        this.users = new HashMap<>();
        this.jobs = new HashMap<>();
        this.sortedUsers = new TreeSet<>();
    }
    // END CORRECTION

    public void addUser(User user) {
        // BEGIN CORRECTION
        this.users.put(user.getId(), user);
        this.sortedUsers.add(user);
        // END CORRECTION
    }
    
    public JobId addJob(String execFile, int maxSpan, String userId){
        // BEGIN CORRECTION
        /**
           Creates a new job and adds it to the scheduler.
           The user must exist.

           Overall complexity : O(log n) average (treeset), Θ(nb users + nb jobs) worst case (hash map).
           NB : I say O(log n), because I have no idea of the exact average complexity of inserting / removing
           in a balanced ABR.
         */
        Job newJob = new Job(execFile, maxSpan);  // O(1) (no collection involved here)
        User user = this.users.get(userId);  // Θ(1) average, Θ(nb users) worst case (hash map)
        // The following instruction ais necessary to reorder the sorted set
        this.sortedUsers.remove(user);  // Θ(log nb users) worst case (treeset)
        user.addJob(newJob);  // Θ(1) worst case (linked list)
        // The following instruction ais necessary to reorder the sorted set
        this.sortedUsers.add(user);  // Θ(log nb users) worst case (treeset)
        JobId newJobId = newJob.getJobId();  // O(1) (nothing to do basically)
        this.jobs.put(newJobId, newJob);  // Θ(1) average, Θ(nb jobs) worst case (hash map)
        
        return newJobId;
        // END CORRECTION
        /* BEGIN STUDENT
        return null;
           END STUDENT */
    }

    public Job getJob(JobId id){
        // BEGIN CORRECTION
        /**
           Returns the job object corresponding to the jobid.
        */        
        return this.jobs.get(id);
        // END CORRECTION
        /* BEGIN STUDENT
        return null;
           END STUDENT */
    }

    public Job extractNextJobToSchedule() {
        // BEGIN CORRECTION
        /**
           Gets the next job to execute, removes it from the list of pending jobs, starts
           it, and updates the user's score.

           @returns the job started, or null if no pending job was found
         */
        
        User nextUser = this.sortedUsders.pollFirst();  // Θ(log nb users) worst case (treeset)
        if (!nextUser.hasJob()) {  // Θ(1) (nothing to do basically)
            return null;
        }
        Job nextJob = nextUser.pollJob();  // Θ(1) worst case (linked list)
        nextJob.start();  // Θ(1) (nothing to do basically)
        nextUser.updateScore();  // Θ(1) (nothing to do basically)
        this.sortedUsers.add(nextUser);  // Θ(log nb users) worst case (treeset)
        return nextJob;
        // END CORRECTION
        /* BEGIN STUDENT
        return null;
           END STUDENT */
    }

    // BEGIN CORRECTION
    public Iterator<User> iterator() {
        return this.users.values().iterator();
    }
    // END CORRECTION
}
