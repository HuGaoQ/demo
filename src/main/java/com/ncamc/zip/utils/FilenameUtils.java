package com.ncamc.zip.utils;

import cn.hutool.core.util.StrUtil;

public class FilenameUtils {

    private static final int NOT_FOUND = -1;

    public static String getExtension(final String filename) {
        if (filename == null) {
            return null;
        }
        final int index = indexOfExtension(filename);
        if (index == NOT_FOUND) {
            return StrUtil.EMPTY;
        } else {
            return filename.substring(index + 1);
        }
    }

    public static int indexOfExtension(final String filename) {
        if (filename == null) {
            return NOT_FOUND;
        }
        final int extensionPos = filename.lastIndexOf(StrUtil.DOT);
        final int lastSeparator = indexOfLastSeparator(filename);
        return lastSeparator > extensionPos ? NOT_FOUND : extensionPos;
    }

    public static int indexOfLastSeparator(final String filename) {
        if (filename == null) {
            return NOT_FOUND;
        }
        final int lastUnixPos = filename.lastIndexOf(StrUtil.SLASH);
        final int lastWindowsPos = filename.lastIndexOf(StrUtil.BACKSLASH);
        return Math.max(lastUnixPos, lastWindowsPos);
    }

}
