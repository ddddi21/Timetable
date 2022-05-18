package com.technokratos.auth.presentation.auth

import android.animation.LayoutTransition
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import androidx.core.view.updatePadding
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
import com.technokratos.common.utils.doOnNestedScrollChanged
import com.technokratos.common.utils.hideViewWithDownAnimation
import com.technokratos.common.utils.setDivider
import com.technokratos.common.utils.showViewWithUpAnimation

class AuthFragment : BaseFragment<AuthViewModel>() {

    private lateinit var viewBinding: FragmentStudentSetUpBinding

    private var isChosenItem = false

    private val listAdapter = BaseAdapter()

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
        initScrollListener()
        initLayoutTransition()
        initContentLayoutPaddingBottom()
        initAdapter()
        initClickListeners()
    }

    override fun subscribe(viewModel: AuthViewModel) {
        viewModel.studentChooseState.observe(viewLifecycleOwner, ::inflateViews)
        viewModel.listFlow.observe(viewLifecycleOwner) {
            listAdapter.update(it)
        }
    }

    private fun inflateViews(state: StudentChooseState) = with(viewBinding) {
        toolbar.setTitle(state.screenType.getScreenTypeAppearance().remarkStatusTitle)
        Log.e("LLL", state.toString())
        nextButton.isVisible = state.isItemChosen
        isChosenItem = state.isItemChosen
        toolbar.navigationIcon = if (state.isNeedToShowBackArrow) {
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        } else null

        when (state.screenType) {
            ScreenType.ELECTIVES ->
                nextButton.setOnClickListener {
                    viewModel.onActionButtonClicked()
                }
            else ->  nextButton.setOnClickListener {
                viewModel.onNextButtonClick()
            }
        }
    }

    private fun initAdapter() {
        viewBinding.recyclerView.adapter = listAdapter
        viewBinding.recyclerView.setDivider(R.drawable.list_divider)
    }

    private fun initClickListeners() {
        viewBinding.toolbar.setNavigationOnClickListener {
            viewModel.onBackClick()
        }
    }

    private fun initScrollListener() {
        with(viewBinding) {
            nestedScrollView.doOnNestedScrollChanged(
//                predicate = { isChosenItem },
                onScrollUp = { nextButton.showViewWithUpAnimation() },
                onScrollDown = { nextButton.hideViewWithDownAnimation() }
            )
        }
    }

    private fun initLayoutTransition() {
        val lt = LayoutTransition()
        lt.disableTransitionType(LayoutTransition.DISAPPEARING)
        viewBinding.contentLayout.layoutTransition = lt
    }

    private fun initContentLayoutPaddingBottom() {
        val saveButtonSizeWithMargin = with(viewBinding.nextButton) {
            marginBottom + minimumHeight
        }
        with(viewBinding.contentLayout) {
            updatePadding(bottom = paddingBottom + saveButtonSizeWithMargin)
        }
    }
}