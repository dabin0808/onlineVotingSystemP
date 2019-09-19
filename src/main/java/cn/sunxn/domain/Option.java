package cn.sunxn.domain;

/**
 * 选项对象
 */
public class Option {
    private int optionId;
    private String content;
    private int orders;
    private int subjectId;
    private int counts;

    private Subject subject;

    public Option() {
    }

    public Option(int optionId, String content, int orders, int subjectId, int counts, Subject subject) {
        this.optionId = optionId;
        this.content = content;
        this.orders = orders;
        this.subjectId = subjectId;
        this.counts = counts;
        this.subject = subject;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Option{" +
                "optionId=" + optionId +
                ", content='" + content + '\'' +
                ", orders=" + orders +
                ", subjectId=" + subjectId +
                ", counts=" + counts +
                ", subject=" + subject +
                '}';
    }
}
