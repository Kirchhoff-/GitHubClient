package com.kirchhoff.example.githubclient.presenters;

import com.kirchhoff.example.githubclient.Constants;
import com.kirchhoff.example.githubclient.Injection;
import com.kirchhoff.example.githubclient.ui.auth.AuthContract;
import com.kirchhoff.example.githubclient.ui.auth.AuthPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertNotNull;

/**
 * @author Kirchhoff-
 */

@RunWith(JUnit4.class)
public class AuthPresenterTest {

    @Mock
    AuthContract.View view;

    private AuthPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new AuthPresenter(Injection.provideGitHubRepository(), view,
                Injection.provideSchedulerProvider(), Injection.provideKeyValueStorage());
    }


    @Test
    public void testCreated() throws Exception {
        assertNotNull(presenter);
        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testEmptyLogin() throws Exception {
        presenter.auth("", Constants.PASSWORD);
        Mockito.verify(view).showLoginError();

        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testEmptyPassword() throws Exception {
        presenter.auth(Constants.LOGIN, "");
        Mockito.verify(view).showPasswordError();

        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testEmptyLoginAndPassword() throws Exception {
        presenter.auth("", "");
        Mockito.verify(view).showLoginError();
        Mockito.verify(view).showPasswordError();

        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testAuthError() throws Exception {
        presenter.auth(Constants.WRONG_LOGIN, Constants.WRONG_PASSWORD);

        Mockito.verify(view).showLoading();
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).showAuthError();

        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testSuccessAuth() throws Exception {
        presenter.auth(Constants.LOGIN, Constants.PASSWORD);

        Mockito.verify(view).showLoading();
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).openRepositoryScreen();

        Mockito.verifyNoMoreInteractions(view);
    }
}
