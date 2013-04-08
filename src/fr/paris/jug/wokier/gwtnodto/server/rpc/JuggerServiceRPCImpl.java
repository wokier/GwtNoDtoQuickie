package fr.paris.jug.wokier.gwtnodto.server.rpc;

import java.util.Date;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.paris.jug.wokier.gwtnodto.client.rpc.JuggerServiceRPC;
import fr.paris.jug.wokier.gwtnodto.server.dao.JuggerDao;
import fr.paris.jug.wokier.gwtnodto.shared.model.Jugger;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class JuggerServiceRPCImpl extends RemoteServiceServlet implements JuggerServiceRPC {

    JuggerDao juggerDao = new JuggerDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	if (juggerDao.list().isEmpty()) {
	    juggerDao.add(new Jugger("François Wauquier", new Date(1983 - 1900, 7 - 1, 3), "wokier@gmail.com", "wokier"));
	}
    }

    @Override
    public Jugger add(Jugger jugger) {
	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	Set<ConstraintViolation<Jugger>> violations = validator.validate(jugger);
	if (violations.isEmpty()) {
	    return juggerDao.add(jugger);
	} else {
	    throw new IllegalArgumentException("Invalid values in " + jugger + ":" + violations);
	}
    }

    @Override
    public void delete(Long juggerId) {
	juggerDao.delete(juggerId);
    }

    public java.util.List<Jugger> list() {
	return juggerDao.list();
    }

    public void update(Jugger jugger) {
	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	Set<ConstraintViolation<Jugger>> violations = validator.validate(jugger);
	if (violations.isEmpty()) {
	    juggerDao.update(jugger);
	} else {
	    throw new IllegalArgumentException("Invalid values in " + jugger + ":" + violations);
	}
    }
}
