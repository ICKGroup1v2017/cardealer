package cardealer.models;

import java.util.Objects;

/**
 *
 * @author Ahmet Murati
 */
public class User_info implements Comparable<User_info> {

    private final String nl = System.lineSeparator();

    private Long idUserInfo;
    private Integer employeeID;
    private String email;
    private String password;
    private boolean status;

    public User_info() {
        this.idUserInfo = 0l;
        this.employeeID = 0;
        this.email = "";
        this.password = "";
        this.status = false;
    }

    public User_info(Long idUserInfo) {
        this();
        this.idUserInfo = idUserInfo;
    }

    public User_info(Long idUserInfo, Integer employeeID, String email, String password, boolean status) {
        this.idUserInfo = idUserInfo;
        this.employeeID = employeeID;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public Long getIdUserInfo() {
        return idUserInfo;
    }

    public void setIdUserInfo(Long idUserInfo) {
        this.idUserInfo = idUserInfo;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int compareTo(User_info o) {
        return this.idUserInfo.compareTo(o.getIdUserInfo());
    }
}
