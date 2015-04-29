/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.file;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import ru.nzuri.domain.file.File;

/**
 *
 * @author bad
 */
public class FileHelper {

    public static File createFile(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        byte[] source = multipartFile.getBytes();
        File file = new File(source, contentType, fileName);
        return file;
    }
}
