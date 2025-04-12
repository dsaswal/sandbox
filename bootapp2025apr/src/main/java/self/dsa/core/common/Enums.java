package self.dsa.core.common;

import jakarta.persistence.*;

public class Enums {
    public enum PartyType {
        INDIVIDUAL("Individual"),
        CORPORATE("Corporate"),
        COUNTERPARTY("Counterparty");

        private final String displayName;

        PartyType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
    
    public enum TransactionStatus {
        PENDING("Pending"),
        COMPLETED("Completed"),
        FAILED("Failed");

        private final String displayName;

        TransactionStatus(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public enum PartyStatus {
        ACTIVE("Active"),
        INACTIVE("Inactive");

        private final String displayName;

        PartyStatus(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public enum IdentityDocumentType {
        PASSPORT("Passport"),
        RESIDENTPERMIT("ResidentPermit"),
        NATIONALID("NationalId");

        private final String displayName;

        IdentityDocumentType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
        
    public enum IdentityDocumentStatus {
        ACTIVE("Active"),
        EXPIRED("Expired");

        private final String displayName;

        IdentityDocumentStatus(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
    // Add more enums here (hundreds if needed)
}