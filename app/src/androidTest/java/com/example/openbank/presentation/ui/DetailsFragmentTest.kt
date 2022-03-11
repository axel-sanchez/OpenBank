package com.example.openbank.presentation.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.openbank.R
import com.example.openbank.data.model.CharacterDTO
import com.example.openbank.data.model.Thumbnail
import com.example.openbank.helpers.Constants.ID_CHARACTER
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class DetailsFragmentTest{
    private val character = CharacterDTO(
        1, "", "",
        "Iron Man",
        "", Thumbnail("", "")
    )

    @Test
    fun should_show_product_title_and_description() {
        val bundle = bundleOf(ID_CHARACTER to character.id)
        val scenario = launchFragmentInContainer<DetailsFragment>(
            fragmentArgs = bundle
        )

        scenario.withFragment {
            this.updateView(character)
        }

        Espresso.onView(withId(R.id.tvName)).check(ViewAssertions.matches(withText(character.name)))
    }
}