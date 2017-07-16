package com.fortest.elice.fortest.util;

import android.support.annotation.Nullable;

import java.util.Collection;

/**
 * Created by elice.kim on 2017. 7. 15..
 */

public class CollectionUtil {
    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }
}
