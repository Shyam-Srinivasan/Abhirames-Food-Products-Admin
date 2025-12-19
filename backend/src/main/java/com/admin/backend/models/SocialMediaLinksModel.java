package com.admin.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SocialMediaLinks")
@EntityListeners(AuditingEntityListener.class)
public class SocialMediaLinksModel {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "social_media_link_id")
    @JsonProperty("social_media_link_id")
    private Long socialMediaLinkId;

    @NotBlank(message = "Platform name cannot be empty")
    @Column(name = "platform_name", unique = true)
    @JsonProperty("platform_name")
    private String platformName;

    @NotBlank(message = "Platform url link cannot be empty")
    @Column(name = "profile_url")
    @JsonProperty("profile_url")
    private String profileUrl;
    
    @Column(name = "is_active")
    @JsonProperty("is_active")
    private boolean isActive;

    @Column(name = "created_at", updatable = false)
    @JsonProperty("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
