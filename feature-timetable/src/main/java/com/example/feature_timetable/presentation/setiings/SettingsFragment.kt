package com.example.feature_timetable.presentation.setiings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.feature_timetable.databinding.SettingsFragmentBinding
import com.example.feature_timetable.di.TimetableFeatureComponent
import com.example.feature_timetable.di.TimetableFeatureKey
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

class SettingsFragment: BaseFragment<SettingsViewModel>() {

    private lateinit var viewBinding: SettingsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = SettingsFragmentBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<TimetableFeatureComponent>(this, TimetableFeatureKey::class.java)
            .settingsComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
//        TODO("Not yet implemented")
    }

    override fun subscribe(viewModel: SettingsViewModel) {
//        TODO("Not yet implemented")
    }
}