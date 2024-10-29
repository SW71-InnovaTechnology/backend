package pe.upc.trackmyroute.profiles.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.upc.trackmyroute.profiles.domain.model.commands.CreateProfileCommand;
import pe.upc.trackmyroute.profiles.domain.model.valueobjects.*;
import pe.upc.trackmyroute.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import pe.upc.trackmyroute.iam.domain.model.aggregates.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile extends AuditableAbstractAggregateRoot<Profile> {
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name"))
    PersonName name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email"))
    EmailAddress email;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password"))
    Password password;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "trasnport_company"))
    TransportCompany transportCompany;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_type"))
    UserType userType;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Profile(User user) {
        this.user = user;
        this.name = new PersonName("Nombre", "Apellido"); // Valores predeterminados
        this.email = new EmailAddress("ejemplo@correo.com"); // Valores predeterminados
        this.password = new Password(""); // Cambia esto si es necesario
        this.transportCompany = new TransportCompany(""); // Cambia esto si es necesario
        this.userType = new UserType(""); // Cambia esto si es necesario
    }

    public Profile(CreateProfileCommand command){
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.password = new Password(command.password());
        this.transportCompany = new TransportCompany(command.transportCompany());
        this.userType = new UserType(command.type());
    }

    public String getFullName() {return this.name.getFullName();}
    public String getEmail() {return this.email.getEmailAddress();}
    public String getPassword() {return this.password.password();}
    public String getTransportCompany() {return this.transportCompany.getTransportCompany();}
    public String getUserType() {return this.userType.getType();}
}
