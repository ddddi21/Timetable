package com.technokratos.common.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.technokratos.common.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onStart

private const val DEFAULT_DEBOUNCE_DELAY = 300L

fun Activity.showShortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.showShortToast(@StringRes msg: Int) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

@SuppressLint("NewApi")
fun Activity.setBarColorBackground(colorId: Int) {
    window.statusBarColor = ContextCompat.getColor(this, colorId)
}

fun <T> MutableLiveData<T>.setValueIfNew(newValue: T) {
    if (this.value != newValue) value = newValue
}

fun <T> MutableLiveData<T>.postValueIfNew(newValue: T) {
    if (this.value != newValue) postValue(newValue)
}

fun View.changeVisibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

@ExperimentalCoroutinesApi
fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow<CharSequence?> {
        val watcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                trySend(s)
            }
        }
        addTextChangedListener(watcher)
        awaitClose {
            removeTextChangedListener(watcher)
        }
    }
        .onStart { emit(text) }
        .debounce(DEFAULT_DEBOUNCE_DELAY)
}

fun RecyclerView.setDivider(@DrawableRes drawableRes: Int) {
    val divider = DividerItemDecoration(
        this.context,
        DividerItemDecoration.VERTICAL
    )
    val drawable = ContextCompat.getDrawable(
        this.context,
        drawableRes
    )
    drawable?.let {
        divider.setDrawable(it)
        addItemDecoration(divider)
    }
}

fun <T : RecyclerView> T.removeItemDecorations() {
    while (itemDecorationCount > 0) {
        removeItemDecorationAt(0)
    }
}

fun ChipGroup.setChip(text: String) {
    val chip = Chip(context).apply {
        isCheckable = false
        chipStrokeColor = ContextCompat.getColorStateList(context, R.color.black)
        chipStrokeWidth = resources.getDimension(R.dimen.chip_stroke_width)
        setText(text)
        setTextAppearanceResource(R.style.ChipTextStyle)
        chipBackgroundColor = ContextCompat.getColorStateList(context, R.color.white)
    }
    this.addView(chip)
    val parameter = chip.layoutParams as ChipGroup.LayoutParams
    val marginValue = resources.getDimension(R.dimen.dimen_4dp).toInt()
    parameter.setMargins(marginValue, marginValue, marginValue, marginValue)
    chip.layoutParams = parameter
}
