package fr.paris.jug.wokier.gwtnodto.client.rpc;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class SimpleAsyncCallback<T> implements AsyncCallback<T> {

    @Override
    public void onFailure(Throwable caught) {
	Window.alert(caught.getClass() + " " + caught.getMessage());
    }
}
