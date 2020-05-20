package xyz.wayhua.kivy101.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import xyz.wayhua.kivy101.R


open class BaseActivity : AppCompatActivity(), ToolbarListener {

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_up, R.anim.slide_down)
    }

    override fun setupToolbar(toolbar: Toolbar) {

        setSupportActionBar(toolbar)
    }

    override fun updateTitleToolbar(newTitle: String) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = newTitle
            subtitle = ""
        }

    }
}