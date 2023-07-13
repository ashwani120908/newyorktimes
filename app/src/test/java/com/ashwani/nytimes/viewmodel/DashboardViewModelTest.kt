package com.ashwani.nytimes.viewmodel

import com.ashwani.nytimes.network.NetworkResult
import com.ashwani.nytimes.network.PostsApiService
import com.ashwani.nytimes.repository.PostsRepository
import com.ashwani.nytimes.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

class DashboardViewModelTest {

    private val postsRepository: PostsRepository = mock()
    private lateinit var viewModel: DashboardViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        viewModel = DashboardViewModel(postsRepository)
    }

    @Test
    fun viewModel() {
        Assert.assertNotNull(viewModel.fetchPost())
    }

    @Test
    fun getUrlTest() {

        Assert.assertEquals(viewModel.getUrl(Constants.PostType.MOST_EMAILED), "https://api.nytimes.com/svc/mostpopular/v2/emailed/1.json?api-key=${Constants.API_KEY}")
        Assert.assertEquals(viewModel.getUrl(Constants.PostType.MOST_SHARED), "https://api.nytimes.com/svc/mostpopular/v2/shared/1/facebook.json?api-key=${Constants.API_KEY}")
        Assert.assertEquals(viewModel.getUrl(Constants.PostType.MOST_VIEWED), "https://api.nytimes.com/svc/mostpopular/v2/viewed/7.json?api-key=${Constants.API_KEY}")
    }
}