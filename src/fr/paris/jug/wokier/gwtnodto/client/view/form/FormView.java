package fr.paris.jug.wokier.gwtnodto.client.view.form;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

import fr.paris.jug.wokier.gwtnodto.client.mvc.Presenter;
import fr.paris.jug.wokier.gwtnodto.shared.model.Jugger;

public class FormView extends Composite {

    private static FormViewUiBinder uiBinder = GWT.create(FormViewUiBinder.class);

    interface FormViewUiBinder extends UiBinder<Widget, FormView> {
    }

    @UiField
    TextBox fullNameTextBox;
    @UiField
    TextBox emailTextBox;
    @UiField
    DateBox birthdayDateBox;
    @UiField
    TextBox twitterTextBox;

    private final Presenter presenter;

    public FormView(Presenter presenter) {
	this.presenter = presenter;
	initWidget(uiBinder.createAndBindUi(this));
    }

    public void clearForm() {
	fullNameTextBox.setText("");
	emailTextBox.setText("");
	birthdayDateBox.setValue(new Date());
	twitterTextBox.setText("");
    }

    @UiHandler("validateButton")
    public void onValidateButtonClick(ClickEvent clickEvent) {
	if (isValidForm()) {
	    presenter.doAddJugger(getFormValue());
	}
    }

    private boolean isValidForm() {
	return true;
    }

    private Jugger getFormValue() {
	return new Jugger(fullNameTextBox.getText(), birthdayDateBox.getValue(), emailTextBox.getText(), twitterTextBox.getText());
    }

    @UiHandler("cancelButton")
    public void onCancelButtonClick(ClickEvent clickEvent) {
	presenter.closeDialog();
    }
}
