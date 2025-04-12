package self.dsa.core.party.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import self.dsa.core.party.entity.IdentityDocument;

@Repository
public interface IdentityDocumentRepository extends JpaRepository<IdentityDocument,Long> {
    
}