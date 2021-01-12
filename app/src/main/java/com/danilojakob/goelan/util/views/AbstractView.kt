package com.danilojakob.goelan.util.views

import android.content.Context
import android.view.View
import android.widget.LinearLayout

/**
 * Abstract class for shared properties and methods across al Views
 */
abstract class AbstractView(context: Context, orientation: Int) {

    private val layout: LinearLayout = LinearLayout(context)

    init {
        this.layout.orientation = orientation
    }

    /**
     * Build the layout
     */
    abstract fun buildLayout()

    /**
     * Add a child component to the root layout
     */
    fun addChildToLayout(child: View) {
        this.layout.addView(child)
    }

    /**
     * Return the views constructed layout
     */
    fun getLayout(): LinearLayout {
        return this.layout
    }
}