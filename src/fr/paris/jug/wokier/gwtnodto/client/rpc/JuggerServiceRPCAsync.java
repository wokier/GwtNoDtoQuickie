package fr.paris.jug.wokier.gwtnodto.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.paris.jug.wokier.gwtnodto.shared.model.Jugger;

/**
 * The async counterpart
 * 
 */
public interface JuggerServiceRPCAsync {

    void list(AsyncCallback<List<Jugger>> callback);

    void add(Jugger jugger, AsyncCallback<Jugger> callback);

    void update(Jugger jugger, AsyncCallback<Void> callback);

    void delete(Long juggerId, AsyncCallback<Void> callback);
}
