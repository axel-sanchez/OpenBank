package com.example.openbank.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.openbank.R
import com.example.openbank.data.model.CharacterDTO
import com.example.openbank.databinding.FragmentDetailsBinding
import com.example.openbank.domain.usecase.GetCharacterUseCase
import com.example.openbank.helpers.Constants.ID_CHARACTER
import com.example.openbank.presentation.viewmodels.DetailsViewModel
import org.koin.android.ext.android.inject

/**
 * @author Axel Sanchez
 */
class DetailsFragment : Fragment() {

    private val getCharacterUseCase: GetCharacterUseCase by inject()
    private val viewModel: DetailsViewModel by viewModels(
        factoryProducer = { DetailsViewModel.DetailsViewModelFactory(getCharacterUseCase) }
    )

    private var fragmentDetailsBinding: FragmentDetailsBinding? = null
    private val binding get() = fragmentDetailsBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentDetailsBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idCharacter = arguments?.getLong(ID_CHARACTER)

        idCharacter?.let {
            viewModel.getCharacter(it)

            viewModel.getCharacterLiveData().observe(viewLifecycleOwner, { character ->
                updateView(character)
            })
        }
    }

    fun updateView(character: CharacterDTO?) {
        with(binding){
            character?.let {
                tvName.text = it.name
                tvDescription.text = it.description

                Glide
                    .with(requireView())
                    .load(it.thumbnail.toString())
                    .centerCrop()
                    .into(ivCoverPage)
            }
        }
    }
}