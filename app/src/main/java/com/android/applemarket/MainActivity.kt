package com.android.applemarket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataList = mutableListOf<SaleItem>()
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataList

        val adapter = ItemAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutMangger = LinearLayoutManager(this)

        adapter.itemClick = object : ItemAdapter.ItmeClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(Constants.ITEM_INDEX, position);
                intent.putExtra(Constants.ITEM_OBJECT, dataList[position]);
                activityResultLauncher.launch(intent)
            }
        }

        adapter.itemLongClick = object : ItemAdapter.ItemLongClick {
            override fun onLongClick(view: View, position: Int) {
                val ad = AlertDialog.Builder(this@MainActivity)
                ad.setIcon(R.drawable.img)
                ad.setTitle("상품 삭제")
                ad.setMessage("상품을 정말로 삭제하시겠습니까?")
                ad.setPositiveButton("확인") {dialog, _ ->
                    dataList.removeAt(position)
                    adapter.notifyItemRemoved(position)
                }
                ad.setNegativeButton("취소"){dialog, _ ->
                    dialog.dismiss()
                }
                ad.show()
            }
        }

        binding.ivNoti.setOnClickListener{
            notification()
        }

        val fadeIn = AlphaAnimation(Of, If).apply { duration = 500 }
        val fadeOut = AlphaAnimation(If, Of).apply { duration = 500 }
        var isTop = true
    }

    override fun onBackPressed() {
        val ad = AlertDialog.Builder(this)
        ad.setIcon(R.drawable.img)
        ad.setTitle("종료")
        ad.setMessage("종료하시겠습니까?")

        ad.setPositiveButton("확인") {dialog, _ ->
            finish()
        }
        ad.setNegativeButton("취소") {dialog, _ ->
            dialog.dismiss()
        }
        ad.show()
    }

    fun notification(){
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelId="one-channel"
            val channelName="My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "My Channel One Description"
                setShowBadge(true)
                val
            }
        }
    }
}