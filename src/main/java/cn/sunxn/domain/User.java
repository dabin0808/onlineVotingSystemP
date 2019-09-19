package cn.sunxn.domain;

/**
 * 用户对象
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private int version;
    private int status;

    public User() {
    }

    public User(int userId, String username, String password, int version, int status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.version = version;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", version=" + version +
                ", status=" + status +
                '}';
    }
}
