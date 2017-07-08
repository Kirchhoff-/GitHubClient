package com.kirchhoff.example.githubclient.presenters

import com.kirchhoff.example.githubclient.Constants
import com.kirchhoff.example.githubclient.Injection
import com.kirchhoff.example.githubclient.ui.auth.AuthContract
import com.kirchhoff.example.githubclient.ui.auth.AuthPresenter
import junit.framework.Assert.assertNotNull
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
class AuthPresenterTest {

    @Mock
    private lateinit var view: AuthContract.View

    private lateinit var presenter: AuthPresenter

    @Before
    @Throws(Exception::class)
    fun init() {
        MockitoAnnotations.initMocks(this)

        presenter = AuthPresenter(Injection.provideGitHubRepository(),
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
    fun testEmptyLogin() {
        presenter.auth("", Constants.PASSWORD)
        verify(view).showLoginError()

        verifyNoMoreInteractions(view)
    }

    @Test
    @Throws(Exception::class)
    fun testEmptyPassword() {
        presenter.auth(Constants.LOGIN, "")
        verify(view).showPasswordError()

        verifyNoMoreInteractions(view)
    }

    @Test
    @Throws(Exception::class)
    fun testEmptyLoginAndPassword() {
        presenter.auth("", "")
        verify(view).showLoginError()
        verify(view).showPasswordError()

        verifyNoMoreInteractions(view)
    }

    @Test
    @Throws(Exception::class)
    fun testAuthError() {
        presenter.auth(Constants.WRONG_LOGIN, Constants.WRONG_PASSWORD)

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showError()

        verifyNoMoreInteractions(view)
    }

    @Test
    @Throws(Exception::class)
    fun testSuccessAuth() {
        presenter.auth(Constants.LOGIN, Constants.PASSWORD)

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).openRepositoryScreen()

        verifyNoMoreInteractions(view)
    }
}
