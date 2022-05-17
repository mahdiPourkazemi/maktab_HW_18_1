package com.pourkazemi.mahdi.maktab_hw_15_v2.listadapter

import android.util.Log
import androidx.core.view.isGone
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

open class MyItemTouchHelper : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT)
    }

    /**
     * Called when ItemTouchHelper wants to move the dragged item from its old position to
     * the new position.
     *
     *
     * If this method returns true, ItemTouchHelper assumes `viewHolder` has been moved
     * to the adapter position of `target` ViewHolder
     * ([ ViewHolder#getAdapterPosition()][ViewHolder.getAdapterPosition]).
     *
     *
     * If you don't support drag & drop, this method will never be called.
     *
     * @param recyclerView The RecyclerView to which ItemTouchHelper is attached to.
     * @param viewHolder   The ViewHolder which is being dragged by the user.
     * @param target       The ViewHolder over which the currently active item is being
     * dragged.
     * @return True if the `viewHolder` has been moved to the adapter position of
     * `target`.
     * @see .onMoved
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    /**
     * Called when a ViewHolder is swiped by the user.
     *
     *
     * If you are returning relative directions ([.START] , [.END]) from the
     * [.getMovementFlags] method, this method
     * will also use relative directions. Otherwise, it will use absolute directions.
     *
     *
     * If you don't support swiping, this method will never be called.
     *
     *
     * ItemTouchHelper will keep a reference to the View until it is detached from
     * RecyclerView.
     * As soon as it is detached, ItemTouchHelper will call
     * [.clearView].
     *
     * @param viewHolder The ViewHolder which has been swiped by the user.
     * @param direction  The direction to which the ViewHolder is swiped. It is one of
     * [.UP], [.DOWN],
     * [.LEFT] or [.RIGHT]. If your
     * [.getMovementFlags]
     * method
     * returned relative flags instead of [.LEFT] / [.RIGHT];
     * `direction` will be relative as well. ([.START] or [                   ][.END]).
     */
    override fun onSwiped(
        viewHolder: RecyclerView.ViewHolder,
        direction: Int
    ) {
        Log.d("test", "this is from swiped")
    }

/*    *//**
     * Called by the ItemTouchHelper when the user interaction with an element is over and it
     * also completed its animation.
     *
     *
     * This is a good place to clear all changes on the View that was done in
     * [.onSelectedChanged],
     * [.onChildDraw] or
     * [.onChildDrawOver].
     *
     * @param recyclerView The RecyclerView which is controlled by the ItemTouchHelper.
     * @param viewHolder   The View that was interacted by the user.
     *//*
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        recyclerView.removeViewAt(viewHolder.adapterPosition)
        recyclerView.adapter?.notifyItemRemoved(viewHolder.adapterPosition)
    }*/
}