package com.fpmislata.banco.presentacion;

/**
 *
 * @author Samuel Lao
 */
public interface JsonTransformer {
    String objectToJson(Object o);
    <T> T jsonToObject(String json, Class <T> clazz);
}
