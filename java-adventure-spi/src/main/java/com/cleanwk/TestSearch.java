package com.cleanwk;

import java.sql.Driver;
import java.util.ServiceLoader;

public class TestSearch {
    public static void main(String[] args) {
        ServiceLoader<Search> loader = ServiceLoader.load(Search.class);
        for (Search search : loader) {
            search.searchDoc("haha");
        }
    }

}
