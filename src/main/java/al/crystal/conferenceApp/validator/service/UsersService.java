package al.crystal.conferenceApp.validator.service;

import al.crystal.conferenceApp.dto.UserDto;
import al.crystal.conferenceApp.mapper.UserMapper;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.model.Participant;
import al.crystal.conferenceApp.model.User;
import al.crystal.conferenceApp.repository.OrganiserRepository;
import al.crystal.conferenceApp.repository.ParticipantRepository;
import al.crystal.conferenceApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrganiserRepository organiserRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    public String saveUser(User user) {

        if (user instanceof Organiser) {
            Organiser organiser = new Organiser();
            organiser.setFirstName(user.getFirstName());
            organiser.setLastName(user.getLastName());
            organiser.setEmail(user.getEmail());
            organiser.setPassword(user.getPassword());
            organiser.setCompanyName(((Organiser) user).getCompanyName());
            organiser.setBiography(((Organiser) user).getBiography());
            organiser.setLinkedinUrl(((Organiser) user).getLinkedinUrl());
            organiser.setTweeterUrl(((Organiser) user).getTweeterUrl());
            organiser.setFacebookUrl(((Organiser) user).getFacebookUrl());
            organiser.setInstagramUrl(((Organiser) user).getInstagramUrl());
            this.organiserRepository.save(organiser);
        } else if (user instanceof Participant) {

            Participant participant = new Participant();
            participant.setFirstName(user.getFirstName());
            participant.setLastName(user.getLastName());
            participant.setEmail(user.getEmail());
            participant.setPassword(user.getPassword());
            participant.setParticipantNumber(((Participant) user).getParticipantNumber());
            participant.setParticipantSessionList(((Participant) user).getParticipantSessionList());
            participant.setSpeakerRatings(((Participant) user).getSpeakerRatings());
            this.participantRepository.save(participant);
        }
        return "Saved";
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public List<Participant> getAllParticipants() {
        return this.participantRepository.findAll();
    }

    public List<Organiser> getAllOrganisers() {
        return this.organiserRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return this.userRepository.findByEmailAndPassword(email, password);
    }

    public List<User> deleteUser(Long id) {
        this.userRepository.deleteById(id);
        return this.userRepository.findAll();
    }

    public List<User> updateUser(User user) {
        if (user instanceof Organiser) {
            Organiser existingOrganiser = this.organiserRepository.findById(user.getId()).orElse(null);
            if (existingOrganiser != null) {
                existingOrganiser.setFirstName(user.getFirstName());
                existingOrganiser.setLastName(user.getLastName());
                existingOrganiser.setEmail(user.getEmail());
                existingOrganiser.setPassword(user.getPassword());
                existingOrganiser.setCompanyName(((Organiser) user).getCompanyName());
                existingOrganiser.setBiography(((Organiser) user).getBiography());
                existingOrganiser.setLinkedinUrl(((Organiser) user).getLinkedinUrl());
                existingOrganiser.setTweeterUrl(((Organiser) user).getTweeterUrl());
                existingOrganiser.setFacebookUrl(((Organiser) user).getFacebookUrl());
                existingOrganiser.setInstagramUrl(((Organiser) user).getInstagramUrl());
                this.organiserRepository.save(existingOrganiser);
            }
        } else if (user instanceof Participant) {
            Participant existingParticipant = this.participantRepository.findById(user.getId()).orElse(null);
            if (existingParticipant != null) {
                existingParticipant.setFirstName(user.getFirstName());
                existingParticipant.setLastName(user.getLastName());
                existingParticipant.setEmail(user.getEmail());
                existingParticipant.setPassword(user.getPassword());
                existingParticipant.setParticipantNumber(((Participant) user).getParticipantNumber());
                existingParticipant.setParticipantSessionList(((Participant) user).getParticipantSessionList());
                existingParticipant.setSpeakerRatings(((Participant) user).getSpeakerRatings());
                this.participantRepository.save(existingParticipant);
            }
        }
        return this.getAll();
    }

    public boolean createUser(User newUser) {
        try {
            userRepository.saveAndFlush(newUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public UserDto loginUser(String email, String password) {
        User byEmailAndPassword = userRepository.findByEmailAndPassword(email, password);
        if (byEmailAndPassword != null)
            return UserMapper.toDto(byEmailAndPassword);
        else
            return null;
    }

}