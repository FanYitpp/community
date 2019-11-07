package com.hkd.community.service;

import com.hkd.community.dto.PaginationDTO;
import com.hkd.community.dto.QuestionDTO;
import com.hkd.community.mapper.QuestionMapper;
import com.hkd.community.mapper.UserMapper;
import com.hkd.community.model.Question;
import com.hkd.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO getAllQuestion(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.getCount();
        Integer totalPages;
        if(totalCount % size ==0){
            totalPages = totalCount / size;
        }else {
            totalPages = totalCount / size + 1;
        }

        if(page <1 ){
            page = 1;
        }
        if(page > totalPages){
            page = totalPages;
        }
        paginationDTO.setPagination(totalPages,page);
        Integer offset = size*(page-1);
        List<Question> questions = questionMapper.getAllQuestion(offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);

        return paginationDTO;
    }

    public PaginationDTO getMyQuestion(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.getCountById(userId);
        Integer totalPages;
        if(totalCount % size ==0){
            totalPages = totalCount / size;
        }else {
            totalPages = totalCount / size + 1;
        }
        if(page <1 ){
            page = 1;
        }
        if(page > totalPages){
            page =  totalPages;
        }
        paginationDTO.setPagination(totalPages,page);

        Integer offset = size*(page-1);
        List<Question> questions = questionMapper.getMyQuestion(userId ,offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);

        return paginationDTO;
    }
}
