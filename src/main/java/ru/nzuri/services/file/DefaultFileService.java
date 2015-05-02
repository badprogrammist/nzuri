/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.file;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.file.File;
import ru.nzuri.domain.file.FileRepository;
import ru.nzuri.services.AbstractService;

/**
 *
 * @author bad
 */

@Service
@Transactional
public class DefaultFileService extends AbstractService<File> implements FileService {

    @Inject
    private FileRepository fileRepository;
    
    @Override
    protected EntityRepository getRepository() {
        return fileRepository;
    }

    @Override
    protected File createEmptyEntity() {
        return new File();
    }
    
}
