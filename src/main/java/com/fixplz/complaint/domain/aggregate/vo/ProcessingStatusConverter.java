package com.fixplz.complaint.domain.aggregate.vo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ProcessingStatusConverter implements AttributeConverter<ProcessingStatus, String> {

    @Override
    public String convertToDatabaseColumn(ProcessingStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public ProcessingStatus convertToEntityAttribute(String dbData) {
        return ProcessingStatus.ofCode(dbData);
    }

}
