package ru.otus.json.writer.util;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TypeResolverTest {

    @Test
    void checkWrapperType() {
        assertTrue(TypeResolver.isWrapperType(Integer.class));
        assertTrue(TypeResolver.isWrapperType(Boolean.class));
        assertTrue(TypeResolver.isWrapperType(Character.class));
        assertTrue(TypeResolver.isWrapperType(Byte.class));
        assertTrue(TypeResolver.isWrapperType(Short.class));
        assertTrue(TypeResolver.isWrapperType(Long.class));
        assertTrue(TypeResolver.isWrapperType(Float.class));
        assertTrue(TypeResolver.isWrapperType(Double.class));
    }

    @Test
    void checkNonWrapperType() {
        assertFalse(TypeResolver.isWrapperType(Object.class));
        assertFalse(TypeResolver.isWrapperType(Collection.class));
        assertFalse(TypeResolver.isWrapperType(Map.class));
        assertFalse(TypeResolver.isWrapperType(String.class));
        assertFalse(TypeResolver.isWrapperType(Enum.class));
    }

}