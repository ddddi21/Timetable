package com.example.feature_timetable.presentation.itemView

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.feature_auth.databinding.ItemStudentChooseBinding
import com.example.feature_timetable.databinding.ItemLessonBinding
import com.example.feature_timetable.presentation.model.LessonItemModel
import com.example.feature_timetable.presentation.model.getSubjectTypeAppearance
import com.technokratos.auth.presentation.model.StudentItemModel
import com.technokratos.common.base.adapter.Fillable

class LessonItemView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attributes, defStyleAttr), Fillable<LessonItemModel> {

    private val binding by lazy {
        ItemLessonBinding.bind(this)
    }

    override fun fill(model: LessonItemModel) = with(binding) {
        startTimeTextView.text = model.startTime
        endTimeTextView.text = model.endTime
        subjectTextView.text = model.subject
        classroomTextView.text = model.classroom
        lessonTypeTextView.setText(model.type.getSubjectTypeAppearance())
//        radioButton.setOnClickListener {
//            model.onItemClicked?.invoke(model)
//        }
    }
}