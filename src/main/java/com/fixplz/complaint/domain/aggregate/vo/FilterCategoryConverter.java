package com.fixplz.complaint.domain.aggregate.vo;


import jakarta.persistence.AttributeConverter;

public class FilterCategoryConverter implements AttributeConverter<FilterCategory, String> {

    @Override
    public String convertToDatabaseColumn(FilterCategory attribute) {
        return attribute.getCode();
    }

    @Override
    public FilterCategory convertToEntityAttribute(String dbData) {
        return FilterCategory.ofCode(dbData);
    }
}
