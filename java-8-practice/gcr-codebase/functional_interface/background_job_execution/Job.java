package background_job_execution;

class Job {
    private String jobName;
    private int duration;

    public Job(String jobName, int duration) {
        this.jobName = jobName;
        this.duration = duration;
    }

    public String getJobName() {
        return jobName;
    }

    public int getDuration() {
        return duration;
    }
}
