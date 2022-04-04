package com.technokratos.common.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter(
    open var entities: List<ViewType> = emptyList()
) : RecyclerView.Adapter<BaseAdapter.ViewTypesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTypesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewTypesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewTypesViewHolder, position: Int) {
        holder.fill(entities[getIndexByPosition(position)])
    }

    override fun getItemCount() = entities.size

    override fun getItemViewType(position: Int) = entities[getIndexByPosition(position)].layoutId

    /*
    * Обновляет список.
    * */
    fun update(list: List<ViewType>) {
        entities = list
        notifyDataSetChanged()
    }

    /*
    * Удаляет элементы из списка.
    * */
    fun removeItems(items: List<ViewType>) {
        if (items.isEmpty()) {
            return
        }

        val mutableList = entities.toMutableList()
        val index = mutableList.indexOf(items.first())

        if (index < 0) {
            return
        }

        mutableList.removeAll(items)
        entities = mutableList

        notifyItemRangeRemoved(index, items.size)
    }

    /*
    * Добавляет элементы в список.
    * */
    fun addItems(newPosition: Int, items: List<ViewType>) {
        val mutableList = entities.toMutableList()

        mutableList.addAll(newPosition, items)
        entities = mutableList

        notifyItemRangeInserted(newPosition, items.size)
    }

    /*
    * Перемещает элементы в списке на позицию newPosition.
    * */
    fun moveItems(newPosition: Int, vararg items: ViewType) {
        removeItems(items.toList())
        addItems(newPosition, items.toList())
    }

    protected open fun getIndexByPosition(position: Int) = position

    open class ViewTypesViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), Fillable<ViewType> {

        override fun fill(model: ViewType) {
            (itemView as? Fillable<ViewType>)?.fill(model)
        }
    }
}