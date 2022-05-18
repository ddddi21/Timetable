package com.example.feature_timetable.presentation.timetable

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.feature_timetable.R
import com.example.feature_timetable.databinding.TimetableFragmentBinding
import com.example.feature_timetable.di.TimetableFeatureComponent
import com.example.feature_timetable.di.TimetableFeatureKey
import com.technokratos.auth.presentation.state.StudentChooseState
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.base.adapter.BaseAdapter
import com.technokratos.common.di.FeatureUtils
import com.technokratos.common.utils.setDivider

private const val STUDENT_CHOOSE_STATE_KEY = "choose_state_key"

class TimetableFragment : BaseFragment<TimetableViewModel>() {

    private lateinit var viewBinding: TimetableFragmentBinding

    private val mondayListAdapter = BaseAdapter()
    private val tuesdayListAdapter = BaseAdapter()
    private val wednesdayListAdapter = BaseAdapter()
    private val thursdayListAdapter = BaseAdapter()
    private val fridayListAdapter = BaseAdapter()
    private val saturdayListAdapter = BaseAdapter()

    private var currentWeek = false

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
        initAdapters()
        initLayoutTransition()
        setUpToolbar()
    }

    override fun subscribe(viewModel: TimetableViewModel) {
        viewModel.mondayListFlow.observe(viewLifecycleOwner) {
            mondayListAdapter.update(it)
        }
        viewModel.tuesdayListFlow.observe(viewLifecycleOwner) {
            tuesdayListAdapter.update(it)
        }
        viewModel.wednesdayListFlow.observe(viewLifecycleOwner) {
            wednesdayListAdapter.update(it)
        }
        viewModel.thursdayListFlow.observe(viewLifecycleOwner) {
            thursdayListAdapter.update(it)
        }
        viewModel.fridayListFlow.observe(viewLifecycleOwner) {
            fridayListAdapter.update(it)
        }
        viewModel.saturdayListFlow.observe(viewLifecycleOwner) {
            saturdayListAdapter.update(it)
        }

        viewModel.currentWeek.observe(viewLifecycleOwner) {
            viewBinding.toolbar.setTitle( setWeekTitle(it))
            currentWeek = it
        }
    }

    private fun setUpToolbar() {
        setHasOptionsMenu(true)

        viewBinding.toolbar.menu.findItem(R.id.currentWeek).setOnMenuItemClickListener {
            viewModel.loadTimetableByCurrentWeek()
            viewBinding.toolbar.setTitle(setWeekTitle(currentWeek))
            false
        }

        viewBinding.toolbar.menu.findItem(R.id.notCurrentWeek).setOnMenuItemClickListener {
            viewModel.loadTimetableByNotCurrentWeek()
            viewBinding.toolbar.setTitle(setWeekTitle(!currentWeek))
            false
        }
    }

    private fun initAdapters() {
        viewBinding.mondayRecyclerView.adapter = mondayListAdapter
        viewBinding.mondayRecyclerView.setDivider(R.drawable.list_divider)

        viewBinding.tuesdayRecyclerView.adapter = tuesdayListAdapter
        viewBinding.tuesdayRecyclerView.setDivider(R.drawable.list_divider)

        viewBinding.wednesdayRecyclerView.adapter = wednesdayListAdapter
        viewBinding.wednesdayRecyclerView.setDivider(R.drawable.list_divider)

        viewBinding.thursdayRecyclerView.adapter = thursdayListAdapter
        viewBinding.thursdayRecyclerView.setDivider(R.drawable.list_divider)

        viewBinding.fridayRecyclerView.adapter = fridayListAdapter
        viewBinding.fridayRecyclerView.setDivider(R.drawable.list_divider)

        viewBinding.saturdayRecyclerView.adapter = saturdayListAdapter
        viewBinding.saturdayRecyclerView.setDivider(R.drawable.list_divider)
    }

    private val studentChooseState by lazy {
        arguments?.getSerializable(STUDENT_CHOOSE_STATE_KEY) as StudentChooseState
    }


    private fun initLayoutTransition() {
        val lt = LayoutTransition()
        lt.disableTransitionType(LayoutTransition.DISAPPEARING)
        viewBinding.contentLayout.layoutTransition = lt
    }


    private fun setWeekTitle(isEvenWeek: Boolean): Int {
        return when (isEvenWeek) {
            true -> R.string.even_week
            false -> R.string.odd_week
        }
    }
}