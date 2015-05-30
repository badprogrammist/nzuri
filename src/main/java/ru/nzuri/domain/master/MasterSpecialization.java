/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.master;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ru.nzuri.domain.action.Specialization;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "master_specializations")
@NamedQueries({
    @NamedQuery(
            name = "MasterSpecialization.findAll",
            query = "Select c from MasterSpecialization c"),
    @NamedQuery(
            name = "MasterSpecialization.findByMasterAndSpecialization",
            query = "Select c from MasterSpecialization c where c.master = :master and c.specialization = :specialization")
})
public class MasterSpecialization extends AbstractMasterCharacteristic<MasterSpecialization> {

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @OneToMany(mappedBy = "masterSpecialization",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<MasterAction> masterActions = new ArrayList<>();

    public MasterSpecialization(Master master, Specialization specialization) {
        super(master);
        this.specialization = specialization;
    }

    public MasterSpecialization() {
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public List<MasterAction> getMasterActions() {
        return masterActions;
    }

    public void setMasterActions(List<MasterAction> masterActions) {
        this.masterActions = masterActions;
    }

    @Override
    public void merge(MasterSpecialization entity) {
    }

}
