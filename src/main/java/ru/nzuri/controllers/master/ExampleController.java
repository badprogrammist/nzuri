/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.master;

import java.io.IOException;
import javax.inject.Inject;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import ru.nzuri.controllers.file.FileHelper;
import ru.nzuri.domain.file.File;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.user.User;
import ru.nzuri.security.AuthenticationService;
import ru.nzuri.services.master.ExampleService;
import ru.nzuri.services.master.MasterService;

/**
 *
 * @author bad
 */
@Controller
public class ExampleController {
    
    @Inject
    private MasterService masterService;
    
    @Inject
    private ExampleService exampleService;
    
    @Inject
    private AuthenticationService authenticationService;
    
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/example/upload", method = RequestMethod.POST)
    public void handleExampleImageUpload(MultipartFile file) {
        User currentUser = authenticationService.getPrincipal();
        if(currentUser != null) {
            Master master = masterService.get(currentUser);
            if(master != null) {
                File image;
                try {
                    image = FileHelper.createFile(file);
                    exampleService.addExample(master,image , "");
                } catch (IOException ex) {
                }
            }
        }
    }
    
    
    
}
