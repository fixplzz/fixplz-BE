package com.fixplz.common.utils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.fixplz.common.utils.dto.response.S3StrorageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class S3Storage {

    private final AmazonS3Client amazonS3Client;

    private final List<String> extension = Arrays.asList(".png", ".jpg", ".jpeg", ".bmp");

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /* 파일 - 다중 업로드 */
    public List<String> uploadFiles(List<MultipartFile> multipartFilesList, String dirName, String fileName) {

        return multipartFilesList.stream().map(multipartFile -> {
                    try {
                        return uploadFile(multipartFile, dirName, fileName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).toList();
    }

    /* 파일 - 단일 업로드 */
    public String uploadFile(MultipartFile multipartFile, String dirName, String fileName) throws IOException {

        File file = convertMultipartFileToFile(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultrpartFile to file 변환에 실패하였습니다."));
        String key = buildKey(dirName, fileName);

        return putFileToS3(file, key);
    }

    /* 파일 - S3 연동 */
    private String putFileToS3(File uploadFile, String key) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, key, uploadFile));
        uploadFile.delete();
//        if (uploadFile.delete()) {
//            log.info("파일이 삭제되었습니다.");
//        } else {
//            log.info("파일 삭제에 실패하였습니다.");
//        }
        return amazonS3Client.getUrl(bucket, key).toString();
    }

    /* bytes - 단일 업로드 */
    public String uploadBytes(byte[] bytes, String dirName, String fileName) {
        String key = buildKey(dirName, fileName);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        objectMetadata.setContentLength(bytes.length);

        try (InputStream inputStream = new ByteArrayInputStream(bytes)) {
            return putBytesToS3(key, inputStream, objectMetadata);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "S3 업로드에 실패하였습니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* bytes - S3 연동 */
    private String putBytesToS3(String key, InputStream inputStream, ObjectMetadata objectMetadata) {

        amazonS3Client.putObject(new PutObjectRequest(bucket, key, inputStream, objectMetadata));

        return amazonS3Client.getUrl(bucket, key).toString();
    }


    /* s3 내 파일 다운로드 */
    public S3StrorageResponse downloadFile(String key) throws IOException {
        S3Object s3Object = amazonS3Client.getObject(bucket, key);
        S3ObjectInputStream out = s3Object.getObjectContent();  // S3ObjectInputStream은 Java로 동작하는 InputStream 객체이다
        byte[] bytesFile = IOUtils.toByteArray(out);    // toByteArray()안에서 ByteArrayOutputStream output을 통해 write를 하고 결과물을 byteArray로 리턴한다.

        // 사진 명 인코딩 작업
        String fileName = URLEncoder.encode(key, StandardCharsets.UTF_8).replaceAll("\\+", "%20");  // URI에서 공백을 '%20'으로 표현함
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        httpHeaders.setContentLength(bytes.length);
//        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return S3StrorageResponse.of(bytesFile, fileName);
    }

    /* s3 내 파일 이동 */
    public void moveFileToS3(String key, String targetKey) {

        copyFile(key, targetKey);
        deleteFile(key);
    }

    /* 파일 복사 */
    public void copyFile(String key, String targetKey) {
        amazonS3Client.copyObject(new CopyObjectRequest(bucket, key, bucket, targetKey));
//        log.info("아마존 S3 객체 복사에 성공하였습니다.");
    }

    /* 파일 삭제 */
    private void deleteFile(String key) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, key));
//        log.info("아마존 S3 객체 삭제에 성공하였습니다.");
    }


    /* 파일 형태 변환 메소드 */
    private Optional<File> convertMultipartFileToFile(MultipartFile file) throws IOException {

        File convertFile = new File(file.getOriginalFilename());

        if (convertFile.createNewFile()) {
            try (FileOutputStream out = new FileOutputStream(convertFile)) {
                out.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }


    /* 파일 저장 경로 설정 */
    private String buildKey(String dirName, String fileName) {
        int fileExtensionIndex = fileName.lastIndexOf(".");
        String fileExtension = fileName.substring(fileExtensionIndex);
        String newFileName = fileName.substring(0, fileExtensionIndex);
//        String now = String.valueOf(System.currentTimeMillis());    // 1970년부터 지금까지를 초 단위로 계산

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(new Date());

        return dirName + "/" + now + "_" + newFileName + fileExtension;
    }


}
