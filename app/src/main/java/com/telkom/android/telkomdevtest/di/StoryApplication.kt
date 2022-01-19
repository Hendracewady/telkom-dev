package com.telkom.android.telkomdevtest.di

import android.app.Application

/**
 * Created by Hendra Cewady on 19/01/2022.
 */
class StoryApplication : Application() {
    private lateinit var storyComponent: StoryComponent
    override fun onCreate() {
        super.onCreate()

        storyComponent = DaggerStoryComponent.builder()
            .storyModule(StoryModule())
            .build()
    }

    fun getStoryComponent(): StoryComponent{
        return storyComponent
    }
}