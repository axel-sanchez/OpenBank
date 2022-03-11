package com.example.openbank.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.openbank.R
import com.example.openbank.data.model.CharacterDTO
import com.example.openbank.databinding.FragmentCharacterBinding
import com.example.openbank.domain.usecase.GetAllCharactersUseCase
import com.example.openbank.helpers.Constants
import com.example.openbank.helpers.Constants.ID_CHARACTER
import com.example.openbank.helpers.Either
import com.example.openbank.helpers.hide
import com.example.openbank.helpers.show
import com.example.openbank.presentation.adapters.CharacterAdapter
import com.example.openbank.presentation.viewmodels.CharacterViewModel
import org.koin.android.ext.android.inject

/**
 * @author Axel Sanchez
 */
class CharacterFragment : Fragment() {

    private val getAllCharactersUseCase: GetAllCharactersUseCase by inject()
    private val viewModel: CharacterViewModel by viewModels(
        factoryProducer = { CharacterViewModel.CharacterViewModelFactory(getAllCharactersUseCase) }
    )

    private var fragmentCharacterBinding: FragmentCharacterBinding? = null
    private val binding get() = fragmentCharacterBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentCharacterBinding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentCharacterBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver(view)
    }

    private fun setUpObserver(view: View) {

        viewModel.getCharactersLiveData().observe(viewLifecycleOwner, { response ->
            updateView(response)
        })
    }

    private fun updateView(response: Either<Constants.ApiError, List<CharacterDTO?>>?) {
        with(binding) {
            response?.fold(
                left = {
                    emptyState.show()
                    errorText.text = getString(R.string.error_api_products)
                    list.hide()
                }, right = {
                    if ((response as Either.Right).r.isEmpty()) {
                        list.hide()
                        errorText.text = getString(R.string.there_is_not_products)
                        emptyState.show()
                    } else {
                        list.show()
                        setAdapter(response.r)
                    }
                }
            )
            progress.hide()
        }
    }

    private fun setAdapter(characters: List<CharacterDTO?>) {
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = CharacterAdapter(characters) {itemClick(it)}
        }
    }

    private fun itemClick(character: CharacterDTO?){
        val bundle = bundleOf(
            ID_CHARACTER to character?.id
        )
        findNavController().navigate(R.id.action_characterFragment_to_detailsFragment, bundle)
    }
}