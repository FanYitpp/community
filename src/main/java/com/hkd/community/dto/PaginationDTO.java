package com.hkd.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPages;
    public void setPagination(Integer totalPages ,Integer page ){

        this.totalPages = totalPages;
        this.page = page;
        pages.add(page);
        for(int i = 1 ; i <= 3 ; i ++){
            if(page - i > 0 ){
                pages.add( 0,page - i );
            }
            if(page + i <= totalPages ){
                pages. add( page + i);
            }
        }
        if(page == 1 ){
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        if(page.equals(totalPages)){
            showNext = false;
        }else {
            showNext = true;
        }

        if(pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        if(pages.contains(totalPages)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }

}
