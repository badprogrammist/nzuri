/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain;

import java.util.List;

/**
 *
 * @author bad
 */
public interface EntityRepository<E extends AbstractEntity> {
    public void store(E entity);
    public E update(E entity);
    public void remove(E entity);
    public void remove(Long id);
    public E get(Long id);
    public List<E> getAll();
}
