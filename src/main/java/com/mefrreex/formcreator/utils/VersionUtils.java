package com.mefrreex.formcreator.utils;

public class VersionUtils {

    public static VersionEntry fromString(String version) {
        String[] parts = version.split("\\.");
        int major = parts.length > 0 ? Integer.parseInt(parts[0]) : -1;
        int minor = parts.length > 1 ? Integer.parseInt(parts[1]) : -1;
        int patch = parts.length > 2 ? Integer.parseInt(parts[2]) : -1;
        return new VersionEntry(major, minor, patch);
    }

    public static record VersionEntry(int major, int minor, int patch) {

    }
}