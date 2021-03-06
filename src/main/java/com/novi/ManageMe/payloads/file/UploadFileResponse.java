package com.novi.ManageMe.payloads.file;

import lombok.Getter;
import lombok.Setter;

@Getter  // lombok shortcut for inserting getters
@Setter // lombok shortcut for inserting setters
public class UploadFileResponse {

    private String fileName;

    private String fileDownloadUri;

    private String fileType;

    private Long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, Long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
