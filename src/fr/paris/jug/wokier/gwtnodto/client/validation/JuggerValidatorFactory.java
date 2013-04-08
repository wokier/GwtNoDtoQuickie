package fr.paris.jug.wokier.gwtnodto.client.validation;

import javax.validation.Validator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

import fr.paris.jug.wokier.gwtnodto.shared.model.Jugger;

public final class JuggerValidatorFactory extends AbstractGwtValidatorFactory {

    /**
     * Validator marker for the Validation Sample project. Only the classes listed in the {@link GwtValidation} annotation can be validated.
     */
    @GwtValidation(value = Jugger.class)
    public interface GwtValidator extends Validator {
    }

    @Override
    public AbstractGwtValidator createValidator() {
	return GWT.create(GwtValidator.class);
    }
}