package com.hly.march2.service.impl;

import com.hly.march2.dao.FileHelperMapper;
import com.hly.march2.dao.UserMapper;
import com.hly.march2.entity.FileHelper;
import com.hly.march2.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

@Service
@Transactional(readOnly = true)
public class FileServiceImpl implements IFileService {

    @Autowired
    private FileHelperMapper fileHelperMapper;



    @Override
    @Transactional(readOnly = false)
    public String saveFile(FileHelper fileHelper, String destination, String path, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".")+1);//文件后缀

        // 判断该路径是否存在
        File file_path = new File(path);
        if (!file_path.exists()) {
            // 创建该文件夹
            file_path.mkdirs();
        }
        // 把文件名称设置成唯一值
        fileName=fileHelper.getUserId()+"_"+new Date().getTime()+"_"+new Random().nextInt(1000);//新的文件名

        File saveFile = new File(file_path, fileName+"."+fileSuffix);

        file.transferTo(saveFile);
        fileHelper.setFilePath(path);
        fileHelper.setFileName(fileName);
        fileHelper.setFileSuffix(fileSuffix);
        fileHelper.setFileSize(saveFile.length());
        fileHelperMapper.insertSelective(fileHelper);
//        if ("profilepic".equals(destination)) {
//            if(originUser.getUserProfilePic()!=null){
//                File oldPic =new File(path+originUser.getUserProfilePic());
//                // 判断目录或文件是否存在
//                if(oldPic.exists()&&oldPic.isFile()){
//                    oldPic.delete();
//                }
//            }
//            user.setUserProfilePic(fileName + "." + fileSuffix);
//        }
        return file_path+"/"+fileName+"."+fileSuffix;
    }

    @Override
    public FileHelper getFileHelperByFileId(Long fileId) {
        return fileHelperMapper.selectByPrimaryKey(fileId);
    }
}
