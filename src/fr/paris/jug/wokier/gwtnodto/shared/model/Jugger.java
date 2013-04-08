package fr.paris.jug.wokier.gwtnodto.shared.model;

import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;

import teh.annotations.ToString;
import teh.annotations.ToStringEquals;
import teh.annotations.ToStringEqualsHashCode;
import teh.fields.TEHFields;
import teh.gwt.shared.GWTEHObject;

import com.google.gwt.core.shared.GWT;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class Jugger extends GWTEHObject {

    @Id
    @ToStringEqualsHashCode
    public Long id;
    @ToStringEquals
    @NotNull
    @NotEmpty
    public String fullName;
    @ToString
    @NotNull
    @NotEmpty
    public String email;
    @Past
    public Date birthday;
    public String twitter;

    public Jugger() {
	super();
    }

    public Jugger(String fullName, Date birthday, String email, String twitter) {
	super();
	this.fullName = fullName;
	this.birthday = birthday;
	this.email = email;
	this.twitter = twitter;
    }

    @Override
    protected TEHFields createTEHFields() {
	return GWT.create(Jugger.class);
    }

}
