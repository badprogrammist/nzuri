/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.master;

import java.util.List;
import ru.nzuri.domain.file.File;
import ru.nzuri.domain.master.Example;
import ru.nzuri.domain.master.Master;
import ru.nzuri.services.EntityService;

/**
 *
 * @author bad
 */
public interface ExampleService extends EntityService<Example> {
    public Example addExample(Master master, File image, String comment);
    public List<Example> getExamples(Master master);
}
