package com.danilojakob.goelan.util.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.danilojakob.goelan.data.GameData
import com.danilojakob.goelan.util.event.Observer

/**
 * UI and logic for the actuator MiniGame
 */
class ActuatorGameView(private val context: Context, orientation: Int): AbstractView(context, orientation), Observer {

    init {
        GameData.actuatorPressedEvent.addObserver(this)
    }

    override fun buildLayout() {
        val bitmap: Bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888)
        val canvas: Canvas = Canvas(bitmap)
        val imageView = ImageView(this.context)

        val shapeDrawable = ShapeDrawable()
        shapeDrawable.setBounds(100, 100, 600, 400)
        shapeDrawable.paint.color = Color.GRAY
        shapeDrawable.draw(canvas)

        imageView.background = BitmapDrawable(this.context.resources, bitmap)

        this.addChildToLayout(imageView)
    }

    override fun update() {
        TODO("Not yet implemented")
    }
}