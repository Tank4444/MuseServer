package ru.chuikov.MuseServer.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "music")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "createDate")
    private Date created = new Date();

    @Column(name = "contentId",nullable = false)
    private String contentId;

    @Column(name = "contentLength")
    private long contentLength;

    @Column(name = "mimeType")
    private String mimeType;

    @ManyToOne
    @JoinColumn(name = "uaccount_id", nullable = true)
    private Uaccount uaccount;


    public Uaccount getUaccount() {
        return uaccount;
    }

    public void setUaccount(Uaccount uaccount) {
        this.uaccount = uaccount;
    }

    public Music() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

}
