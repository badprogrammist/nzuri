/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.master;

import java.util.List;
import ru.nzuri.domain.master.Comment;
import ru.nzuri.domain.master.Master;
import ru.nzuri.services.EntityService;

/**
 *
 * @author bad
 */
public interface CommentService extends EntityService<Comment> {
    public List<Comment> getAll(Master master);
}
