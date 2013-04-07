package fr.paris.jug.wokier.gwtnodto.client;

import usefulllogging.api.Log;
import usefulllogging.api.LogFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.paris.jug.wokier.gwtnodto.client.mvc.Presenter;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EntryPoint implements com.google.gwt.core.client.EntryPoint, UncaughtExceptionHandler {

    private static final Log log = LogFactory.getLog(EntryPoint.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
	GWT.setUncaughtExceptionHandler(this);
	log.entering(this, "onModuleLoad");
	RootPanel.get("loading").getElement().setInnerHTML("");
	SimplePanel panel = new SimplePanel();
	RootPanel.get("content").add(panel);
	new Presenter().start(panel, new SimpleEventBus());
    }

    @Override
    public void onUncaughtException(Throwable e) {
	log.error("uncaugth exception", e);
	Window.alert(e.getClass() + " " + e.getMessage());
    }
}
