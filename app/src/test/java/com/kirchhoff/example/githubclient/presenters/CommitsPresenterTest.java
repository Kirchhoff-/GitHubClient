package com.kirchhoff.example.githubclient.presenters;

import com.kirchhoff.example.githubclient.Constants;
import com.kirchhoff.example.githubclient.repository.FakeGitHubRepository;
import com.kirchhoff.example.githubclient.repository.GitHubDataSource;
import com.kirchhoff.example.githubclient.ui.commit.CommitsContract;
import com.kirchhoff.example.githubclient.ui.commit.CommitsPresenter;
import com.kirchhoff.example.githubclient.utils.schedulers.BaseSchedulerProvider;
import com.kirchhoff.example.githubclient.utils.schedulers.ImmediateSchedulerProvider;

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

        GitHubDataSource repository = new FakeGitHubRepository();
        BaseSchedulerProvider provider = new ImmediateSchedulerProvider();

        presenter = new CommitsPresenter(repository, view, provider);
    }

    @Test
    public void testCreated() throws Exception {
        assertNotNull(presenter);
        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testCommitsError() throws Exception {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.ERROR;
        presenter.loadCommits(Constants.REPOSITORY);

        Mockito.verify(view).showLoading();
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).showError();

        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testCommitsEmpty() throws Exception {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.EMPTY;
        presenter.loadCommits(Constants.REPOSITORY);

        Mockito.verify(view).showLoading();
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).showEmptyView();

        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testCommitsSuccess() throws Exception {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.DATA;
        presenter.loadCommits(Constants.REPOSITORY);

        Mockito.verify(view).showLoading();
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).openCommits(Constants.emulateCommitsList());

        Mockito.verifyNoMoreInteractions(view);
    }
}
