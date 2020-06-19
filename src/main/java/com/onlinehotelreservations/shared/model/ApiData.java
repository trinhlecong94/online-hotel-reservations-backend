package com.onlinehotelreservations.shared.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiData<T> {
    private T body;
    @Builder.Default
    private int length = 1;
    @Builder.Default
    private String message = null;


    public ApiData(T body, int length, String message) {
        this.body = body;
        this.length = length;
        this.message = message;
        if (length == 0) {
            if (this.body instanceof List) {
                this.length = ((List) this.body).size();
            }

            if (this.body instanceof Map) {
                this.length = ((Map) this.body).size();
            }
        }

    }

    public ApiData(T body, String message) {
        this.body = body;
        this.message = message;

        if (this.body instanceof List) {
            this.length = ((List) this.body).size();
        }

        if (this.body instanceof Map) {
            this.length = ((Map) this.body).size();
        }
    }

    public ApiData(T body, Integer length) {
        this.body = body;
        this.length = length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public ApiData(T body) {
        this.body = body;
        if (this.body instanceof List) {
            this.length = ((List) this.body).size();
        }

        if (this.body instanceof Map) {
            this.length = ((Map) this.body).size();
        }
    }
}
