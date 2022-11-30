package al.crystal.conferenceApp.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = Organiser.class, name = "organiser"),
                @JsonSubTypes.Type(value = Participant.class, name = "participant"),
        })
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
        ,defaultImpl = Participant.class
)
@Entity
//@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
@Data
@AllArgsConstructor
public abstract class User {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name ="UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false,columnDefinition = "BINARY(16)", unique = true)
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Type userType;
    protected User(){}

}




//package al.crystal.conferenceApp.model;
//
//        import com.fasterxml.jackson.annotation.*;
//        import lombok.AllArgsConstructor;
//        import lombok.Data;
//
//        import javax.persistence.*;
//        import java.util.UUID;
//
//@JsonSubTypes(
//        {
//                @JsonSubTypes.Type(value = Organiser.class, name = "organiser"),
//                @JsonSubTypes.Type(value = Participant.class, name = "participant"),
//        })
//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "type"
//        ,defaultImpl = Participant.class
//)
//
////@JsonRootName(value = "data")
//@Entity(name = "user")
////@Table(name = "user")
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "type",
//        discriminatorType = DiscriminatorType.STRING)
//@Data
//@AllArgsConstructor
//public abstract class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @JsonProperty("id")mys
//    private UUID id;
//    //    @JsonProperty("firstName")
//    private String firstName;
//    //    @JsonProperty("lastName")
//    private String lastName;
//    //    @JsonProperty("email")
//    private String email;
//    //    @JsonProperty("password")
//    private String password;
//    //    @JsonProperty("userType")
//    private Type userType;
//    protected User(){}
//}
//
