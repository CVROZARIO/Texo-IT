package com.texoit.movies.utils;

import org.junit.platform.commons.util.StringUtils;

public final class Commons {

    public static final boolean parseBoolean(final String d) {
        return StringUtils.isNotBlank(d)
                && (Boolean.parseBoolean(d) || d.trim().equalsIgnoreCase(Consts.BoolStrTrueVariant));
    }

}
