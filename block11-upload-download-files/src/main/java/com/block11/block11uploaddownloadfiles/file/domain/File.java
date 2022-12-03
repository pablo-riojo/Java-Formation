package com.block11.block11uploaddownloadfiles.file.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String category;

    @Column
    private String name;

    @Column
    private String root;

    @Column
    private LocalDateTime uploadDate;

    public File(String category, String name, String root) {
        setCategory(category);
        setName(name);
        setRoot(root);
        setUploadDate(LocalDateTime.now());
    }
}
