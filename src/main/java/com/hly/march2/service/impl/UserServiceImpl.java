package com.hly.march2.service.impl;

import com.hly.march2.controller.UserController;
import com.hly.march2.dao.UserMapper;
import com.hly.march2.dto.UserSearchDTO;
import com.hly.march2.entity.BlogExample;
import com.hly.march2.entity.User;
import com.hly.march2.entity.UserExample;
import com.hly.march2.service.IUserService;
import com.hly.march2.vo.UserBriefStatisticsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 通过邮件查找用户
     * @param userEmail
     * @return
     */
    @Override
    public List<User> findUserByEmail(String userEmail) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserEmailEqualTo(userEmail);
        return  userMapper.selectByExample(example);
    }

    /**
     * 通过邮件查找用户
     * @param userEmail
     * @return
     */
    @Override
    public List<User> findUserByEmailOnlyFirst(String userEmail) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserEmailEqualTo(userEmail);
        example.setOrderByClause("user_id limit 1");
        return userMapper.selectByExample(example);
    }


    /**
     * 通过手机号码查找用户
     * @param userPhoneNum
     * @return
     */
    @Override
    public List<User> findUserByPhoneNum(String userPhoneNum) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserTelephoneNumberEqualTo(userPhoneNum);
        return userMapper.selectByExample(example);
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 注册用户
     * insert 返回的int是受影响的行数，如果spring 中SqlSessionTemplate的 executorType为BATCH,返回的就不是受影响行数。但是BATCH批处理效率更高。
     * @param user
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void registerUserAccount(User user) {
        userMapper.insert(user);
    }

    @Override
    @Transactional(readOnly = false)
    public String saveUserProfilePic(Long userId,String destination, String path, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".")+1);//文件后缀

        // 判断该路径是否存在
        File file_path = new File(path);
        if (!file_path.exists()) {
            // 创建该文件夹
            file_path.mkdirs();
        }
        // 把文件名称设置成唯一值
        fileName=userId+"_"+new Date().getTime()+"_"+new Random().nextInt(1000);//新的文件名

        File saveFile = new File(file_path, fileName+"."+fileSuffix);

        file.transferTo(saveFile);
        User originUser = userMapper.selectByPrimaryKey(userId);
        User user = new User();
        user.setUserId(userId);
        if ("profilepic".equals(destination)) {
            if(originUser.getUserProfilePic()!=null){
                File oldPic =new File(path+originUser.getUserProfilePic());
                // 判断目录或文件是否存在
                if(oldPic.exists()&&oldPic.isFile()){
                    oldPic.delete();
                }
            }
            user.setUserProfilePic(fileName + "." + fileSuffix);
        }else if("blogbg".equals(destination)){
            if(originUser.getUserBg()!=null){
                File oldPic =new File(path+originUser.getUserBg());
                // 判断目录或文件是否存在
                if(oldPic.exists()&&oldPic.isFile()){
                    oldPic.delete();
                }
            }
            user.setUserBg(fileName + "." + fileSuffix);
        }
        userMapper.updateByPrimaryKeySelective(user);
        return fileName+"."+fileSuffix;
    }

    @Override
    @Transactional(readOnly = false)
    public void resetUserProfilePic(Long userId,String destination, String path,String defaultFilename) {
        User originUser = userMapper.selectByPrimaryKey(userId);
        if ("profilepic".equals(destination)) {
            if(originUser.getUserProfilePic()!=null){
                File oldPic =new File(path+originUser.getUserProfilePic());
                // 判断目录或文件是否存在
                if(oldPic.exists()&&oldPic.isFile()){
                    oldPic.delete();
                }
            }
            User user = new User();
            user.setUserId(userId);
            user.setUserProfilePic(defaultFilename);
            userMapper.updateByPrimaryKeySelective(user);
        }else if("blogbg".equals(destination)){
            if(originUser.getUserBg()!=null){
                File oldPic =new File(path+originUser.getUserBg());
                // 判断目录或文件是否存在
                if(oldPic.exists()&&oldPic.isFile()){
                    oldPic.delete();
                }
            }
            User user = new User();
            user.setUserId(userId);
            user.setUserBg(null);
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Integer updateUserByUserId(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<User> getUserByLikeUserName(String userName) {
        UserExample example1 = new UserExample();
        UserExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andUserNameLike("%"+userName.trim()+"%");
        List<User> userList1= userMapper.selectByExample(example1);

        UserExample example2 = new UserExample();
        UserExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andUserNicknameLike("%"+userName.trim()+"%");
        List<User> userList2= userMapper.selectByExample(example2);
        userList1.addAll(userList2);
        return userList1;
    }

    @Override
    public List<User> getTopRankUserByLimit(Integer limit) {
        UserExample example = new UserExample();
//        UserExample.Criteria criteria = example.createCriteria();
        String order = "user_credit desc";
        if(limit!=null&&limit>0){
            order += " limit "+limit;
        }
        example.setOrderByClause(order);
        return userMapper.selectByExample(example);
    }

    @Override
    public List<User> getLatestLoginUserByLimit(Integer limit) {
        UserExample example = new UserExample();
//        UserExample.Criteria criteria = example.createCriteria();
        String order = "user_last_login_time desc";
        if(limit!=null&&limit>0){
            order += " limit "+limit;
        }
        example.setOrderByClause(order);
        return userMapper.selectByExample(example);
    }

    @Override
    public List<UserBriefStatisticsVO> countSumUserBlogByBlogExample(List<Integer> statusList) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andBlogStatusIn(statusList);
        return userMapper.countSumUserBlogByBlogExample(example);
    }

    @Override
    public Long countTotalUser(List<Integer> statusList) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andAccountStatusIn(statusList);
        return userMapper.countByExample(example);
    }

    @Override
    public List<User> getUserBySearch(UserSearchDTO userSearchDTO) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        List<Long> userIdList = new ArrayList<>();
        if(userSearchDTO.getUserName()!=null&&!"".equals(userSearchDTO.getUserName().trim())){
            List<User> userList = getUserByLikeUserName(userSearchDTO.getUserName().trim());
            if(userList.size()>0) {
                for (User u : userList) {
                    userIdList.add(u.getUserId());
                }
            }
        }
        if(userSearchDTO.getUserId()!=null){
            userIdList.add(userSearchDTO.getUserId());
        }
        if(userIdList.size()>0){
            criteria.andUserIdIn(userIdList);
        }

        if(userSearchDTO.getUserEmail()!=null&&!"".equals(userSearchDTO.getUserEmail().trim())){
            criteria.andUserEmailLike("%"+userSearchDTO.getUserEmail().trim()+"%");
        }
        if(userSearchDTO.getUserTelephoneNumber()!=null){
            criteria.andUserTelephoneNumberLike("%"+userSearchDTO.getUserTelephoneNumber().trim()+"%");
        }

        if(userSearchDTO.getLastLoginTimeStart()!=null&&userSearchDTO.getLastLoginTimeEnd()!=null){
            if(userSearchDTO.getLastLoginTimeStart().before(userSearchDTO.getLastLoginTimeEnd())) {
                criteria.andUserLastLoginTimeBetween(userSearchDTO.getLastLoginTimeStart(),userSearchDTO.getLastLoginTimeEnd());
            }else{
                return new ArrayList<User>();
            }
        }else if(userSearchDTO.getLastLoginTimeStart()!=null){
            criteria.andUserLastLoginTimeGreaterThanOrEqualTo(userSearchDTO.getLastLoginTimeStart());
        }else if(userSearchDTO.getLastLoginTimeEnd()!=null){
            criteria.andUserLastLoginTimeLessThanOrEqualTo(userSearchDTO.getLastLoginTimeEnd());
        }

        if(userSearchDTO.getUserRights()!=null){
            criteria.andUserRightsEqualTo(userSearchDTO.getUserRights());
        }

        if(userSearchDTO.getAccountStatus()!=null){
            criteria.andAccountStatusEqualTo(userSearchDTO.getAccountStatus());
        }

        if(userSearchDTO.getListSort()!=null&&!"".equals(userSearchDTO.getListSort().trim())){
            String order = null;
            if(UserController.UserSortEnum.LOGIN_TIME_ASC.getDesc().equals(userSearchDTO.getListSort())){
                order = "user_last_login_time asc";
            }else if(UserController.UserSortEnum.LOGIN_TIME_DESC.getDesc().equals(userSearchDTO.getListSort())){
                order = "user_last_login_time desc";
            }else if(UserController.UserSortEnum.REGISTER_TIME_ASC.getDesc().equals(userSearchDTO.getListSort())){
                order = "user_register_time asc";
            }else if(UserController.UserSortEnum.REGISTER_TIME_DESC.getDesc().equals(userSearchDTO.getListSort())){
                order = "user_register_time desc";
            }else if(UserController.UserSortEnum.BIRTHDAY_TIME_ASC.getDesc().equals(userSearchDTO.getListSort())){
                order = "user_birthday asc";
            }else if(UserController.UserSortEnum.BIRTHDAY_TIME_DESC.getDesc().equals(userSearchDTO.getListSort())){
                order = "user_birthday desc";
            }else if(UserController.UserSortEnum.CREDIT_ASC.getDesc().equals(userSearchDTO.getListSort())){
                order = "user_credit asc";
            }else if(UserController.UserSortEnum.CREDIT_DESC.getDesc().equals(userSearchDTO.getListSort())){
                order = "user_credit desc";
            }
            if(order!=null){
                example.setOrderByClause(order);
            }
        }else{
            example.setOrderByClause("user_last_login_time desc");
        }
        return userMapper.selectMostColumnByExample(example);
    }


}
