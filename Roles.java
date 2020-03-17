package net.javaguides.springboot.tutorial.entity;

import javax.persistence.*;

@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name="name")
    private String userName;

    @Column(name="userpid")
    private String userPid;

    @Column(name="role")
    private String userRole;

    @Column(name="approver")
    private boolean isApprover;

    @Column(name="reviwer")
    private boolean isReviwer;

    @Column(name="context")
    private String context;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPid() {
        return userPid;
    }

    public void setUserPid(String userPid) {
        this.userPid = userPid;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public boolean isApprover() {
        return isApprover;
    }

    public void setApprover(boolean approver) {
        isApprover = approver;
    }

    public boolean isReviwer() {
        return isReviwer;
    }

    public void setReviwer(boolean reviwer) {
        isReviwer = reviwer;
    }
}
