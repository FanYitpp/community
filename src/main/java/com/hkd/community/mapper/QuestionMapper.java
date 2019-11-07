package com.hkd.community.mapper;

import com.hkd.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title, description, gmt_create, gmt_modified, creator, comment_count, view_count, like_count, tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset} , #{size}")
    List<Question> getAllQuestion( Integer offset, Integer size);

    @Select("select count(1) from question")
    Integer getCount();

    @Select("select * from question where creator = #{userId} limit #{offset} , #{size}")
    List<Question> getMyQuestion(Integer userId, Integer offset, Integer size);

    @Select("select count(1) from question where creator = #{userId}")
    Integer getCountById(Integer userId);

}
