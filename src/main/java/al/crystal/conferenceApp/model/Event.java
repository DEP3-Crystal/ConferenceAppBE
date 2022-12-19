package al.crystal.conferenceApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "events")
@Builder
public class Event {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
   // @DateTimeFormat(iso = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDay;
//    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDay;
    private String location;
    //Status Open or Restricted
    private boolean participation;
    //New, Ongoing, Ended
    private int capacity;
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private Organiser organiser;


    @ManyToMany
    @JoinTable(name = "participant_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<Participant> participants;
    private String eventImage;
    private String description;

}
