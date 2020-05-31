package com.hly.march2.controller;

import com.hly.march2.entity.FileHelper;
import com.hly.march2.entity.Msg;
import com.hly.march2.entity.User;
import com.hly.march2.shiro.ShiroHelper;
import com.hly.march2.service.IFileService;
import com.hly.march2.service.IUserService;
import com.hly.march2.utils.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@RequestMapping("/file")
@Controller
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private IFileService fileService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ShiroHelper shiroHelper;

    @Value("${file.upload.path}")
    private String fileDir;

    private enum FileTypeEnum {
        PNG_TYPE(1, "png"),
        JPG_TYPE(2, "jpg"),
        JPEG_TYPE(3, "jpeg"),
        GIF_TYPE(4, "gif");

        private Integer code;
        private String desc;

        FileTypeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private static List<String> imageTypeDescList;

        static {
            List<String> list1 = new ArrayList<>();
            list1.add(PNG_TYPE.getDesc());
            list1.add(JPG_TYPE.getDesc());
            list1.add(JPEG_TYPE.getDesc());
            list1.add(GIF_TYPE.getDesc());
            imageTypeDescList = Collections.unmodifiableList(list1);
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static List<String> getImageTypeDescList() {
            return imageTypeDescList;
        }
    }

    /**
     * 保存用户的头像图片profilePic或者背景图片。
     * 这里是对前端js插件fileinput做的相应适配。 注意，每个用户同时只存在一个头像图片和背景图片，插入新的就会删除旧的。
     * 本项目是图片数据保存在文件目录下，数据库中保存此图片的相关信息。
     * @param file
     * @param destination 区分保存的图片是头像还是背景
     * @param request
     * @return
     */
    @RequiresUser
    @RequiresRoles(value = {"user"})
    @ResponseBody
    @RequestMapping("/upload/img")
    public Msg saveImage(@RequestParam("upload") MultipartFile file, @RequestParam("destination") String destination, HttpServletRequest request) {
        log.debug("destination:{}",destination);
        Subject subject = SecurityUtils.getSubject();
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);//文件后缀
        log.debug("fileName:{} . fileSuffix:{}",fileName,fileSuffix);
        if (FileTypeEnum.getImageTypeDescList().contains(fileSuffix)) {
            User user = (User) subject.getPrincipal();
            Date now = DateUtils.getCurrentDateTime();
            if ("profilepic".equals(destination) || "blogbg".equals(destination)) {
                // 上传的位置
                String path = null;
                if ("profilepic".equals(destination)) {
//                    path = request.getSession().getServletContext().getRealPath("/uploads/avatar/");
                    path = fileDir + "/uploads/avatar/";
                } else if ("blogbg".equals(destination)) {
//                    path = request.getSession().getServletContext().getRealPath("/uploads/blogBg/");
                    path = fileDir+ "/uploads/blogBg/";
                }
                if (path == null) {
                    return Msg.fail().setMsg("路径错误!");
                }

                try {
                    String savePath = userService.saveUserProfilePic(user.getUserId(), destination, path, file);
                    log.info("save profilepic success. path:{}",savePath);
                    shiroHelper.clearAuthenticationInfo();
                    return Msg.success().add("path", savePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    return Msg.fail().setMsg("图片上传失败!");
                }
            } else if ("blog".equals(destination)) {
                String path = null;
                if ("blog".equals(destination)) {
//                    path = request.getSession().getServletContext().getRealPath("/image/" + user.getUserId() + "/blog/");
                    path = fileDir+"/image/" + user.getUserId() + "/blog/";
                }
                try {
                    FileHelper fileHelper = new FileHelper();
                    fileHelper.setUserId(user.getUserId());
                    fileHelper.setSaveType("localPath");
                    fileHelper.setFileUsage("blog");
                    fileHelper.setFileCreateTime(now);
                    fileHelper.setFileStatus(BlogController.BlogStatusEnum.NORMAL_STATUS.getCode());
                    String savePath = fileService.saveFile(fileHelper, destination, path, file);
                    log.info("save blog image success. savePath:{}",savePath);
                    return Msg.success().add("success", 1).add("message", "上传成功!").add("url", "/file/image/" + fileHelper.getFileId());
                } catch (IOException e) {
                    e.printStackTrace();
                    return Msg.fail().setMsg("图片上传失败!");
                }


            } else {
                return Msg.fail().setMsg("参数不正确!");
            }
        } else {
            return Msg.fail().setMsg("图片格式不符合要求!");
        }

    }

    /**
     * 保存的是blog editor中上传的blog 插入图片。
     * 这里是对前端js插件，markdown编辑器 editor.md 中插入图片功能做的相应适配
     * @param file
     * @param request
     * @return
     */
    @RequiresUser
    @RequiresRoles(value = {"user"})
    @ResponseBody
    @RequestMapping("/upload/blogImage")
    public Map<String, String> saveEditorImage(@RequestParam("editormd-image-file") MultipartFile file, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        Map<String, String> resultMap = new HashMap<String, String>();

        User user = (User) subject.getPrincipal();
        Date now = DateUtils.getCurrentDateTime();
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);//文件后缀
        if (!FileTypeEnum.getImageTypeDescList().contains(fileSuffix)) {
            resultMap.put("success", "0");
            resultMap.put("message", "文件格式不符合");
            return resultMap;
        }
        log.info("filename:{}   fileSuffix:{}",fileName,fileSuffix);
//        String path = request.getSession().getServletContext().getRealPath("/image/" + user.getUserId() + "/blog/");
        String path = fileDir+"/image/" + user.getUserId() + "/blog/";
        try {
            FileHelper fileHelper = new FileHelper();
            fileHelper.setUserId(user.getUserId());
            fileHelper.setSaveType("localPath");
            fileHelper.setFileUsage("blog");
            fileHelper.setFileCreateTime(now);
            fileHelper.setFileStatus(BlogController.BlogStatusEnum.NORMAL_STATUS.getCode());
            String savePath = fileService.saveFile(fileHelper, "blog", path, file);
            log.info("savePath:{}",savePath);
            /**
             * 项目服务器地址路径
             */
            String projectServerPath = request.getScheme() + "://" + request.getServerName() + ":" +
                    request.getServerPort() + request.getContextPath();
            resultMap.put("success", "1");
            resultMap.put("message", "上传成功");
            resultMap.put("url", projectServerPath + "/file/image/" + fileHelper.getFileId());
            return resultMap;
        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("success", "0");
            resultMap.put("message", "上传失败");
            return resultMap;
        }

    }

    /**
     * 删除图片。
     * 还是与前端js插件fileinput的删除功能做了相应适配。但是前端禁用掉了删除后端图片的功能(但是调试成功了)，所以此功能不用。
     * @param destination 区分删除的是头像还是背景。
     * @param request
     * @return
     */
    @RequiresUser
    @RequiresRoles(value = {"user"})
    @ResponseBody
    @RequestMapping(value = "/deletePic", method = RequestMethod.POST)
    public Msg deleteImage(@RequestParam("destination") String destination, HttpServletRequest request) {
        log.debug("destination:{}",destination);
        Subject subject = SecurityUtils.getSubject();
        // 1.首选判断当前用户是否已登录过
        if (subject.isRemembered() || subject.isAuthenticated()) {
            if ("profilepic".equals(destination) || "blogbg".equals(destination)) {
                // 上传的位置
                String path = null;
                if ("profilepic".equals(destination)) {
//                    path = request.getSession().getServletContext().getRealPath("/uploads/avatar/");
                    path = fileDir+"/uploads/avatar/";
                } else if ("blogbg".equals(destination)) {
//                    path = request.getSession().getServletContext().getRealPath("/uploads/blogBg/");
                    path = fileDir+"/uploads/blogBg/";
                }
                if (path == null) {
                    return Msg.fail().setMsg( "路径错误!");
                }
                User user = (User) SecurityUtils.getSubject().getPrincipal();
                userService.resetUserProfilePic(user.getUserId(), destination, path, "default.jpeg");
                log.info("delete success. user Id:{}",user.getUserId());
                return Msg.success().add("path", "default.jpeg");
            } else {
                return Msg.fail().setMsg( "参数不正确!");
            }
        } else {
            return Msg.stranger().setMsg("当前未登录!");
        }
    }

    /**
     * 向前端展示blog插入图片。
     * 注意这里request url并不是图片在项目中真实的路径。
     * 需要根据传入的file Id从数据库取得file 保存路径，再根据路径取得file。
     * @param fileId
     * @param request
     * @param response
     */
    @RequestMapping(value = "/image/{fileId}", method = RequestMethod.GET)
    public void showBlogImage(@PathVariable("fileId") Long fileId, HttpServletRequest request, HttpServletResponse response) {
        log.debug("fileId:{}",fileId);
        FileHelper fileHelper = fileService.getFileHelperByFileId(fileId);
        if (fileHelper != null) {
//            String path = request.getSession().getServletContext().getRealPath("/image/" + fileHelper.getUserId() + "/blog/");
            String path = fileDir + "/image/" + fileHelper.getUserId() + "/blog/";
            File imgFile = new File(path + fileHelper.getFileName() + "." + fileHelper.getFileSuffix());
            responseFile(response, imgFile);
        }
    }

    /**
     * 向前端展示头像图片。
     * 注意这里request url并不是图片在项目中真实的路径。
     * 需要根据传入的file name和格式来取得图片。并不经过数据库。
     * @param fileName
     * @param suffix
     * @param request
     * @param response
     */
    @RequestMapping(value = "/showPic/{filename}.{suffix}", method = RequestMethod.GET)
    public void showProfilePic(@PathVariable("filename") String fileName, @PathVariable("suffix") String suffix, HttpServletRequest request, HttpServletResponse response) {
        log.debug("fileName:{}  suffix:{}",fileName,suffix);
        if (!FileTypeEnum.getImageTypeDescList().contains(suffix)) {
            return;
        }
//        String path = request.getSession().getServletContext().getRealPath("/uploads/avatar/");
        String path=fileDir+"/uploads/avatar/";
        log.info("context path + upload path:{}",path);
        File imgFile = new File(path + fileName + "." + suffix);

        responseFile(response, imgFile);
    }


    /**
     * 向前端展示背景图片。
     * 注意这里request url并不是图片在项目中真实的路径。
     * 需要根据传入的file name和格式来取得图片。并不经过数据库。
     * @param fileName
     * @param suffix
     * @param request
     * @param response
     */
    @RequestMapping(value = "/showBg/{filename}.{suffix}", method = RequestMethod.GET)
    public void showBackgroundImage(@PathVariable("filename") String fileName, @PathVariable("suffix") String suffix, HttpServletRequest request, HttpServletResponse response) {
        log.debug("fileName:{}  suffix:{}",fileName,suffix);
        if (!FileTypeEnum.getImageTypeDescList().contains(suffix)) {
            return;
        }
//        String path = request.getSession().getServletContext().getRealPath("/uploads/blogBg/");
        String path = fileDir+"/uploads/blogBg/";
        File imgFile = new File(path + fileName + "." + suffix);
        responseFile(response, imgFile);
    }

    /**
     * 下载图片。
     * @param fileName
     * @param suffix
     * @param request
     * @param response
     */
    @RequiresUser
    @RequiresRoles(value = {"user"})
    @RequestMapping(value = "/downloadPic/{filename}.{suffix}", method = RequestMethod.GET)
    public void downloadImage(@PathVariable("filename") String fileName, @PathVariable("suffix") String suffix, HttpServletRequest request, HttpServletResponse response) {
        log.debug("fileName:{}  suffix:{}",fileName,suffix);
        if (!FileTypeEnum.getImageTypeDescList().contains(suffix)) {
            return;
        }
        // 上传的位置
//        String path = request.getSession().getServletContext().getRealPath("/uploads/avatar/");
        String path = fileDir+"/uploads/avatar/";
        File imgFile = new File(path + fileName + "." + suffix);
        // 设置下载的响应头信息
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + fileName + "." + suffix);

        responseFile(response, imgFile);

    }

    /**
     * 响应输出图片文件
     *
     * @param response
     * @param imgFile
     */
    private void responseFile(HttpServletResponse response, File imgFile) {
        try (InputStream is = new FileInputStream(imgFile);
             OutputStream os = response.getOutputStream();) {
            byte[] buffer = new byte[1024]; // 图片文件流缓存池
            while (is.read(buffer) != -1) {
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
