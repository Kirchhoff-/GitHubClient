package com.kirchhoff.example.githubclient.presenters

import com.kirchhoff.example.githubclient.Constants
import com.kirchhoff.example.githubclient.Injection
import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage
import com.kirchhoff.example.githubclient.ui.splash.SplashContract
import com.kirchhoff.example.githubclient.ui.splash.SplashPresenter
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations

/**
 * @author Kirchhoff-
 */
class SplashPresenterTest {

    @Mock
    private lateinit var view: SplashContract.View

    private lateinit var presenter: SplashPresenter

    private lateinit var storage: KeyValueStorage

    @Before
    @Throws(Exception::class)
    fun init() {
        MockitoAnnotations.initMocks(this)

        storage = Injection.provideKeyValueStorage()

        presenter = SplashPresenter(storage, view)
    }

    @Test
    @Throws(Exception::class)
    fun testCreated() {
        assertNotNull(presenter)
        verifyNoMoreInteractions(view)
    }

    @Test
    @Throws(Exception::class)
    fun testEmptyUserName() {
        storage.saveUserName(Constants.EMPTY_USER_NAME)

        presenter.subscribe()
        Thread.sleep(2200)

        verify(view).openAuthScreen()
        verifyNoMoreInteractions(view)
    }

    @Test
    @Throws
    fun testExistUserName() {
        storage.saveUserName(Constants.USER_NAME)

        presenter.subscribe()
        Thread.sleep(2200)

        verify(view).openRepositoriesScreen()
        verifyNoMoreInteractions(view)
    }
}