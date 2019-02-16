package com.mhemdan.popularactors.utils.rx

import com.mhemdan.popularactors.util.rx.SchedulerProvider
import io.reactivex.*
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider(var mTestScheduler: TestScheduler) : SchedulerProvider {
    override fun <T> ioToMainObservableScheduler(): ObservableTransformer<T, T> = ObservableTransformer { upstream ->
        upstream.subscribeOn(mTestScheduler)
            .observeOn(mTestScheduler)
    }

    override fun <T> ioToMainSingleScheduler(): SingleTransformer<T, T> = SingleTransformer { upstream ->
        upstream.subscribeOn(mTestScheduler)
            .observeOn(mTestScheduler)
    }

    override fun ioToMainCompletableScheduler(): CompletableTransformer = CompletableTransformer { upstream ->
        upstream.subscribeOn(mTestScheduler)
            .observeOn(mTestScheduler)
    }

    override fun <T> ioToMainFlowableScheduler(): FlowableTransformer<T, T> = FlowableTransformer { upstream ->
        upstream.subscribeOn(mTestScheduler)
            .observeOn(mTestScheduler)
    }

    override fun <T> ioToMainMaybeScheduler(): MaybeTransformer<T, T> = MaybeTransformer { upstream ->
        upstream.subscribeOn(mTestScheduler)
            .observeOn(mTestScheduler)
    }

}