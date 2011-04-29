package php.ware.valueconverter;

import org.eclipse.xtext.common.services.DefaultTerminalConverters;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.impl.QualifiedNameValueConverter;

import com.google.inject.Inject;

public class WebValueConverterService extends DefaultTerminalConverters {

	@Inject
	private QualifiedNameValueConverter qualifiedNameValueConverter;

	@ValueConverter(rule = "QualifiedName")
	public IValueConverter<String> QualifiedName() {
		return qualifiedNameValueConverter;
	}

	@ValueConverter(rule = "QualifiedNameWithWildCard")
	public IValueConverter<String> QualifiedNameWithWildCard() {
		return qualifiedNameValueConverter;
	}
}
