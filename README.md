# ![ic_star_selected](https://user-images.githubusercontent.com/28524260/144566032-f6ecbe1c-8f37-4865-9850-5c7226448e98.png) QRatingView
A custom view for rating which easy to make and use, but function is excellent
# Effect Picture
![Screenshot_20211203-144523](https://user-images.githubusercontent.com/28524260/144564534-285cc1b2-2f52-4d5d-8cda-72b671959198.jpg)
# Properties
```
    <declare-styleable name="QRatingView">
        <!-- 未选中图片 -->
        <attr name="normalIcon" format="reference"/>
        <!-- 选中图片 -->
        <attr name="selectedIcon" format="reference"/>
        <!-- 自定义评分数量 -->
        <attr name="totalCount" format="integer"/>
        <!-- 选中的个数 -->
        <attr name="selectedCount" format="integer"/>
        <!-- 图案的间隙 -->
        <attr name="gap" format="dimension"/> // Default Size is 5dp
        <!-- 图案的大小 -->
        <attr name="iconSize" format="dimension"/> // Default Size is 25dp
        <!-- 是否可用 不可用时用于仅展示 -->
        <attr name="available" format="boolean"/> // Default value is true
    </declare-styleable>
```
# Usage
## for displaying
You are supposed to set tow properties, available and selectedCount. And available should be set as false.
you can use it in .xml
``` 
    <com.yeqingqing.qratingview.QRatingView
        android:id="@+id/main_qrv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:available="false" // if just for displaying ,there are should be false
        app:iconSize="40dp"
        app:selectedCount="3"
        app:totalCount="5" />
 ```
 also it also will be work in java
 ```
    main_qrv2.available = false
    main_qrv2.selectedCount = 5
    
 ```
## for rating
In this case, we need to set totalCount in .xml and add A selected Lisntener for result
```
    <com.yeqingqing.qratingview.QRatingView
        android:id="@+id/qrv_main3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:iconSize="40dp" 
        app:totalCount="5" />
```
```
     findViewById<QRatingView>(R.id.qrv_main3).setOnSelectedListener(object :
            QRatingView.OnSelectedListener {
            override fun onSelected(selectedCount: Int) {
                when (selectedCount) {
                //todo any reaction for rating
                    1 -> commence1.text = "很差"
                    2 -> commence1.text = "有待改进"
                    3 -> commence1.text = "还行"
                    4 -> commence1.text = "不错"
                    5 -> commence1.text = "非常可以"
                }
            }

        })
```
# About Me
blog : [QCoder](https://blog.csdn.net/yqq577) <br>
email: 526416248@qq.com
