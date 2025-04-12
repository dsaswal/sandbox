package self.dsa.core.party.entity;

import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
// import self.dsa.core.common.entity.AuditData;
import self.dsa.core.common.Enums;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IdentityDocument {

    @Id
    private Long partyId;
    @Enumerated(EnumType.STRING)
    private Enums.IdentityDocumentType identityDocumentType;
    private String identityDocumentReference;
    private LocalDate validFrom;
    private LocalDate validTill;
    @Enumerated(EnumType.STRING)
    private Enums.IdentityDocumentStatus status;
    private String remarks;
    //@Embedded
    //private AuditData auditData;

    @PrePersist
    public void PrePersist() {
        this.setStatus(Enums.IdentityDocumentStatus.ACTIVE);

        //setAuditData(auditData.setDefaultAuditDataAtPersist(getAuditData()));

    }
}