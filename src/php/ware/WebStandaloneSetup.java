
package php.ware;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class WebStandaloneSetup extends WebStandaloneSetupGenerated{

	public static void doSetup() {
		new WebStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

