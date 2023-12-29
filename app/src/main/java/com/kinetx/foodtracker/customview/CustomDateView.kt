package com.kinetx.foodtracker.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import androidx.core.content.withStyledAttributes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.helpers.HelperFunctions
import java.util.*

class CustomDateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
)    : androidx.appcompat.widget.AppCompatImageButton(context, attrs, defStyleAttr)
{

    private val monthArray = arrayOf(
        "Jan", "Feb",
        "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )

    private val weekArray = arrayOf(
        "Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"
    )
    private var day : String        = "1"
    private var month : String      = "0"
    private var year : String       = "2023"

    private val paintDate = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 50.0f
        typeface = Typeface.create( "", Typeface.BOLD)
        color = Color.BLACK
    }
    private val paintDayOfWeek = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 40.0f
        typeface = Typeface.create( "", Typeface.BOLD)
        color = Color.BLACK
    }

    private var myCalendar: Calendar = Calendar.getInstance()

    init
    {
        myCalendar = HelperFunctions.resetToMidnight(myCalendar)

        context.withStyledAttributes(attrs, R.styleable.CustomDateView)
        {
            day = getString(R.styleable.CustomDateView_day_c).toString()
            month = getString(R.styleable.CustomDateView_month_c).toString()
            year = getString(R.styleable.CustomDateView_year_c).toString()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var pickedDate : Calendar = Calendar.getInstance()
        pickedDate = HelperFunctions.resetToMidnight(pickedDate)

        pickedDate.set(Calendar.YEAR,year.toInt())
        pickedDate.set(Calendar.MONTH,month.toInt())
        pickedDate.set(Calendar.DATE,day.toInt())

        val mm = (pickedDate.timeInMillis-myCalendar.timeInMillis)/(1000*60*60*24)
        Log.i("III",mm.toString())

        val date = when((pickedDate.timeInMillis-myCalendar.timeInMillis)/(1000*60*60*24)) {
            0L -> "Today"
            1L -> "Tomorrow"
            -1L-> "Yesterday"
            else-> "$day-${monthArray[month.toInt()]}-$year"
        }

        paintDate.color = Color.WHITE
        paintDayOfWeek.color = Color.WHITE

        val dayOfWeek = weekArray[pickedDate.get(Calendar.DAY_OF_WEEK)-1]

        canvas?.drawText(date,(width/2).toFloat(),(60).toFloat(),paintDate)
        canvas?.drawText(dayOfWeek,(width/2).toFloat(),(120).toFloat(),paintDayOfWeek)
    }

    fun setTextDay(d: String)
    {
        day = d
        invalidate()
    }

    fun setTextMonth(m: String)
    {
        month = m
        invalidate()
    }

    fun setTextYear(y: String)
    {
        year = y
        invalidate()
    }
}


@BindingAdapter("day_c")
fun setDay(view: CustomDateView, text: LiveData<String>)
{
    view.setTextDay(text.value.toString())
}

@BindingAdapter("month_c")
fun setMonth(view: CustomDateView, text: LiveData<String>)
{
    view.setTextMonth(text.value.toString())
}

@BindingAdapter("year_c")
fun setYear(view: CustomDateView, text: LiveData<String>)
{
    view.setTextYear(text.value.toString())
}
