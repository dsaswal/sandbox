package self.dsa.core.party.controller;


import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import self.dsa.core.common.Enums;
import self.dsa.core.party.DTO.IndividualPartyDTO;
import self.dsa.core.party.entity.IdentityDocument;
import self.dsa.core.party.entity.Party;
import self.dsa.core.party.repository.IdentityDocumentRepository;
import self.dsa.core.party.repository.PartyRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/party")
public class PartyController {

    private static final Logger logger = LoggerFactory.getLogger(PartyController.class);

    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private IdentityDocumentRepository identityDocumentRepository;

    @PostMapping("/party")
    public Party createParty(@RequestBody Party party, @RequestHeader("userId") String userId) {
        logger.debug("++++++ inmethod ++++++" + "createParty");
        logger.debug("from request body --- " + party.toString());

/*         AuditData audData = new AuditData();
        audData.setAudChannel(AccessChannel.API);
        audData.setAudCreatedBy(userId);

        party.setAuditData(audData); */
        partyRepository.save(party);
        return party;
    }
    
    @PostMapping("/createIndividualParty")
    @Transactional
    public void createIndividualParty(@RequestBody IndividualPartyDTO reqObject, @RequestHeader("userId") String userId) {
        logger.debug("++++++ inmethod ++++++ " + "createIndividualParty");

        Party party = (Party) reqObject.getParty();
        logger.debug("from request body --- " + party.toString());
        IdentityDocument identityDocument = reqObject.getIdentityDocument();
        logger.debug("++++++ from request body --- ++++++ " + identityDocument.toString());

/*         AuditData audData = new AuditData();
        audData.setAudChannel(AccessChannel.API);
        audData.setAudCreatedBy(userId);
        party.setAuditData(audData); */
        partyRepository.save(party);
        
        identityDocument.setPartyId(party.getId());
/*         audData.setAudChannel(AccessChannel.API);
        audData.setAudCreatedBy(userId);
        identityDocument.setAuditData(audData); */
        identityDocumentRepository.save(identityDocument);
    }

    @PostMapping("/closeParty")
    @Transactional
    public void closeParty(@RequestBody IndividualPartyDTO reqObject, @RequestHeader("userId") String userId) {
        logger.debug("++++++ inmethod ++++++ " + "closeParty");

        Party inpParty = (Party) reqObject.getParty();
        Optional<Party> persistedParty = partyRepository.findById(inpParty.getId());
        Party partyToUpdate;
        if(persistedParty.isPresent()) {
            partyToUpdate = persistedParty.get();
            partyToUpdate.setClosingDate((Instant.now().atOffset(ZoneOffset.UTC).toLocalDate()));
            partyToUpdate.setStatus(Enums.PartyStatus.INACTIVE);
            partyRepository.save(partyToUpdate);
        }
        else {
            // error
        }
    }

    @GetMapping("/parties")
    public List<Party> getParties() {
        logger.info("++++++ inmethod ++++++ " + "getParties");
        List<Party> listParties = new ArrayList<Party>();
        listParties = partyRepository.findAll();
        return listParties;
    }

    @GetMapping("/party/{name}")
    public List<Party> getParty(@PathVariable String name) {
        logger.info("++++++ inmethod ++++++ " + "getParty for " + name);
        List<Party> listParties = new ArrayList<Party>();
        listParties = partyRepository.getParty(name);
        return listParties;
    }
    

}
