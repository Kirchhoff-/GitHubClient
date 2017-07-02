package com.kirchhoff.example.githubclient.presenters;

import com.kirchhoff.example.githubclient.Constants;
import com.kirchhoff.example.githubclient.model.Repository;
import com.kirchhoff.example.githubclient.repository.FakeGitHubRepository;
import com.kirchhoff.example.githubclient.repository.FakeKeyValueStorage;
import com.kirchhoff.example.githubclient.repository.GitHubDataSource;
import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage;
import com.kirchhoff.example.githubclient.ui.repositories.RepositoriesContract;
import com.kirchhoff.example.githubclient.ui.repositories.RepositoriesPresenter;
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
public class RepositoriesPresenterTest {

    @Mock
    RepositoriesContract.View view;

    private RepositoriesPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        GitHubDataSource repository = new FakeGitHubRepository();
        KeyValueStorage keyValueStorage = new FakeKeyValueStorage();
        BaseSchedulerProvider provider = new ImmediateSchedulerProvider();

        presenter = new RepositoriesPresenter(repository, keyValueStorage, view, provider);
    }

    @Test
    public void testCreated() throws Exception {
        assertNotNull(presenter);
        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testRepositoriesError() throws Exception {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.ERROR;
        presenter.loadRepositoriesList();

        Mockito.verify(view).showLoading();
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).showError();

        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testRepositoriesEmpty() throws Exception {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.EMPTY;
        presenter.loadRepositoriesList();

        Mockito.verify(view).showLoading();
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).showEmptyView();

        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testRepositoriesSuccess() throws Exception {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.DATA;
        presenter.loadRepositoriesList();

        Mockito.verify(view).showLoading();
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).showRepositories(Constants.emulateRepositoryList());

        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testLogout() throws Exception {

        presenter.logout();

        Mockito.verify(view).moveToAuth();
        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void testOpenRepository() throws Exception {
        Repository repository = new Repository();

        presenter.onRepositoryClick(repository);

        Mockito.verify(view).openRepository(repository);
        Mockito.verifyNoMoreInteractions(view);
    }

}
