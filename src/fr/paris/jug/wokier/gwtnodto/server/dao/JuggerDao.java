package fr.paris.jug.wokier.gwtnodto.server.dao;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.helper.DAOBase;

import fr.paris.jug.wokier.gwtnodto.shared.model.Jugger;

public class JuggerDao extends DAOBase {

    static {
	ObjectifyService.register(Jugger.class);
    }

    public List<Jugger> list() {
	return ofy().query(Jugger.class).list();
    }

    public Jugger add(Jugger jugger) {
	Key<Jugger> key = ofy().put(jugger);
	jugger.id = key.getId();
	return jugger;
    }

    public void update(Jugger jugger) {
	ofy().put(jugger);
    }

    public void delete(Long juggerId) {
	ofy().delete(Jugger.class, juggerId);
    }
}
