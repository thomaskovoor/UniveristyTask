package com.example.myapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.ui.WebActivity
import com.example.myapp.dataclass.University

class RecAdapter( private val context: Context) : RecyclerView.Adapter<RecAdapter.ViewHolder>() {

    private var universityList:MutableList<University>? = null



    fun setData(universities: MutableList<University>) {
        this.universityList = universities
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return if(universityList == null) 0
        else
            universityList!!.size
    }

    override fun onBindViewHolder(holder: RecAdapter.ViewHolder, position: Int) {
        val university = universityList!![position]
        holder.bind(university)


        holder.universityWebsite.setOnClickListener {

            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("url", holder.universityWebsite.text)
            context.startActivity(intent)


        }

    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val universityName: TextView = itemView.findViewById(R.id.Uname)
        private val universityCountry: TextView = itemView.findViewById(R.id.Ucountry)
       val universityWebsite: TextView = itemView.findViewById(R.id.Ulink)

        fun bind(university: University) {

            universityName.text = university.name
            universityCountry.text = university.country
            universityWebsite.text = university.webPages[0]


        }


    }




}