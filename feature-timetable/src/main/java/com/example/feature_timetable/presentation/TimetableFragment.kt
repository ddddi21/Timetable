package com.example.feature_timetable.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.feature_auth.databinding.FragmentStudentSetUpBinding
import com.example.feature_timetable.databinding.TimetableFragmentBinding
import com.example.feature_timetable.di.TimetableFeatureComponent
import com.example.feature_timetable.di.TimetableFeatureKey
import com.technokratos.auth.presentation.state.StudentChooseState
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

private const val STUDENT_CHOOSE_STATE_KEY = "choose_state_key"

class TimetableFragment : BaseFragment<TimetableViewModel>() {

    private lateinit var viewBinding: TimetableFragmentBinding

    companion object {
        fun buildArgs(
            studentChooseState: StudentChooseState
        ) = bundleOf(STUDENT_CHOOSE_STATE_KEY to studentChooseState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = TimetableFragmentBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<TimetableFeatureComponent>(this, TimetableFeatureKey::class.java)
            .timetableComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
//        TODO("Not yet implemented")
    }

    override fun subscribe(viewModel: TimetableViewModel) {
//        TODO("Not yet implemented")
    }


    private val studentChooseState by lazy {
        arguments?.getSerializable(STUDENT_CHOOSE_STATE_KEY) as StudentChooseState
    }

}