/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.master;

import java.util.List;
import ru.nzuri.domain.master.Education;
import ru.nzuri.domain.master.Master;
import ru.nzuri.services.EntityService;

/**
 *
 * @author bad
 */
public interface EducationService extends EntityService<Education> {
    public List<Education> getAll(Master master);
}
