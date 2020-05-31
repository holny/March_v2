package com.hly.march2.service;

import com.hly.march2.entity.FileHelper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {
    String saveFile(FileHelper fileHelper, String destination, String path, MultipartFile file) throws IOException;

    FileHelper getFileHelperByFileId(Long fileId);


}
