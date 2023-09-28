package com.example.myapp.ui;


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.adapter.RecAdapter
import com.example.myapp.dataclass.Resource
import com.example.myapp.dataclass.University
import com.example.myapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    // var gameState:String? = null
    lateinit var recyclerAdapter : RecAdapter
    private var dialog: CustomProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val viewmodel = ViewModelProvider(this)[MainViewModel::class.java]
        dialog = CustomProgressBar(this)


        val recyclerView = findViewById<RecyclerView>(R.id.rec_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter= RecAdapter(this)
        recyclerView.adapter = recyclerAdapter

        viewmodel.getUniversityData()
      //  val intent = Intent(this, UniversityRefreshService::class.java)
       // startService(intent)

        viewmodel.universityLiveData.observe(this){ result->
            when(result){
                is Resource.Failure ->{
                    Toast.makeText(this, result.errorBody.toString(),Toast.LENGTH_SHORT).show()


                }
                is Resource.Success ->{
                   // Toast.makeText(this, "data retrieved",Toast.LENGTH_SHORT).show()
                    dialog!!.dismissDialog()
                    recyclerAdapter.setData(result.value as MutableList<University>)
                    recyclerAdapter.notifyDataSetChanged()
                }

                is Resource.Loading ->{
                   // Toast.makeText(this, "Loading",Toast.LENGTH_SHORT).show()
                    dialog!!.showDialog()
                }
            }

        }







    }
}