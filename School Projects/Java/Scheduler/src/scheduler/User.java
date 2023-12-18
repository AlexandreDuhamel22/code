package scheduler;

// BEGIN CORRECTION
import java.util.Queue;
import java.util.LinkedList;
// END CORRECTION

public class User /* BEGIN CORRECTION */ implements Comparable<User> /* END CORRECTION */ {
    private String id;
    private Score score;
    // BEGIN CORRECTION
    private Queue<Job> pendingJobs;
    // END CORRECTION
    
    public User(String id, Score score) {
        this.id = id;
        this.score = score;
        // BEGIN CORRECTION
        this.pendingJobs = new LinkedList<Job>();
        // END CORRECTION
    }

    public String getId() {
        return this.id;
    }

    public void addJob(Job job) {
        // BEGIN CORRECTION
        this.pendingJobs.add(job);
        // END CORRECTION
    }
    
    // BEGIN CORRECTION
    public boolean hasJob() {
        return !this.pendingJobs.isEmpty();
    }

    public Job pollJob() {
        return this.pendingJobs.poll();
    }

    public void updateScore() {
        this.score.increase();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof User && ((User) other).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return this.id;
    }

    public String pretty() {
        return this.id + " " + this.pendingJobs;
    }

    @Override
    public int compareTo(User other) {
        if (this.pendingJobs.isEmpty()) {
            return other.pendingJobs.isEmpty() ? this.id.compareTo(other.id) : 1;
        }
        if (other.pendingJobs.isEmpty()) {
            return -1;
        }
        return this.score.compareTo(other.score) == 0 ? this.id.compareTo(other.id) : this.score.compareTo(other.score);
    }
    // END CORRECTION
}
