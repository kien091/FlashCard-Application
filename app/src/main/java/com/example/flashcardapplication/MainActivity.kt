@file:Suppress("DEPRECATION")

package com.example.flashcardapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.flashcardapplication.fragments.HomePageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.flashcardapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var viewPage: ViewPager? = null
    private var bottomNavigation : BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewPage = binding.viewPager
        bottomNavigation = binding.bottomNavigation

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomePageFragment(), "Trang chủ")


        viewPage?.adapter = adapter


        bottomNavigation?.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.i_trang_chu -> viewPage?.currentItem = 0
                R.id.i_loi_giai -> viewPage?.currentItem = 1
                R.id.i_add -> {
                    val bottomSheetFragment = CustomBottomSheetDialogFragment()
                    bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
                }
                R.id.i_thu_vien -> viewPage?.currentItem = 3
                R.id.i_ho_so -> viewPage?.currentItem = 4
            }
            true
        }
    }

}

class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }
    override fun getCount(): Int {
        return fragmentList.size
    }
    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitleList[position]
    }
}

class CustomBottomSheetDialogFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog, container, false)


    }
}
