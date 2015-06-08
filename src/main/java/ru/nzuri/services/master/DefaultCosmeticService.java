/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.master;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.master.Cosmetic;
import ru.nzuri.domain.master.CosmeticRepository;
import ru.nzuri.domain.master.Master;
import ru.nzuri.services.AbstractService;

/**
 *
 * @author bad
 */
@Service
@Transactional
public class DefaultCosmeticService extends AbstractService<Cosmetic> implements CosmeticService {

    @Inject
    private CosmeticRepository cosmeticRepository;
    
    
    
    @Override
    protected EntityRepository getRepository() {
        return cosmeticRepository;
    }

    @Override
    public Cosmetic createEmptyEntity() {
        return new Cosmetic();
    }

    @Override
    public List<Cosmetic> getAll(Master master) {
        return cosmeticRepository.findAll(master);
    }
    
}
