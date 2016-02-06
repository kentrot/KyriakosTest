/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askisi2;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kyriakos
 */
@Entity
@Table(name = "WORKPERMIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Workpermit.findAll", query = "SELECT w FROM Workpermit w"),
    @NamedQuery(name = "Workpermit.findByWorkPermitId", query = "SELECT w FROM Workpermit w WHERE w.workPermitId = :workPermitId"),
    @NamedQuery(name = "Workpermit.findByFromdate", query = "SELECT w FROM Workpermit w WHERE w.fromdate = :fromdate"),
    @NamedQuery(name = "Workpermit.findByTodate", query = "SELECT w FROM Workpermit w WHERE w.todate = :todate"),
    @NamedQuery(name = "Workpermit.findByNumdays", query = "SELECT w FROM Workpermit w WHERE w.numdays = :numdays")})
public class Workpermit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WORK_PERMIT_ID")
    private Long workPermitId;
    @Basic(optional = false)
    @Column(name = "FROMDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromdate;
    @Basic(optional = false)
    @Column(name = "TODATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date todate;
    @Basic(optional = false)
    @Column(name = "NUMDAYS")
    private int numdays;
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    @ManyToOne(optional = false)
    private Employee employeeId;
    @JoinColumn(name = "WORK_PERMIT_TYPE_ID", referencedColumnName = "WORK_PERMIT_TYPE_ID")
    @ManyToOne(optional = false)
    private Workpermittype workPermitTypeId;

    public Workpermit() {
    }

    public Workpermit(Long workPermitId) {
        this.workPermitId = workPermitId;
    }

    public Workpermit(Long workPermitId, Date fromdate, Date todate, int numdays) {
        this.workPermitId = workPermitId;
        this.fromdate = fromdate;
        this.todate = todate;
        this.numdays = numdays;
    }

    public Long getWorkPermitId() {
        return workPermitId;
    }

    public void setWorkPermitId(Long workPermitId) {
        this.workPermitId = workPermitId;
    }

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    public int getNumdays() {
        return numdays;
    }

    public void setNumdays(int numdays) {
        this.numdays = numdays;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Workpermittype getWorkPermitTypeId() {
        return workPermitTypeId;
    }

    public void setWorkPermitTypeId(Workpermittype workPermitTypeId) {
        this.workPermitTypeId = workPermitTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workPermitId != null ? workPermitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Workpermit)) {
            return false;
        }
        Workpermit other = (Workpermit) object;
        if ((this.workPermitId == null && other.workPermitId != null) || (this.workPermitId != null && !this.workPermitId.equals(other.workPermitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "askisi2.Workpermit[ workPermitId=" + workPermitId + " ]";
    }
    
}
