package com.mhemdan.popularactors.ui.actordetails.presenter

import com.mhemdan.popularactors.data.model.ActorModel
import com.mhemdan.popularactors.data.model.ImageModel
import com.mhemdan.popularactors.data.model.ImagesResponse
import com.mhemdan.popularactors.ui.actordetails.ActorDetailsContract
import com.mhemdan.popularactors.ui.actordetails.interactor.ActorDetailsIneractor
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
class ActorDetailsPresenterTest {
    val mockView = spyk<ActorDetailsContract.View>()
    val mockActorModel = mockk<ActorModel>()
    val mockImagesList = mockk<List<ImageModel>>()
    val mockImagesResponse = mockk<ImagesResponse>()
    val mockActorDetailsInteractor = mockk<ActorDetailsIneractor>()
    val stateManager = mockk<StateManager>()
    private lateinit var mockActorDetailsPresenter: ActorDetailsContract.Presenter<ActorDetailsContract.View, ActorDetailsIneractor>
    private lateinit var mTestScheduler: TestScheduler

    @Before
    fun setup() {
        mTestScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler)

        mockActorDetailsPresenter = ActorDetailsPresenter(
            mockActorDetailsInteractor,
            testSchedulerProvider,
            stateManager
        )
        mockActorDetailsPresenter.attachView(mockView)
    }

    @Test
    fun getActorsList_Success() {

        getActorDetails()

        verify { mockView.showLoading() }
        verify { mockView.hideLoading() }
        verify { mockView.insertActorDetails(mockActorModel) }
        verify { mockView.insertActorImages(mockImagesList) }
    }

    private fun getActorDetails() {
        every { stateManager.isConnected() } returns true
        every { mockImagesResponse.profiles } returns mockImagesList
        every { mockActorDetailsInteractor.getActorDetails(1) } returns Single.just(mockActorModel)
        every { mockActorDetailsInteractor.getActorImages(1) } returns Single.just(mockImagesResponse)

        mockActorDetailsPresenter.getActorDetails(1)
        mTestScheduler.triggerActions()
    }
}