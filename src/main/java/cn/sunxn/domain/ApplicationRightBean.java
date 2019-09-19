package cn.sunxn.domain;

public class ApplicationRightBean {
    private int applicationId;
    private int userId;
    private int rights;

    private User user;

    public ApplicationRightBean() {
    }

    public ApplicationRightBean(int applicationId, int userId, int rights, User user) {
        this.applicationId = applicationId;
        this.userId = userId;
        this.rights = rights;
        this.user = user;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRights() {
        return rights;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ApplicationRightBean{" +
                "applicationId=" + applicationId +
                ", userId=" + userId +
                ", rights=" + rights +
                ", user=" + user +
                '}';
    }
}
