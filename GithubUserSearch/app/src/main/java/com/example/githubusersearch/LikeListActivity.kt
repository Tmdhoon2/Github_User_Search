package com.example.githubusersearch

import android.annotation.SuppressLint
import android.media.Image
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusersearch.Recyclerview.LikeAdapter
import com.example.githubusersearch.databinding.ActivityLikeListBinding

@SuppressLint("StaticFieldLeak")
class LikeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLikeListBinding
    private lateinit var likelist: List<LikeEntity>
    private lateinit var db: LikeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLikeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = LikeDatabase.getInstace(this)!!

        val url = intent.getStringExtra("Url")
        val name = intent.getStringExtra("Name")

        Toast.makeText(applicationContext, url + "", Toast.LENGTH_SHORT).show()

        val like = LikeEntity(null, url, name)
        addlike(like)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        getLikes()

    }

    fun addlike(like: LikeEntity){

        val insertTask = object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                db.likeDAO().insert(like)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getLikes()
            }
        }
        insertTask.execute()
    }

    private fun getLikes() {
        val getTask = (object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                likelist = db.likeDAO().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecycerView(likelist)
            }
        }).execute()
    }

    private fun setRecycerView(likelist: List<LikeEntity>) {
        binding.recyclerview.adapter = LikeAdapter(this, likelist)
    }
}