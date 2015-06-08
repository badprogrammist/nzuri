/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.master;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "educations")
@Cacheable
@NamedQueries({
    @NamedQuery(name = "Education.findAll",
            query = "Select c from Education  c"),
    @NamedQuery(name = "Education.findAllByMaster",
            query = "Select c from Education  c where c.master = :master")
})
public class Education extends AbstractMasterCharacteristic<Education>{

    @Column(name = "institution")
    private String institution;
    
    @Column(name = "speciality")
    private String speciality;
    
    @Column(name = "start_year")
    private String startYear;
    
    @Column(name = "end_year")
    private String endYear;

    public Education(Master master) {
        super(master);
    }

    public Education() {
    }
    
    
    @Override
    public void merge(Education entity) {
        this.institution = entity.getInstitution();
        this.speciality = entity.getSpeciality();
        this.startYear = entity.getStartYear();
        this.endYear = entity.getEndYear();
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    
    
    
    
}
