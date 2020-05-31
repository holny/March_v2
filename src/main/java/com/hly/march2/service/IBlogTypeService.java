package com.hly.march2.service;

import com.hly.march2.entity.BlogType;
import com.hly.march2.entity.Draft;

import java.util.List;

public interface IBlogTypeService {
    Integer insertTypeByInitial(String typeName,Long userId);

    List<BlogType> getTypeByTypeNameAndUserId(String typeName, Long userId);

    List<BlogType> getTypeByTypeName(String TypeName);

    void insertTypeWithCheck(Draft draft,BlogType blogType);

    List<BlogType> getTypeByUserId(Long userId);

    List<BlogType> getTypeByUserIdAndStatus(Long userId,List<Integer> statusList);

    List<BlogType> getTypeByStatus(List<Integer> statusList);

    List<BlogType> getAllType();
}
