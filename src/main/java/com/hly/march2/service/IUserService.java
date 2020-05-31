package com.hly.march2.service;

import com.hly.march2.dto.UserSearchDTO;
import com.hly.march2.entity.User;
import com.hly.march2.vo.UserBriefStatisticsVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUserService {

    List<User> findUserByEmail(String userName);

    List<User> findUserByEmailOnlyFirst(String userEmail);

    List<User> findUserByPhoneNum(String userPhoneNum);

    User getUserByUserId(Long userId);

    void registerUserAccount(User user);

    String saveUserProfilePic(Long userId,String destination,String path, MultipartFile file) throws IOException;

    void resetUserProfilePic(Long userId,String destination,String path,String defaultFilename);

    Integer updateUserByUserId(User user);

    List<User> getUserByLikeUserName(String userName);

    List<User> getTopRankUserByLimit(Integer limit);

    List<User> getLatestLoginUserByLimit(Integer limit);

    List<UserBriefStatisticsVO> countSumUserBlogByBlogExample(List<Integer> statusList);

    Long countTotalUser(List<Integer> statusList);

    List<User> getUserBySearch(UserSearchDTO userSearchDTO);

}
