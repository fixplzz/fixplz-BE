package com.fixplz.facility.domain.service;

import com.google.zxing.WriterException;

public interface QRApiDomainService {
    byte[] createQRcode(Long facilityNo) throws WriterException;

    String uploadQRcodeToS3(byte[] qrByte);
}
