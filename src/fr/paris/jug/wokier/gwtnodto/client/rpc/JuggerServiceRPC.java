package fr.paris.jug.wokier.gwtnodto.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.paris.jug.wokier.gwtnodto.shared.model.Jugger;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("jugger")
public interface JuggerServiceRPC extends RemoteService {

    List<Jugger> list();

    Jugger add(Jugger jugger);

    void update(Jugger jugger);

    void delete(Long juggerId);
}
