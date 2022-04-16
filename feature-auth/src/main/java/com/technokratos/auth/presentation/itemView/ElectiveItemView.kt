package com.technokratos.auth.presentation.itemView

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.feature_auth.databinding.ItemElectiveBinding
import com.technokratos.auth.presentation.model.ElectiveItemModel
import com.technokratos.common.base.adapter.Fillable

class ElectiveItemView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attributes, defStyleAttr), Fillable<ElectiveItemModel> {

    private val binding by lazy {
        ItemElectiveBinding.bind(this)
    }

    override fun fill(model: ElectiveItemModel) = with(binding) {
        title.text = model.title
        title.setOnClickListener {
            model.onItemClicked?.invoke(model)
        }
    }
}