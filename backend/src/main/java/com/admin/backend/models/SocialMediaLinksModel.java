package com.admin.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "SocialMediaLinks")
public class SocialMediaLinksModel {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "social_media_link_id")
    @JsonProperty("social_media_link_id")
    private Long socialMediaLinkId;

    @Column(name = "platform_name")
    @JsonProperty("platform_name")
    private String platformName;

    @Column(name = "profile_url")
    @JsonProperty("profile_url")
    private String profileUrl;
    
    @Column(name = "is_active")
    @JsonProperty("is_active")
    private boolean isActive;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    private java.time.LocalDateTime updatedAt;

}
