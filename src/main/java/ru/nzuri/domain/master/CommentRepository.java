/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.master;

import java.util.List;
import ru.nzuri.domain.EntityRepository;

/**
 *
 * @author bad
 */
public interface CommentRepository extends EntityRepository<Comment> {
    public List<Comment> findAll(Master master);
}
