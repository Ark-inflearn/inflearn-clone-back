package com.ark.inflearnback.configuration.security.model.entity;

import com.ark.inflearnback.domain.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleResource extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resource resource;

    private boolean deleted;

    private RoleResource(final Role role, final Resource resource, final boolean deleted) {
        this.role = role;
        this.resource = resource;
        this.deleted = deleted;
    }

    @Builder
    public static RoleResource of(final Role role, final Resource resource, final boolean deleted) {
        return new RoleResource(role, resource, deleted);
    }

    public String getRole() {
        return role.get();
    }

}