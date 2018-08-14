package com.thorn.springboost.dao;

import com.thorn.springboost.model.post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface postMapper {

    List<post> findPost(String content, int page);//-1*10
}