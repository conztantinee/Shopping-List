package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SimpleAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.db.ItemEntity
import com.example.shoppinglist.db.RoomDBapp
import com.example.shoppinglist.touch.ItemRecyclerTouchCallback
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.dialog.*
import java.util.ArrayList

class HomeActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: RecyclerAdapter
    lateinit var viewModel: MyViewModel
    lateinit var list: List<ItemEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)


        val listRepository = Repository(RoomDBapp.getAppDatabase(this)!!)
        val factory = MyViewModelFactory(listRepository)

        viewModel = ViewModelProvider(this,factory).get(MyViewModel::class.java)
        val r_adapter = RecyclerAdapter(viewModel, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = r_adapter

        viewModel.allItems()?.observe(this, Observer {
            r_adapter.Items = it as ArrayList<ItemEntity>
            list = it
            r_adapter.notifyDataSetChanged()
        })
        val gestures = ItemRecyclerTouchCallback(r_adapter)
        val itemTouchHelper = ItemTouchHelper(gestures)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var selectedView = item.itemId
        when(selectedView){
            R.id.AddItem -> AddDialog(this,object : Dialog{
                override fun onSaveButtonClicked(item: ItemEntity) {
                    viewModel.insert(item)
                }
            }).show()
            R.id.DeleteAll -> deleteAllItems()
        }
        return false
    }

    private fun deleteAllItems(): Boolean{
        for(Item in list)
            viewModel.delete(Item)
        return true
    }
}

//private operator fun <T> MutableLiveData<T>.iterator(): Iterator<ItemEntity> {
//
//}
