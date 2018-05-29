package com.police.innovation.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class DialogueResponse {
    private String id;
    private String timestamp;
    private String lang;
    private Result result;

    @Getter
    @Setter
    public class Result{
        private String source;
        private String resolvedQuery;
        private HashMap parameters;
    }

}

