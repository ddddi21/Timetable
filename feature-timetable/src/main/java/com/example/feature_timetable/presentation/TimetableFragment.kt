package com.example.feature_timetable.presentation

import com.example.feature_timetable.di.TimetableFeatureComponent
import com.example.feature_timetable.di.TimetableFeatureKey
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

class TimetableFragment : BaseFragment<TimetableViewModel>() {

    override fun inject() {
        FeatureUtils.getFeature<TimetableFeatureComponent>(this, TimetableFeatureKey::class.java)
            .splashComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }

    override fun subscribe(viewModel: TimetableViewModel) {
        TODO("Not yet implemented")
    }
}