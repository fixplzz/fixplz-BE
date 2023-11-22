package com.fixplz.facility.domain.service;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QRApiDomainService {
    byte[] createQRcode(Long facilityNo) throws WriterException;

    String uploadQRcodeToS3(byte[] qrByte, String facilityName) throws IOException;
}
