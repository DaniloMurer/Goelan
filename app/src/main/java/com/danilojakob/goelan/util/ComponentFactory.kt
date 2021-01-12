package com.danilojakob.goelan.util

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Class for generating components programatically
 */
class ComponentFactory {

    /**
     * Method for generating a new TextView
     * @param text Text to show in the TextView
     * @param context Context of the new Button
     */
    fun createTextView(text: String, context: Context): TextView {
        val newTextView = TextView(context)
        newTextView.text = text
        return newTextView
    }

    /**
     * Method for generating a new LinearLayout
     * @param orientation Orientation of the LinearLayout
     * @param context Context of the new LinearLayout
     */
    fun createLinearLayout(orientation: Int, context: Context): LinearLayout {
        val newLinearLayout = LinearLayout(context)
        newLinearLayout.orientation = orientation
        return newLinearLayout
    }

    /**
     * Method for generating a new Button
     * @param text Text of the new Button
     * @param context Context of the new Button
     */
    fun createButton(text: String, context: Context): Button {
        val newButton = Button(context)
        newButton.setOnClickListener( {

        });
        newButton.text = text
        return newButton
    }
}