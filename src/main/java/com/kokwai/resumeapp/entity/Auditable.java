package com.kokwai.resumeapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.*;

/**
 * @author chankokwai
 * @project ResumeApp
 * @date 15/9/24
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @Id
    @SequenceGenerator( name = "primary_key_seq", sequenceName = "primary_key_seq", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "primary_key_seq")
    @Column( name = "id", updatable = false )
    private Long id;
    private String referenceId = new AlternativeJdkIdGenerator().generateId().toString();

    @NotNull
    private Long createdBy;

    @NotNull
    private Long updatedBy;

    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreatedDate
    @Column(name = "updated_at", updatable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforePersist() {
        var user_id = 1L;
        if( user_id == null ) {
            throw new ApiException("Cannot persists entity without user id in request context for this thread");
        }
        setCreatedAt( now() );
        setCreatedBy( user_id );
        setUpdatedBy( user_id );
        setUpdatedAt( now() );
    }

    @PreUpdate
    public void beforeUpdate() {
        var user_id = 1L;
        if( user_id == null )
        {
            throw new ApiException( "Cannot persists entity without user id in request context for this thread" );
        }
        setUpdatedAt( now() );
        setUpdatedBy( user_id );
    }

}
