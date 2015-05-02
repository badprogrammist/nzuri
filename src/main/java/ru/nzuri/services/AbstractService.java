/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.AbstractEntity;
import ru.nzuri.domain.EntityRepository;

/**
 *
 * @author bad
 */
@Transactional
public abstract class AbstractService<E extends AbstractEntity> implements EntityService<E> {
    
    protected abstract EntityRepository<E> getRepository();
    protected abstract E createEmptyEntity();

    @Override
    public void store(E entity) {
        getRepository().store(entity);
    }
    
    @Override
    public E update(E entity) {
        return getRepository().update(entity);
    }
    
    @Override
    public List<E> getAll() {
        return getRepository().getAll();
    }
    
    @Override
    public E get(Long id) {
        return (E)getRepository().get(id);
    }
    
    @Override
    public void remove(Long id) {
        E entity = get(id);
        if(entity != null) {
            remove(entity);
        }
    }
    
    @Override
    public void remove(E entity) {
        getRepository().remove(entity);
    }
}
