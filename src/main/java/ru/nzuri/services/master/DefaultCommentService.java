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
import ru.nzuri.domain.master.Comment;
import ru.nzuri.domain.master.CommentRepository;
import ru.nzuri.domain.master.Master;
import ru.nzuri.services.AbstractService;

/**
 *
 * @author bad
 */
@Service
@Transactional
public class DefaultCommentService extends AbstractService<Comment> implements CommentService {

    @Inject
    private CommentRepository commentRepository;
    
    @Override
    protected EntityRepository getRepository() {
        return commentRepository;
    }

    @Override
    public Comment createEmptyEntity() {
        return new Comment();
    }

    @Override
    public List<Comment> getAll(Master master) {
        return commentRepository.findAll(master);
    }
    
}
