/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.repositories.master;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.nzuri.domain.master.Comment;
import ru.nzuri.domain.master.CommentRepository;
import ru.nzuri.domain.master.Master;
import ru.nzuri.repositories.AbstractRepositoryJPA;

/**
 *
 * @author bad
 */
@Repository
public class CommentRepositoryJPA extends AbstractRepositoryJPA<Comment> implements CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CommentRepositoryJPA() {
        super(Comment.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Comment> findAll(Master master) {
        return getEntityManager().createNamedQuery("Comment.findAllByMaster", Comment.class)
                .setParameter("master", master)
                .getResultList();
    }
    
}
