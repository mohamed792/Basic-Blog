package com.example.basicblog.exceptions;

public class WrongMethodArgsExceptions extends RuntimeException{

private String resource;
private String field;
private String vaue;

    public WrongMethodArgsExceptions( String resource, String field, String vaue) {
        super(String.format("%s with value %s not allowed for %s",field,vaue,resource));
        this.resource = resource;
        this.field = field;
        this.vaue = vaue;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getVaue() {
        return vaue;
    }

    public void setVaue(String vaue) {
        this.vaue = vaue;
    }
}
