/**
 * @file ValidatingConfig.java
 * @author Nuke Williams
 * @brief Validator Config
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Nuke Williams
 *
 */

package org.thunderpay.xmlloader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class ValidatingConfig<Context> {

    protected Context root;

    public abstract ValidationErrors validate(Context root, ValidationErrors errors);

    public void initialize(final Context root) {
        this.root = root;
    }

    public Context getRoot() {
        return root;
    }

    protected void validateCollection(final Context context, final ValidationErrors errors, final ValidatingConfig<Context>[] configs) {
        for (final ValidatingConfig<Context> config : configs) {
            config.validate(context, errors);
        }
    }
}