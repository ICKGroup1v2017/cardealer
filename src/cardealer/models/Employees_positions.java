package cardealer.models;

import java.util.Objects;

/**
 *
 * @author Ahmet Murati
 */
public class Employees_positions implements Comparable<Employees_positions> {

    private final String nl = System.lineSeparator();

    private Integer id;
    private Long employeeId;
    private Long positionID;
    private boolean status;

    public Employees_positions() {
        this.id = 0;
        this.employeeId = 0l;
        this.positionID = 0l;
        this.status = false;
                
    }

    public Employees_positions(Integer id) {
        this();
        this.id = id;
    }

    public Employees_positions(Integer id, Long employeeId, Long positionID, boolean status) {
        this.id = id;
        this.employeeId = employeeId;
        this.positionID = positionID;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return id;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getPositionID() {
        return positionID;
    }

    public void setPositionID(Long positionID) {
        this.positionID = positionID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    

    @Override
    public int compareTo(Employees_positions o) {
        return this.id.compareTo(o.getId());
    }
}