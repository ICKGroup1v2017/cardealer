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

    public Employees_positions() {
        this.id = 0;
        this.employeeId = 0l;
        this.positionID = 0l;
    }

    public Employees_positions(Integer id) {
        this();
        this.id = id;
    }

    public Employees_positions(Integer id, Long employeeId, Long positionID) {
        this.id = id;
        this.employeeId = employeeId;
        this.positionID = positionID;
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

    @Override
    public int compareTo(Employees_positions o) {
        return this.id.compareTo(o.getId());
    }
}