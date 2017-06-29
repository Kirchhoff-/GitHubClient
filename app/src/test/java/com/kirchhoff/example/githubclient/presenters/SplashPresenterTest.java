package com.kirchhoff.example.githubclient.presenters;

import com.kirchhoff.example.githubclient.Constants;
import com.kirchhoff.example.githubclient.DataModule;
import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage;
import com.kirchhoff.example.githubclient.ui.splash.SplashContract;
import com.kirchhoff.example.githubclient.ui.splash.SplashPresenter;

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
public class SplashPresenterTest {

    @Mock
    SplashContract.View view;

    KeyValueStorage storage;

    private SplashPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        storage = DataModule.provideKeyValueStorage();

        presenter = new SplashPresenter(storage, view);
    }

    @Test
    public void testCreated() throws Exception {
        assertNotNull(presenter);
        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testEmptyUserName() throws Exception {
        storage.saveUserName(Constants.EMPTY_USER_NAME);

        presenter.subscribe();
        Thread.sleep(2200);
        Mockito.verify(view).openAuthScreen();
        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testExistUserName() throws Exception {
        storage.saveUserName(Constants.USER_NAME);

        presenter.subscribe();
        Thread.sleep(2200);
        Mockito.verify(view).openRepositoriesScreen();
        Mockito.verifyNoMoreInteractions(view);
    }
}
