package self.dsa.core.party.DTO;

import self.dsa.core.party.entity.IdentityDocument;
import self.dsa.core.party.entity.Party;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndividualPartyDTO {
    private Party party;
    private IdentityDocument identityDocument;    
}