package cn.sunxn.domain;

import java.util.List;

/**
 * 主题对象
 */
public class Subject {
    private int subjectId;
    private String title;
    private String discription;
    private int type;
    private String createTime;
    private String endTime;
    private int userId;

    private User user;
    private List<Option> options;

    public Subject() {
    }

    public Subject(int subjectId, String title, String discription, int type, String createTime, String endTime, int userId, User user, List<Option> options) {
        this.subjectId = subjectId;
        this.title = title;
        this.discription = discription;
        this.type = type;
        this.createTime = createTime;
        this.endTime = endTime;
        this.userId = userId;
        this.user = user;
        this.options = options;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", title='" + title + '\'' +
                ", discription='" + discription + '\'' +
                ", type=" + type +
                ", createTime='" + createTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", userId=" + userId +
                ", user=" + user +
                ", options=" + options +
                '}';
    }
}
