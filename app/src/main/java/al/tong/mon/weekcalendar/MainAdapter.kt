package al.tong.mon.weekcalendar

import al.tong.mon.weekcalendar.databinding.ItemDayBinding
import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

class MainAdapter() :
    RecyclerView.Adapter<MainAdapter.PatientDetailViewHolder>() {


    constructor(activity: Activity, weeks: Vector<Week>, today: String, startDay: String) : this() {
        this.activity = activity
        this.weeks = weeks
        this.today = today
        this.startDay = startDay
        this.iToday = today.replace("-","").toInt()
        this.iStartDay = startDay.replace("-", "").toInt()
        this.context = activity.applicationContext
    }


    private lateinit var activity : Activity
    private lateinit var weeks : Vector<Week>
    private lateinit var context : Context

    private lateinit var today : String
    private lateinit var startDay: String
    private var iToday: Int = 0
    private var iStartDay: Int = 0

    private lateinit var txtViews: Array<TextView>

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PatientDetailViewHolder {
        val binding = ItemDayBinding.inflate(LayoutInflater.from(context), viewGroup, false)

        return PatientDetailViewHolder(binding)
    }


    override fun onBindViewHolder(viewHolder: PatientDetailViewHolder, position: Int) {
        val binding = viewHolder.binding
        val week = weeks[position]
        txtViews = arrayOf(
            binding.sunTxtView,
            binding.monTxtView,
            binding.tueTxtView,
            binding.wedTxtView,
            binding.thuTxtView,
            binding.friTxtView,
            binding.sunTxtView
        )

        binding.sunTxtView.text = week.day1.day
        binding.monTxtView.text = week.day2.day
        binding.tueTxtView.text = week.day3.day
        binding.wedTxtView.text = week.day4.day
        binding.thuTxtView.text = week.day5.day
        binding.friTxtView.text = week.day6.day
        binding.satTxtView.text = week.day7.day

        //오늘 날짜를 크기를 크게
        for(i in 0 until txtViews.size) {
            val today = todays(position, i)
            if (today == this.today) {
                txtViews[i].scaleX = 1.75f
                txtViews[i].scaleY = 1.75f
            } else {
                txtViews[i].scaleX = 1.0f
                txtViews[i].scaleY = 1.0f
            }

        }
    }

    override fun getItemCount(): Int {
        return weeks.size
    }

    class PatientDetailViewHolder : RecyclerView.ViewHolder {

        var binding: ItemDayBinding

        constructor(binding: ItemDayBinding) : super(binding.root) {
            this.binding = binding
        }

    }

    fun todays(pos1 : Int, pos2 : Int) : String {
        val week = weeks[pos1]
        var day: Day? = null
        when(pos2) {
            0 -> {
                day = week.day1
            }
            1 -> {
                day = week.day2
            }
            2 -> {
                day = week.day3
            }
            3 -> {
                day = week.day4
            }
            4 -> {
                day = week.day5
            }
            5 -> {
                day = week.day6
            }
            6 -> {
                day = week.day7
            }
        }
        return "${day!!.year}-${day.month}-${day.day}"
    }

}