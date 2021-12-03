package com.yeqingqing.qratingview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import java.lang.Exception

/**
 *
 * @Author: QCoder
 * @CreateDate: 2021/12/2
 * @Description: 评分控件 V 0.1 支持自定义个数，图片，图片大小；添加了监听事件
 * @Email: 526416248@qq.com
 */
val DEFAULT_ICON_SIZE = DisplayHelper.dpToPx(25).toFloat()
val DEFAULT_GAP_SIZE = DisplayHelper.dpToPx(5).toFloat()

class QRatingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var moveX = 0f
    private var totalCount = 0
    private val selectedBitmap: Bitmap
    private val normalBitmap: Bitmap
    private var listener: OnSelectedListener? = null
    private var iconSize = DEFAULT_ICON_SIZE
    private var iconGap = DEFAULT_GAP_SIZE

    /** 选中值应小于等于总数值，否则将会抛出异常 */
    var selectedCount = 0
        set(value) {
            if (selectedCount > totalCount) throw Exception("selectedCount shouldn't be bigger than totalCount")
            field = value
        }
    var available = true

    interface OnSelectedListener {
        fun onSelected(selectedCount: Int)
    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.QRatingView)
        totalCount = typedArray.getInt(R.styleable.QRatingView_totalCount, 0)
        iconSize = typedArray.getDimension(R.styleable.QRatingView_iconSize, DEFAULT_ICON_SIZE)
        iconGap = typedArray.getDimension(R.styleable.QRatingView_gap, DEFAULT_GAP_SIZE)
        selectedCount = typedArray.getInt(R.styleable.QRatingView_selectedCount, 0)
        available = typedArray.getBoolean(R.styleable.QRatingView_available, true)
        val selectedIconId = typedArray.getResourceId(
            R.styleable.QRatingView_selectedIcon,
            R.drawable.ic_star_selected
        )
        selectedBitmap =
            zoomImg(BitmapFactory.decodeResource(resources, selectedIconId), iconSize, iconSize)


        val normalIconId =
            typedArray.getResourceId(R.styleable.QRatingView_normalIcon, R.drawable.ic_star_normal)
        normalBitmap =
            zoomImg(BitmapFactory.decodeResource(resources, normalIconId), iconSize, iconSize)

        typedArray.recycle()

    }

    fun setOnSelectedListener(listener: OnSelectedListener) {
        this.listener = listener
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(
                (totalCount * iconSize).toInt() + paddingStart + paddingEnd,
                iconSize.toInt() + paddingTop + paddingBottom
            )
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(
                (totalCount * iconSize).toInt() + paddingStart + paddingEnd,
                heightSize + paddingTop + paddingBottom
            )
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(
                widthSize + paddingStart + paddingEnd,
                iconSize.toInt() + paddingTop + paddingBottom
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (i in 0 until totalCount) {
            //选中
            if (i < selectedCount) {
                canvas.drawBitmap(
                    selectedBitmap,
                    paddingStart + i * iconSize,
                    paddingTop.toFloat(),
                    null
                )
            }
            //未选中
            else {
                canvas.drawBitmap(
                    normalBitmap,
                    paddingStart + i * iconSize,
                    paddingTop.toFloat(),
                    null
                )
            }

        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!available) return true
        when (event.action) {
            ACTION_DOWN, ACTION_MOVE -> {
                moveX = event.x
                if (moveX >= iconSize * totalCount) return true
                selectedCount = (moveX / iconSize).toInt() + 1
                invalidate()
            }
            ACTION_UP -> {
                if (moveX > iconSize * totalCount) return true
                listener?.onSelected(selectedCount)
            }
        }
        return true
    }

    private fun zoomImg(bm: Bitmap, newWidth: Float, newHeight: Float): Bitmap {
        // 获得图片的宽高
        val width = bm.width
        val height = bm.height
        // 计算缩放比例
        val scaleWidth = (newWidth) / width
        val scaleHeight = (newHeight) / height
        // 取得想要缩放的matrix参数
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        // 得到新的图片
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true)
    }

}