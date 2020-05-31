package com.hly.march2.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class FileHelper implements Serializable {
    private Long fileId;

    private Long caseId;

    private Long userId;

    private String saveType;

    private String filePath;

    private String fileName;

    private String fileSuffix;

    private Long fileSize;

    private String fileUsage;

    private Date fileCreateTime;

    private Integer fileStatus;

    private String serverPath;

    private String serverKey;

    private Integer fartherId;

    private String fartherName;

    private byte[] fileContent;

    private static final long serialVersionUID = 1L;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSaveType() {
        return saveType;
    }

    public void setSaveType(String saveType) {
        this.saveType = saveType == null ? null : saveType.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix == null ? null : fileSuffix.trim();
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileUsage() {
        return fileUsage;
    }

    public void setFileUsage(String fileUsage) {
        this.fileUsage = fileUsage == null ? null : fileUsage.trim();
    }

    public Date getFileCreateTime() {
        return fileCreateTime;
    }

    public void setFileCreateTime(Date fileCreateTime) {
        this.fileCreateTime = fileCreateTime;
    }

    public Integer getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(Integer fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath == null ? null : serverPath.trim();
    }

    public String getServerKey() {
        return serverKey;
    }

    public void setServerKey(String serverKey) {
        this.serverKey = serverKey == null ? null : serverKey.trim();
    }

    public Integer getFartherId() {
        return fartherId;
    }

    public void setFartherId(Integer fartherId) {
        this.fartherId = fartherId;
    }

    public String getFartherName() {
        return fartherName;
    }

    public void setFartherName(String fartherName) {
        this.fartherName = fartherName == null ? null : fartherName.trim();
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public String toString() {
        return "FileHelper{" +
                "fileId=" + fileId +
                ", caseId=" + caseId +
                ", userId=" + userId +
                ", saveType='" + saveType + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSuffix='" + fileSuffix + '\'' +
                ", fileSize=" + fileSize +
                ", fileUsage='" + fileUsage + '\'' +
                ", fileCreateTime=" + fileCreateTime +
                ", fileStatus=" + fileStatus +
                ", serverPath='" + serverPath + '\'' +
                ", serverKey='" + serverKey + '\'' +
                ", fartherId=" + fartherId +
                ", fartherName='" + fartherName + '\'' +
                ", fileContent=" + Arrays.toString(fileContent) +
                '}';
    }
}