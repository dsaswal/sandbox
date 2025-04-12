package self.dsa.core.party.entity;

import java.time.LocalDate;

//import org.hibernate.envers.Audited;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import self.dsa.core.common.Enums;
import self.dsa.core.config.audit.Auditable;

@Entity
//@Audited
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Party extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String reference;
    private String externalReference;
    @Enumerated(EnumType.STRING)
    private Enums.PartyType type;
    private String name;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Enums.PartyStatus status;
    private LocalDate openingDate;
    private LocalDate closingDate;
    @Embedded

    @PrePersist
    public void PrePersist() {
        this.setStatus(Enums.PartyStatus.ACTIVE);
        if(this.getName() == null) {
            this.setName(this.getFirstName() + " " + this.getLastName());
        }

        //setAuditData(auditData.setDefaultAuditDataAtPersist(getAuditData()));

    }
}