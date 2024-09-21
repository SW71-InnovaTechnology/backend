package pe.upc.trackmyroute.profiles.domain.model.aggregates;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.upc.trackmyroute.profiles.domain.model.commands.CreateProfileCommand;
import pe.upc.trackmyroute.profiles.domain.model.valueobjects.*;
import pe.upc.trackmyroute.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;

@Entity
@Getter
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
