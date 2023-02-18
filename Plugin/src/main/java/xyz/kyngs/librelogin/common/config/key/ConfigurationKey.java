package xyz.kyngs.librelogin.common.config.key;

import xyz.kyngs.librelogin.common.config.ConfigurateHelper;

import java.util.function.BiFunction;

public record ConfigurationKey<T>(String key, T defaultValue, String comment,
                                  BiFunction<ConfigurateHelper, String, T> getter) {

    public static ConfigurationKey<?> getComment(String key, String comment) {
        return new ConfigurationKey<>(key, null, comment, (x, y) -> {
            throw new UnsupportedOperationException();
        });
    }

    public T compute(ConfigurateHelper configurateHelper) {
        var value = getter.apply(configurateHelper, key());

        return value == null ? defaultValue : value;
    }
}
