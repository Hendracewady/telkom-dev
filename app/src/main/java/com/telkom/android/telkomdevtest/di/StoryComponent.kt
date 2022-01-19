package com.telkom.android.telkomdevtest.di

import com.telkom.android.telkomdevtest.detail.StoryDetailViewModel
import com.telkom.android.telkomdevtest.story.TopStoriesViewModel
import com.telkom.android.telkomdevtest.story.UserItemViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Hendra Cewady on 19/01/2022.
 */

@Singleton
@Component(
    modules = [
        StoryModule::class
    ]
)
interface StoryComponent {

    fun inject(topStoriesViewModel: TopStoriesViewModel)

    fun inject(userItemViewModel: UserItemViewModel)

    fun inject(storyDetailViewModel: StoryDetailViewModel)

}