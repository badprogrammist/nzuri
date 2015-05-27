/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services;

import java.util.List;
import ru.nzuri.domain.AbstractEntity;

/**
 *
 * @author bad
 */
public interface EntityService<E extends AbstractEntity> {

    public void store(E entity);
    
    public E update(E entity);
    
    public E merge(E entity);
    
    public List<E> getAll();
    
    public E get(Long id);
    
    public void remove(Long id);
    
    public void remove(E entity);
    
    public E createEmptyEntity();
}
