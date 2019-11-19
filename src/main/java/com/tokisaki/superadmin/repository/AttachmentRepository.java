package com.tokisaki.superadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tokisaki.superadmin.domain.Attachment;
@RepositoryRestResource(path = "attachment", collectionResourceRel = "attachment", itemResourceRel = "attachment")
public interface AttachmentRepository extends JpaRepository<Attachment, String> {

    
    
}
