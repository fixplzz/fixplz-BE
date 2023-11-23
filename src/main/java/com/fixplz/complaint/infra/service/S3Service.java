package com.fixplz.complaint.infra.service;

import com.fixplz.common.annotation.InfraService;
import com.fixplz.common.utils.S3Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@InfraService
@RequiredArgsConstructor
public class S3Service {

    private final S3Storage s3StorageService;

    public String uploadComplaintImage(MultipartFile image, String phoneNumber) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = simpleDateFormat.format(new Date());
        String fileName = phoneNumber.substring(6, 10) + date + "complaint-image";

        return s3StorageService.uploadFile(image, "complaint", fileName);
    }
}
