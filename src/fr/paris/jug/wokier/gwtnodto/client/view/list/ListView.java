package fr.paris.jug.wokier.gwtnodto.client.view.list;

import java.util.List;

import com.github.wokier.gwt.client.GravatarColumn;
import com.google.common.base.Strings;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import fr.paris.jug.wokier.gwtnodto.client.mvc.Presenter;
import fr.paris.jug.wokier.gwtnodto.shared.model.Jugger;

public class ListView extends Composite {

    private static ListViewUiBinder uiBinder = GWT.create(ListViewUiBinder.class);

    interface ListViewUiBinder extends UiBinder<Widget, ListView> {
    }

    @UiField
    CellTable<Jugger> cellTable;
    ListDataProvider<Jugger> dataProvider = new ListDataProvider<Jugger>();

    private final Presenter presenter;

    public ListView(final Presenter presenter) {
	this.presenter = presenter;
	initWidget(uiBinder.createAndBindUi(this));
	cellTable.addColumn(new GravatarColumn<Jugger>() {
	    @Override
	    protected String getEmail(Jugger jugger) {
		return jugger.email;
	    }
	}, "Avatar");
	cellTable.addColumn(new TextColumn<Jugger>() {
	    public String getValue(Jugger jugger) {
		return jugger.fullName;
	    };
	}, "Full Name");
	cellTable.addColumn(new TextColumn<Jugger>() {
	    public String getValue(Jugger jugger) {
		return jugger.email;
	    };
	}, "Email");
	cellTable.addColumn(new TextColumn<Jugger>() {
	    public String getValue(Jugger jugger) {
		if (jugger.birthday != null) {
		    return DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(jugger.birthday);
		}
		return "";
	    };
	}, "Birthday");
	cellTable.addColumn(new TextColumn<Jugger>() {
	    public String getValue(Jugger jugger) {
		if (!Strings.isNullOrEmpty(jugger.twitter)) {
		    return "@" + jugger.twitter;
		}
		return "";
	    };
	}, "Twitter");
	cellTable.addColumn(new Column<Jugger, Jugger>(new ActionCell<Jugger>("✘ Delete", new ActionCell.Delegate<Jugger>() {
	    @Override
	    public void execute(Jugger jugger) {
		presenter.doDeleteJugger(jugger);
	    }
	})) {
	    @Override
	    public Jugger getValue(Jugger jugger) {
		return jugger;
	    }
	}, "Delete");
	dataProvider.addDataDisplay(cellTable);
    }

    public void show(List<Jugger> juggers) {
	dataProvider.getList().clear();
	dataProvider.getList().addAll(juggers);
    }

    @UiHandler("addButton")
    public void onAddButtonClick(ClickEvent clickEvent) {
	presenter.startAdd();
    }

    public void addJugger(Jugger jugger) {
	dataProvider.getList().add(jugger);
    }

    public void removeJugger(Jugger jugger) {
	dataProvider.getList().remove(jugger);
    }
}
