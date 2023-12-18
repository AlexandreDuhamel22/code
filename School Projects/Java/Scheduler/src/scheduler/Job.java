package scheduler;

public class Job {    
    private JobId jobId;
    private String execFile;
    private int maxSpan;
    private JobStatus status;

    public Job(String execFile, int maxSpan) {
        this.jobId = new JobId();
        this.execFile = execFile;
        this.maxSpan = maxSpan;
        this.status = JobStatus.PENDING;
    }

    public void start() {
        this.status = JobStatus.STARTED;
    }

    @Override
    public String toString() {
        return "<" + this.execFile + ", " + this.maxSpan + ">";
    }

    // BEGIN CORRECTION
    public JobId getJobId() {
        return this.jobId;
    }
    
    @Override
    public boolean equals(Object other) {
        return other instanceof Job && ((Job) other).jobId.equals(this.jobId);
    }

    @Override
    public int hashCode() {
        return this.jobId.hashCode();
    }
    // END CORRECTION
}
