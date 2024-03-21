package com.javabase.ocr;

public class OcrIdentifyReq {

    /**
     * 产品id编号.
     * 行驶证	5
     * 机动车合格证	68
     */
    private String pid;

    /**
     * 图片文件的BASE64编码.
     */
    private String filedata;

    /**
     * 识别信息Id.
     */
    private Long ocrIdentifyId;

    /**
     * 福顺文件存储主键ID.
     */
    private Long fileId;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getFiledata() {
        return filedata;
    }

    public void setFiledata(String filedata) {
        this.filedata = filedata;
    }

    public Long getOcrIdentifyId() {
        return ocrIdentifyId;
    }

    public void setOcrIdentifyId(Long ocrIdentifyId) {
        this.ocrIdentifyId = ocrIdentifyId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
}
