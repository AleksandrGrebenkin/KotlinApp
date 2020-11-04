package com.github.aleksandrgrebenkin.kotlinapp.view.activity

import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.github.aleksandrgrebenkin.kotlinapp.R
import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note
import com.github.aleksandrgrebenkin.kotlinapp.view.adapter.NotesAdapter
import com.github.aleksandrgrebenkin.kotlinapp.view.viewstate.MainViewState
import com.github.aleksandrgrebenkin.kotlinapp.viewmodel.MainViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.StandAloneContext.stopKoin

class MainActivityTest {

    private val viewModel: MainViewModel = mockk(relaxed = true)
    private val viewStateLiveData = MutableLiveData<MainViewState>()

    private val testNotes = listOf(
            Note("1", "title1", "text1"),
            Note("2", "title2", "text2"),
            Note("3", "title3", "text3")
    )

    @Before
    fun setup() {
        loadKoinModules(
                listOf(
                        module {
                            viewModel { viewModel }
                        }
                )
        )

        every { viewModel.getViewState() } returns viewStateLiveData
        ActivityScenario.launch(MainActivity::class.java)
        viewStateLiveData.postValue(MainViewState(notes = testNotes))
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun check_data_is_displayed() {
        onView(withId(R.id.rv_notes)).perform(
                RecyclerViewActions.scrollToPosition<NotesAdapter.NoteViewHolder>(1)
        )
        onView(withText(testNotes[1].body)).check(matches(isDisplayed()))
    }

}