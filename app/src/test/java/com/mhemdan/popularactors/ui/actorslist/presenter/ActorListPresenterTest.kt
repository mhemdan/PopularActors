package com.mhemdan.popularactors.ui.actorslist.presenter

import com.mhemdan.popularactors.data.model.ActorModel
import com.mhemdan.popularactors.data.model.PopularActorsResponse
import com.mhemdan.popularactors.ui.actorslist.ActorListContract
import com.mhemdan.popularactors.ui.actorslist.interactor.ActorListInteractor
import com.mhemdan.popularactors.ui.actorslist.interactor.ActorListInteractorImpl
import com.mhemdan.popularactors.util.StateManager
import com.mhemdan.popularactors.utils.rx.TestSchedulerProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test


/**
 * Created by m.hemdan on 16,February,2019
 * github : https://github.com/mhemdan
 */
class ActorListPresenterTest {
    val mockView = spyk<ActorListContract.View>()
    val mockActorListResponse = mockk<PopularActorsResponse>()
    val mockActorList = mockk<List<ActorModel>>()
    val mockActorListInteractor = mockk<ActorListInteractorImpl>()
    val stateManager = mockk<StateManager>()


    private lateinit var mockActorsListPresenter: ActorListContract.Presenter<ActorListContract.View, ActorListInteractor>
    private lateinit var mTestScheduler: TestScheduler

    @Before
    fun setup() {
        mTestScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler)

        mockActorsListPresenter = ActorListPresenter(
            mockActorListInteractor,
            testSchedulerProvider,
            stateManager
        )
        mockActorsListPresenter.attachView(mockView)
    }

    @Test
    fun getActorsList_Success() {

        getActorsWithEmptyFlag(false)

        verify { mockView.showLoading() }
        verify { mockView.hideLoading() }
        verify { mockView.insertItems(mockActorList) }
    }

    @Test
    fun getActorsList_Empty() {

        getActorsWithEmptyFlag(true)

        verify { mockView.showLoading() }
        verify { mockView.hideLoading() }
        verify { mockView.showEmptyResults() }
    }

    private fun getActorsWithEmptyFlag(emptyFlag: Boolean){
        every { stateManager.isConnected() } returns true
        every { mockActorListResponse.results } returns mockActorList
        every { mockActorListInteractor.getPopularActors(1) } returns Single.just(mockActorListResponse)
        every { mockActorList.isEmpty() } returns emptyFlag

        mockActorsListPresenter.getPopularActors(1)
        mTestScheduler.triggerActions()
    }
}