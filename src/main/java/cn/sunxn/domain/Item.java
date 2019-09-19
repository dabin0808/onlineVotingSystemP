package cn.sunxn.domain;

/**
 * 记录对象
 */
public class Item {
    private int itemId;
    private int subjectId;
    private int optionId;
    private int userId;
    private String time;

    private Subject subject;
    private Option option;
    private User user;

    public Item() {
    }

    public Item(int itemId, int subjectId, int optionId, int userId, String time, Subject subject, Option option, User user) {
        this.itemId = itemId;
        this.subjectId = subjectId;
        this.optionId = optionId;
        this.userId = userId;
        this.time = time;
        this.subject = subject;
        this.option = option;
        this.user = user;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", subjectId=" + subjectId +
                ", optionId=" + optionId +
                ", userId=" + userId +
                ", time='" + time + '\'' +
                ", subject=" + subject +
                ", option=" + option +
                ", user=" + user +
                '}';
    }
}
