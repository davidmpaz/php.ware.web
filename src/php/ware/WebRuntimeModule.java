/*
 * generated by Xtext
 */
package php.ware;

import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.scoping.IScopeProvider;

import php.ware.scoping.WebScopeProvider;
import php.ware.valueconverter.WebValueConverterService;

/**
 * Use this class to register components to be used at runtime / without the
 * Equinox extension registry.
 */
public class WebRuntimeModule extends php.ware.AbstractWebRuntimeModule {

    @Override
    public Class<? extends IScopeProvider> bindIScopeProvider() {
        return WebScopeProvider.class;
    }

    @Override
    public Class<? extends IValueConverterService> bindIValueConverterService() {
        return WebValueConverterService.class;
    }
}
