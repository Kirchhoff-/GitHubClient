package com.kirchhoff.example.githubclient.presenters

import com.kirchhoff.example.githubclient.Constants
import com.kirchhoff.example.githubclient.Injection
import com.kirchhoff.example.githubclient.ui.commit.CommitsContract
import com.kirchhoff.example.githubclient.ui.commit.CommitsPresenter
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations

/**
 * @author Kirchhoff-
 */
@RunWith(JUnit4::class)
class CommitsPresenterTest {

    @Mock
    private lateinit var view: CommitsContract.View

    private lateinit var presenter: CommitsPresenter

    @Before
    @Throws(Exception::class)
    fun init() {
        MockitoAnnotations.initMocks(this)

        presenter = CommitsPresenter(Injection.provideGitHubRepository(), view,
                Injection.provideSchedulerProvider())
    }

    @Test
    @Throws(Exception::class)
    fun testCreated() {
        assertNotNull(presenter)

        verifyNoMoreInteractions(view)
    }

    @Test
    @Throws(Exception::class)
    fun testCommitsError() {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.ERROR
        presenter.loadCommits(Constants.REPOSITORY)

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showError()

        verifyNoMoreInteractions(view)
    }

    @Test
    @Throws(Exception::class)
    fun testEmptyCommits() {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.EMPTY
        presenter.loadCommits(Constants.REPOSITORY)

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showEmptyView()

        verifyNoMoreInteractions(view)
    }

    @Test
    @Throws(Exception::class)
    fun testCommitsSuccess() {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.DATA
        presenter.loadCommits(Constants.REPOSITORY)

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showCommits(Constants.emulateCommitsList())

        verifyNoMoreInteractions(view)
    }
}