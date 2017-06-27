package com.kirchhoff.example.githubclient;

import com.kirchhoff.example.githubclient.model.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirchhoff-
 *         <p>
 *         Constants that should be used in test to provide communication with fake objects
 */

public class Constants {

    public static final String LOGIN = "LOGIN";
    public static final String PASSWORD = "PASSWORD";

    public static final String WRONG_LOGIN = "WRONG_LOGIN";
    public static final String WRONG_PASSWORD = "WRONG_PASSWORD";

    //Variable for return different type of data for getRepositoryRequest
    public static RepositoryTestEnum REPOSITORY_TEST_ENUM = RepositoryTestEnum.DATA;

    public static List<Repository> emulateRepositoryList() {
        Repository repository1 = new Repository("GitHub1", "description1", "Java1", 1, 1, 1);
        Repository repository2 = new Repository("GitHub2", "description2", "Java2", 2, 2, 2);
        Repository repository3 = new Repository("GitHub3", "description3", "Java3", 3, 3, 3);
        Repository repository4 = new Repository("GitHub4", "description4", "Java4", 4, 4, 4);
        Repository repository5 = new Repository("GitHub5", "description5", "Java5", 5, 5, 5);
        Repository repository6 = new Repository("GitHub6", "description6", "Java6", 6, 6, 6);
        Repository repository7 = new Repository("GitHub7", "description7", "Java7", 7, 7, 7);

        List<Repository> list = new ArrayList<>();
        list.add(repository1);
        list.add(repository2);
        list.add(repository3);
        list.add(repository4);
        list.add(repository5);
        list.add(repository6);
        list.add(repository7);

        return list;
    }

    public enum RepositoryTestEnum {
        DATA,
        EMPTY,
        ERROR
    }
}
