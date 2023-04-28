package com.example.pastebin.specification;

import com.example.pastebin.model.Paste;
import com.example.pastebin.enums.Status;
import org.springframework.data.jpa.domain.Specification;

public class PasteSpecificashion {

    public static Specification<Paste> byStatus(Status status){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.and(criteriaBuilder.equal(root.get("status"), status));
        };
    }

    public static Specification<Paste> byTitle(String titleText){
        return (root, query, criteriaBuilder) -> {
            if (titleText == null || titleText.isBlank()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.and(criteriaBuilder.like(root.get("title"), "%" + titleText + "%"));
        };
    }

    public static Specification<Paste> byBody(String bodyText){
        return (root, query, criteriaBuilder) -> {
            if (bodyText == null || bodyText.isBlank()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.and(criteriaBuilder.like(root.get("body"), "%" + bodyText + "%"));
        };
    }
}