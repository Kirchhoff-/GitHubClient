package com.kirchhoff.example.githubclient

import com.kirchhoff.example.githubclient.model.CommitResponse
import com.kirchhoff.example.githubclient.model.Repository
import java.util.*

/**
 * @author Kirchhoff-
 * * Constants that should be used in test to provide communication with fake objects
 */

object Constants {

    val LOGIN = "LOGIN"
    val PASSWORD = "PASSWORD"

    val WRONG_LOGIN = "WRONG_LOGIN"
    val WRONG_PASSWORD = "WRONG_PASSWORD"

    val REPOSITORY = "REPOSITORY"

    val EMPTY_USER_NAME = ""
    val USER_NAME = "USER_NAME"

    //Variable for return different type of data for getRepositoryRequest
    var DATA_TEST_ENUM = DataTestEnum.DATA

    fun emulateRepository(): Repository {
        return Repository(REPOSITORY, "description1", "Java1", 1, 1, 1)
    }

    fun emulateRepositoryList(): List<Repository> {
        val repository1 = Repository("GitHub1", "description1", "Java1", 1, 1, 1)
        val repository2 = Repository("GitHub2", "description2", "Java2", 2, 2, 2)
        val repository3 = Repository("GitHub3", "description3", "Java3", 3, 3, 3)
        val repository4 = Repository("GitHub4", "description4", "Java4", 4, 4, 4)
        val repository5 = Repository("GitHub5", "description5", "Java5", 5, 5, 5)
        val repository6 = Repository("GitHub6", "description6", "Java6", 6, 6, 6)
        val repository7 = Repository("GitHub7", "description7", "Java7", 7, 7, 7)

        val list = ArrayList<Repository>()
        list.add(repository1)
        list.add(repository2)
        list.add(repository3)
        list.add(repository4)
        list.add(repository5)
        list.add(repository6)
        list.add(repository7)

        return list
    }

    fun emulateCommitsList(): List<CommitResponse> {
        val commit1 = CommitResponse()
        val commit2 = CommitResponse()
        val commit3 = CommitResponse()
        val commit4 = CommitResponse()
        val commit5 = CommitResponse()
        val commit6 = CommitResponse()
        val commit7 = CommitResponse()

        val list = ArrayList<CommitResponse>()
        list.add(commit1)
        list.add(commit2)
        list.add(commit3)
        list.add(commit4)
        list.add(commit5)
        list.add(commit6)
        list.add(commit7)

        return list
    }

    enum class DataTestEnum {
        DATA,
        EMPTY,
        ERROR
    }
}
