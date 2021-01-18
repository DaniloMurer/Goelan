package com.danilojakob.goelan.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Class for generating components programatically
 */
class ComponentFactory {

    /**
     * Method for generating a new [TextView]
     * @param text Text to show in the [TextView]
     * @param context Context of the new [TextView]
     */
    fun createTextView(text: String, context: Context): TextView {
        val newTextView = TextView(context)
        newTextView.text = text
        return newTextView
    }

    /**
     * Method for generating a new [LinearLayout]
     * @param orientation Orientation of the [LinearLayout]
     * @param context Context of the new [LinearLayout]
     */
    fun createLinearLayout(orientation: Int, context: Context): LinearLayout {
        val newLinearLayout = LinearLayout(context)
        newLinearLayout.orientation = orientation
        return newLinearLayout
    }

    /**
     * Method for generating a new [Button]
     * @param text Text of the new [Button]
     * @param context Context of the new [Button]
     */
    fun createButton(text: String, context: Context): Button {
        val newButton = Button(context)
        newButton.text = text
        // TODO Implement deleting current LinearLayout
        return newButton
    }

    fun createImageView(color: Int, context: Context): ImageView {
        val shapeDrawable = ShapeDrawable()
        val bitmap: Bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888)
        val canvas: Canvas = Canvas(bitmap)
        val imageView = ImageView(context)

        shapeDrawable.setBounds(100, 100, 600, 400)
        shapeDrawable.paint.color = Color.GRAY
        shapeDrawable.draw(canvas)

        imageView.background = BitmapDrawable(context.resources, bitmap)
        return imageView
    }
}