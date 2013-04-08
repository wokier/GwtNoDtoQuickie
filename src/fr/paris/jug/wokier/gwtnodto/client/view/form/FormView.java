package fr.paris.jug.wokier.gwtnodto.client.view.form;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.validation.client.impl.Validation;

import fr.paris.jug.wokier.gwtnodto.client.mvc.Presenter;
import fr.paris.jug.wokier.gwtnodto.shared.model.Jugger;

public class FormView extends Composite {

    private static FormViewUiBinder uiBinder = GWT.create(FormViewUiBinder.class);

    interface FormViewUiBinder extends UiBinder<Widget, FormView> {
    }

    @UiField
    TextBox fullNameTextBox;
    @UiField
    Label fullNameErrorLabel;
    @UiField
    TextBox emailTextBox;
    @UiField
    Label emailErrorLabel;
    @UiField
    DateBox birthdayDateBox;
    @UiField
    Label birthdayErrorLabel;
    @UiField
    TextBox twitterTextBox;

    Map<String, Label> errors;

    private final Presenter presenter;

    public FormView(Presenter presenter) {
	this.presenter = presenter;
	initWidget(uiBinder.createAndBindUi(this));
	errors = new HashMap<String, Label>() {
	    {
		put("fullName", fullNameErrorLabel);
		put("email", emailErrorLabel);
		put("birthday", birthdayErrorLabel);
	    }
	};
    }

    public void clearForm() {
	fullNameTextBox.setText("");
	emailTextBox.setText("");
	birthdayDateBox.setValue(new Date());
	twitterTextBox.setText("");
	clearErrors();
    }

    private void clearErrors() {
	for (Label errorLabel : errors.values()) {
	    errorLabel.setText("");
	}
    }

    @UiHandler("validateButton")
    public void onValidateButtonClick(ClickEvent clickEvent) {
	Jugger jugger = getFormValue();
	if (isValidForm(jugger)) {
	    presenter.doAddJugger(jugger);
	}
    }

    private boolean isValidForm(Jugger jugger) {
	clearErrors();
	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	Set<ConstraintViolation<Jugger>> violations = validator.validate(jugger);
	if (violations.isEmpty()) {
	    return true;
	}
	for (ConstraintViolation<Jugger> constraintViolation : violations) {
	    String path = constraintViolation.getPropertyPath().iterator().next().getName();
	    errors.get(path).setText(constraintViolation.getMessage());
	}
	return false;
    }

    private Jugger getFormValue() {
	return new Jugger(fullNameTextBox.getText(), birthdayDateBox.getValue(), emailTextBox.getText(), twitterTextBox.getText());
    }

    @UiHandler("cancelButton")
    public void onCancelButtonClick(ClickEvent clickEvent) {
	presenter.closeDialog();
    }
}
