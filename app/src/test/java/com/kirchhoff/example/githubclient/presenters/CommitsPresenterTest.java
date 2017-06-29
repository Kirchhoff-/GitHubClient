package com.kirchhoff.example.githubclient.presenters;

import com.kirchhoff.example.githubclient.Constants;
import com.kirchhoff.example.githubclient.Injection;
import com.kirchhoff.example.githubclient.ui.commit.CommitsContract;
import com.kirchhoff.example.githubclient.ui.commit.CommitsPresenter;

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
public class CommitsPresenterTest {

    @Mock
    CommitsContract.View view;

    private CommitsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new CommitsPresenter(Injection.provideGitHubRepository(),
                view, Injection.provideSchedulerProvider());
    }

    @Test
    public void testCreated() throws Exception {
        assertNotNull(presenter);
        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testRepositoriesError() throws Exception {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.ERROR;
        presenter.loadCommits(Constants.REPOSITORY);

        Mockito.verify(view).showLoading();
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).showError();

        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testRepositoriesEmpty() throws Exception {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.EMPTY;
        presenter.loadCommits(Constants.REPOSITORY);

        Mockito.verify(view).showLoading();
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).showEmptyView();

        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testRepositoriesSuccess() throws Exception {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.DATA;
        presenter.loadCommits(Constants.REPOSITORY);

        Mockito.verify(view).showLoading();
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).openCommits(Constants.emulateCommitsList());

        Mockito.verifyNoMoreInteractions(view);
    }
}
