package com.blankj.lib.base

import android.content.Intent
import android.net.Uri
import android.support.annotation.StringRes
import android.support.v4.widget.DrawerLayout
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.StringUtils
import kotlinx.android.synthetic.main.activity_drawer.*

/**
 * ```
 * author: blankj
 * blog  : http://blankj.com
 * time  : 2018/11/16
 * desc  : base about drawer activity
 * ```
 */
abstract class BaseDrawerActivity : BaseActivity() {

    protected lateinit var mDrawerRootLayout: DrawerLayout
    protected lateinit var mDrawerContainerView: FrameLayout

    override fun setRootLayout(layoutId: Int) {
        super.setRootLayout(R.layout.activity_drawer)
        if (layoutId > 0) {
            LayoutInflater.from(this).inflate(layoutId, drawerContainerView)
        }
        drawerNavView.setNavigationItemSelectedListener l@{ item ->
            when (item.itemId) {
                R.id.action_git_hub -> return@l goWeb(R.string.github)
                R.id.action_blog -> return@l goWeb(R.string.blog)
            }
            false
        }
        mDrawerRootLayout = drawerRootLayout
        mDrawerContainerView = drawerContainerView
    }

    private fun goWeb(@StringRes id: Int): Boolean {
        return ActivityUtils.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(StringUtils.getString(id)))
        )
    }
}
