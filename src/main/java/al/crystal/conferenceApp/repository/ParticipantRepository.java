package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

   Participant findByEmail(String email);
}
