package com.technokratos.auth.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.feature_auth.R
import com.example.feature_auth.databinding.FragmentStudentSetUpBinding
import com.technokratos.auth.di.AuthFeatureKey
import com.technokratos.auth.di.AuthFeatureComponent
import com.technokratos.auth.presentation.model.ScreenType
import com.technokratos.auth.presentation.model.getScreenTypeAppearance
import com.technokratos.auth.presentation.state.StudentChooseState
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.base.adapter.BaseAdapter
import com.technokratos.common.di.FeatureUtils
import com.technokratos.common.utils.setDivider

class AuthFragment : BaseFragment<AuthViewModel>() {

    private lateinit var viewBinding: FragmentStudentSetUpBinding

    private val universityAdapter = BaseAdapter()
    private val instituteAdapter = BaseAdapter()
    private val groupAdapter = BaseAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentStudentSetUpBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<AuthFeatureComponent>(this, AuthFeatureKey::class.java)
            .authComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
    }

    override fun subscribe(viewModel: AuthViewModel) {
        viewModel.studentChooseState.observe(viewLifecycleOwner, ::inflateViews)
    }

    private fun inflateViews(state: StudentChooseState) = with(viewBinding) {
        titleTextView.setText(state.screenType.getScreenTypeAppearance().remarkStatusTitle)
        nextButton.isVisible = state.isItemChosen
        toolbar.navigationIcon?.setVisible(state.isNeedToShowBackArrow, false)

        initAdapter(state)
    }

    private fun initAdapter(state: StudentChooseState) {
        when (state.screenType) {
            ScreenType.UNIVERSITY -> viewBinding.recyclerView.adapter = universityAdapter
            ScreenType.INSTITUTE -> viewBinding.recyclerView.adapter = instituteAdapter
            ScreenType.GROUP -> viewBinding.recyclerView.adapter = groupAdapter
        }
        viewBinding.recyclerView.setDivider(R.drawable.list_divider)
    }

    private fun initRadioGroups(state: StudentChooseState) {
    }
}