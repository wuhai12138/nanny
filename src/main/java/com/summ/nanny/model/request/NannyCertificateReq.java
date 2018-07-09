package com.summ.nanny.model.request;

import org.springframework.web.multipart.MultipartFile;

public class NannyCertificateReq {
    private MultipartFile file;
    private Integer nannyCertId;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getNannyCertId() {
        return nannyCertId;
    }

    public void setNannyCertId(Integer nannyCertId) {
        this.nannyCertId = nannyCertId;
    }
}
