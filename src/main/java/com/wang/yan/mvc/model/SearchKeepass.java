package com.wang.yan.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by ywang on 19/12/15.
 */
public class SearchKeepass {

    private String searchField;

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }
}
