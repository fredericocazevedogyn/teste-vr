package br.com.frederico.vr.domain;

public interface ValidationRule<T> {

    void validate(T obj);

}
