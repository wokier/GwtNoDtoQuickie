package fr.paris.jug.wokier.gwtnodto.client.mvc;

import java.util.List;

import usefulllogging.api.Log;
import usefulllogging.api.LogFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;

import fr.paris.jug.wokier.gwtnodto.client.rpc.JuggerServiceRPC;
import fr.paris.jug.wokier.gwtnodto.client.rpc.JuggerServiceRPCAsync;
import fr.paris.jug.wokier.gwtnodto.client.rpc.SimpleAsyncCallback;
import fr.paris.jug.wokier.gwtnodto.client.view.form.FormView;
import fr.paris.jug.wokier.gwtnodto.client.view.list.ListView;
import fr.paris.jug.wokier.gwtnodto.shared.model.Jugger;

public class Presenter {

    private static final Log log = LogFactory.getLog(Presenter.class);

    private final JuggerServiceRPCAsync juggerServiceRpc = GWT.create(JuggerServiceRPC.class);

    private ListView listView = new ListView(this);
    private FormView formView = new FormView(this);

    private DialogBox dialogBox = new DialogBox();

    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	panel.setWidget(listView);
	juggerServiceRpc.list(new SimpleAsyncCallback<List<Jugger>>() {
	    @Override
	    public void onSuccess(List<Jugger> result) {
		listView.show(result);
	    }
	});
    }

    public void startAdd() {
	dialogBox.setText("Add a jugger");
	formView.clearForm();
	dialogBox.setWidget(formView);
	dialogBox.center();
    }

    public void doAddJugger(Jugger jugger) {
	juggerServiceRpc.add(jugger, new SimpleAsyncCallback<Jugger>() {
	    @Override
	    public void onSuccess(Jugger result) {
		String text = result + " added";
		toast(text);
		closeDialog();
		listView.addJugger(result);
	    }
	});
    }

    public void closeDialog() {
	dialogBox.hide();
    }

    public void doDeleteJugger(final Jugger jugger) {
	juggerServiceRpc.delete(jugger.id, new SimpleAsyncCallback<Void>() {
	    @Override
	    public void onSuccess(Void result) {
		toast(jugger + " deleted");
		listView.removeJugger(jugger);
	    }

	});
    }

    private void toast(String text) {
	final Element notification = RootPanel.get("notification").getElement();
	notification.setInnerText(text);
	notification.addClassName("fade");
	new Timer() {
	    @Override
	    public void run() {
		notification.removeClassName("fade");
	    }
	}.schedule(2000);
    }

}
