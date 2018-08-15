package com.thorn.springboost.dao;

import com.thorn.springboost.model.post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface postMapper {

    List<post> findpost(String content, int page);//-1*10
}