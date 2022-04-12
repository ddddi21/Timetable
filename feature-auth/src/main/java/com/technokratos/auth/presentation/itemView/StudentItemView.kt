package com.technokratos.auth.presentation.itemView

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.feature_auth.databinding.ItemStudentChooseBinding
import com.technokratos.auth.presentation.model.StudentItemModel
import com.technokratos.common.base.adapter.Fillable

class StudentItemView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attributes, defStyleAttr), Fillable<StudentItemModel> {

    private val binding by lazy {
        ItemStudentChooseBinding.bind(this)
    }

    override fun fill(model: StudentItemModel) = with(binding) {
        radioButton.text = model.title
        setOnClickListener {
            model.onItemClicked?.invoke()
        }
    }
}
