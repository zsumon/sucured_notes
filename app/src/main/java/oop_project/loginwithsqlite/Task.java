package oop_project.loginwithsqlite;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sumon on 7/25/2017.
 */

public class Task implements Serializable {

    private String taskName;
    private String taskDetails;
    private Date addinngDate;

    public Task() {
    }


    public Task(String taskName, String taskDetails) {
        this.taskName = taskName;
        this.taskDetails = taskDetails;
    }

    public Task(String taskName, String taskDetails, Date addinngDate) {
        this(taskName, taskDetails);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public Date getAddinngDate() {
        return addinngDate;
    }

    public void setAddinngDate(Date addinngDate) {
        this.addinngDate = addinngDate;
    }
}
