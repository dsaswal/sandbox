package self.dsa.core.party.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import self.dsa.core.party.entity.Party;

public interface PartyRepository extends JpaRepository<Party,Long> {
    
    @Query("FROM Party p where p.lastName = :partyName")
    List<Party> getParty(@Param("partyName") String partyName);

}