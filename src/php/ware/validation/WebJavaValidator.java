package php.ware.validation;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.xtext.validation.Check;

import php.ware.entity.StructuralFeature;
import php.ware.web.Controller;
import php.ware.web.CrudAction;
import php.ware.web.WebPackage;

public class WebJavaValidator extends AbstractWebJavaValidator {

    public static final String INVALID_NAME = "php.ware.web.InvalidControllerName";

    @Check
    public void checkControllerStartsWithCapital(Controller controller) {
        if (!Character.isUpperCase(controller.getName().charAt(0))) {
            error("Name should start with a capital",
                    WebPackage.CONTROLLER__NAME, INVALID_NAME,
                    controller.getName());
        }
    }

    public static final String DUPLICATED_FEATURES = "php.ware.web.DuplicatedActionFeatures";

    @Check
    public void checkDuplicatedActionFeatures(CrudAction action) {

        ArrayList<String> features = new ArrayList<String>();

        for (Iterator<StructuralFeature> iterator = action
                .getIncludedFeatures().iterator(); iterator.hasNext();) {
            String name = iterator.next().getName();

            if (features.contains(name)) {
                error("Feature " + name + " for action " + action.getName()
                        + " should not be duplicated",
                        WebPackage.CRUD_ACTION__INCLUDED_FEATURES,
                        DUPLICATED_FEATURES, name);
            } else {
                features.add(name);
            }
        }

        features.removeAll(features);

        for (Iterator<StructuralFeature> iterator = action
                .getExcludedFeatures().iterator(); iterator.hasNext();) {
            String name = iterator.next().getName();

            if (features.contains(name)) {
                error("Feature " + name + " for action " + action.getName()
                        + " should not be duplicated",
                        WebPackage.CRUD_ACTION__EXCLUDED_FEATURES,
                        DUPLICATED_FEATURES, name);
            } else {
                features.add(name);
            }
        }

    }

    public static final String INVALID_INCLUDE_EXCLUDE = "php.ware.web.InvalidActionExcludesIncludes";

    @Check
    public void checkContainOnlyIncludesOrExcludes(CrudAction action) {
        if (action.getExcludedFeatures().size() > 0
                && action.getIncludedFeatures().size() > 0) {
            error("You should include or exclude features in action "
                    + action.getName() + ", not both.",
                    WebPackage.CRUD_ACTION__EXCLUDED_FEATURES,
                    INVALID_INCLUDE_EXCLUDE, action.getName());
        }
    }

    public static final String INVALID_RENDER_NAV = "php.ware.web.InvalidActionRenderNav";

    @Check
    public void checkRenderOrForward(CrudAction action) {
        if (action.getTemplate() != null && action.getNavigatedAction() != null) {
            error("You should render or navigate in action " + action.getName()
                    + ", not both.", WebPackage.ABSTRACT_ACTION__NAVIGATE,
                    INVALID_RENDER_NAV, action.getName());
        }
    }

    public static final String EMPTY_CRUD_ACTION = "php.ware.web.EMptyCrudAction";

    @Check
    public void checkEmptyCrudAction(CrudAction action) {

        if (action.getExcludedFeatures().size() == 0
                && action.getIncludedFeatures().size() == 0
                && action.getTemplate() == null
                && action.getNavigatedAction() == null) {
            // TODO export the error and finish implement the quick fix.
            warning("Empty crud action " + action.getName() + " is meaningless",
                    WebPackage.CRUD_ACTION, action.getName());
        }
    }
}
