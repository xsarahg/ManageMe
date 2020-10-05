package com.novi.ManageMe.models.files;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Getter  // lombok shortcut for inserting getters
@Setter // lombok shortcut for inserting setters
@MappedSuperclass // class whose mapping information is applied to the entities that inherit from the class
public abstract class FileModel {

    @Column(name = "user_id")
    protected Long userId;

    @Column(name = "description")
    @Size(max = 250)
    protected String description;

    @Column(name = "file_name")
    protected String fileName;

    @Column(name = "file_type")
    @Size(max = 20)
    protected String fileType;

    @Column(name = "data")
    @Lob // maps binary or large character objects
    protected byte[] data; // the content of the files will be stored as a byte array

    public FileModel(Long userId, String description, String fileName, String fileType, byte[] data) {
        this.userId = userId;
        this.description = description;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
}
