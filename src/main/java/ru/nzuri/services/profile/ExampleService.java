/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.profile;

import ru.nzuri.domain.file.File;
import ru.nzuri.domain.profile.Example;
import ru.nzuri.domain.profile.Profile;
import ru.nzuri.services.EntityService;

/**
 *
 * @author bad
 */
public interface ExampleService extends EntityService<Example> {
    public Example addExample(Profile profile, File image, String comment);
}
