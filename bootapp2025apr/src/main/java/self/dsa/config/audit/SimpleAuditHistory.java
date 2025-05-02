package self.dsa.config.audit;



import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "simple_audit_history")
public class SimpleAuditHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "operation")
    private String operation;

    @Column(name = "changed_by")
    private String changedBy;

    @Column(name = "changed_at")
    private OffsetDateTime changedAt;

    @Column(name = "entity_snapshot", length = 4000)
    private String entitySnapshot;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public OffsetDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(OffsetDateTime changedAt) {
        this.changedAt = changedAt;
    }

    public String getEntitySnapshot() {
        return entitySnapshot;
    }

    public void setEntitySnapshot(String entitySnapshot) {
        this.entitySnapshot = entitySnapshot;
    }
}

