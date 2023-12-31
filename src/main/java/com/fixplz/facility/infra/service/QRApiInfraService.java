package com.fixplz.facility.infra.service;

import com.fixplz.common.annotation.InfraService;
import com.fixplz.common.utils.S3Storage;
import com.fixplz.facility.domain.service.QRApiDomainService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;

@InfraService
@RequiredArgsConstructor
public class QRApiInfraService implements QRApiDomainService {

    private final S3Storage s3Storage;

    @Override
    public byte[] createQRcode(Long facilityNo) throws WriterException {

        // QR 정보
        int width = 250;
        int height = 250;
        String url = "http://localhost:3000/api/v1/complaint/page?facilityNo=" + facilityNo;

        //  QR Code -BitMatrix: QR code 정보 생성
        BitMatrix encode = new MultiFormatWriter()
                .encode(url, BarcodeFormat.QR_CODE, width, height);

        // QR Code - Image 생성(Stream으로 생성, 1회성 아니면 File로 작성 가능)
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(encode, "PNG", out); // QR 코드 이미지를 바이트 배열 형태로 저장, flush 작업 자동 실행해줌
            return out.toByteArray();   // 바이트 형태로 QR code Image 출력
        } catch (Exception e) {
            throw new RuntimeException("QR code 생성에 실패하였습니다. 잠시 후 다시 시도해주세요.");
        }
    }

    @Override
    public String uploadQRcodeToS3(byte[] qrByte, String facilityName) {
        String dirName = "qr";
        return s3Storage.uploadBytes(qrByte, dirName, facilityName);
    }
}
