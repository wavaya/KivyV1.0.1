package xyz.wayhua.kivy101.ui.main


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import xyz.wayhua.kivy101.R
import xyz.wayhua.kivy101.databinding.ActivityMainBinding
import xyz.wayhua.kivy101.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    private val viewBinding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private lateinit var mNavHost: NavHostFragment
    private lateinit var mNavController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化数据绑定
        viewBinding.executePendingBindings()
        setupNavController()
        setupAppBar()
        if (::mNavController.isInitialized && ::appBarConfiguration.isInitialized) {
            setupActionBar(mNavController, appBarConfiguration)
        }
        updateTitleToolbar("kivy")
    }


    override fun onBackPressed() {

        if (::mNavHost.isInitialized) {
            val fragmentsSize = mNavHost.childFragmentManager.fragments.size
            if (fragmentsSize >= 1) {
                super.onBackPressed()
            } else {
                findNavController(R.id.navHostFragment).navigateUp(appBarConfiguration)
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (::mNavHost.isInitialized) {
            findNavController(R.id.navHostFragment).navigateUp(appBarConfiguration)
        } else {
            false
        }

    }

    private fun setupNavController() {
        mNavHost = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment? ?: return
        mNavController = mNavHost.navController
    }

    private fun setupAppBar() {
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.postFragment),
            null
        )
    }


    private fun setupActionBar(
        navController: NavController,
        appBarConfiguration: AppBarConfiguration
    ) {
        setupToolbar(viewBinding.toolbar.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }


}