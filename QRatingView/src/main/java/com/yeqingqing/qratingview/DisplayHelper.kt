package com.yeqingqing.qratingview

import android.content.res.Resources

/**
 *
 * @Author: QCoder
 * @CreateDate: 2021/12/2
 * @Description: 视图有关工具类
 * @Email: 526416248@qq.com
 */
object DisplayHelper {

    private val DENSITY = Resources.getSystem().displayMetrics.density
    fun dpToPx(dpValue: Int): Int {
        return (dpValue * DENSITY + 0.5f).toInt()
    }

    fun pxToDp(pxValue: Float): Int {
        return (pxValue / DENSITY + 0.5f).toInt()
    }
}