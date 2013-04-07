package fr.paris.jug.wokier.gwtnodto.server.rpc;

import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

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
	return juggerDao.add(jugger);
    }

    @Override
    public void delete(Long juggerId) {
	juggerDao.delete(juggerId);
    }

    public java.util.List<Jugger> list() {
	return juggerDao.list();
    }

    public void update(Jugger jugger) {
	juggerDao.update(jugger);
    }
}
