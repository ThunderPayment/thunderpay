/**
 * @file Bully.java
 * @author Krisna Pranav
 * @brief Bully
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.config;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Bully {
    private static final List<Coercible<?>> TYPE_COERCIBLES;

    private static final List<Coercible<?>> DEFAULT_COERCIBLES;

    static {
        final List<Coercible<?>> typeCoercibles = new ArrayList<Coercible<?>>();
        final List<Coercible<?>> defaultCoercibles = new ArrayList<Coercible<?>>();

        typeCoercibles.add(DefaultCoercibles.BOOLEAN_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.BYTE_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.SHORT_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.INTEGER_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.LONG_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.FLOAT_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.DOUBLE_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.STRING_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.URI_COERCIBLE);

        defaultCoercibles.add(DefaultCoercibles.CASE_INSENSITIVE_ENUM_COERCIBLE);
        defaultCoercibles.add(DefaultCoercibles.VALUE_OF_COERCIBLE);
        defaultCoercibles.add(DefaultCoercibles.STRING_CTOR_COERCIBLE);
        defaultCoercibles.add(DefaultCoercibles.OBJECT_CTOR_COERCIBLE);

        TYPE_COERCIBLES = Collections.unmodifiableList(typeCoercibles);
        DEFAULT_COERCIBLES = Collections.unmodifiableList(defaultCoercibles);
    }

    private final Map<Class<?>, Coercer<?>> mappings = new HashMap<Class<?>, Coercer<?>>();


    private final List<Coercible<?>> coercibles = new ArrayList<Coercible<?>>();

    public Bully() {
        coercibles.addAll(TYPE_COERCIBLES);
    }

    
}
