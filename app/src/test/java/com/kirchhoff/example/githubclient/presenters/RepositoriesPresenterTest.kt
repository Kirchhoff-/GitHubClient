package com.kirchhoff.example.githubclient.presenters

import com.kirchhoff.example.githubclient.Constants
import com.kirchhoff.example.githubclient.Injection
import com.kirchhoff.example.githubclient.model.Repository
import com.kirchhoff.example.githubclient.ui.repositories.RepositoriesContract
import com.kirchhoff.example.githubclient.ui.repositories.RepositoriesPresenter
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations

/**
 * @author Kirchhoff-
 */
@RunWith(JUnit4::class)
class RepositoriesPresenterTest {

    @Mock
    private lateinit var view: RepositoriesContract.View

    private lateinit var presenter: RepositoriesPresenter

    @Before
    @Throws(Exception::class)
    fun init() {
        MockitoAnnotations.initMocks(this)

        presenter = RepositoriesPresenter(Injection.provideGitHubRepository(),
                view, Injection.provideSchedulerProvider(), Injection.provideKeyValueStorage())
    }


    @Test
    @Throws(Exception::class)
    fun testCreated() {
        assertNotNull(presenter)
        verifyNoMoreInteractions(view)
    }

    @Test
    @Throws(Exception::class)
    fun testRepositoriesError() {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.ERROR
        presenter.loadRepositoriesList()

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showError()

        verifyNoMoreInteractions(view)
    }

    @Test
    @Throws(Exception::class)
    fun testRepositoriesEmpty() {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.EMPTY
        presenter.loadRepositoriesList()

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showEmptyView()

        Mockito.verifyNoMoreInteractions(view)
    }

    @Test
    @Throws
    fun testRepositorySuccess() {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.DATA
        presenter.loadRepositoriesList()

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showRepositories(Constants.emulateRepositoryList())

        verifyNoMoreInteractions(view)
    }

    @Test
    @Throws
    fun testLogout() {
        presenter.logout()

        verify(view).moveToAuth()
        verifyNoMoreInteractions(view)
    }

    @Test
    @Throws
    fun testOpenRepository() {
        val repository = Repository("Name", "Description", "Kotlin", 0, 0, 0)

        presenter.onRepositoryClick(repository)

        verify(view).openRepository(repository)
        verifyNoMoreInteractions(view)
    }
}