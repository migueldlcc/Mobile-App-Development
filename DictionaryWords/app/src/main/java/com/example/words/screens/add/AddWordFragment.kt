package com.example.words.screens.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.words.R
import com.example.words.database.WordDatabase
import com.example.words.databinding.FragmentAddWordBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class AddWordFragment : Fragment() {
    private lateinit var viewModel: AddWordViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentAddWordBinding.inflate(inflater)
        val dao = WordDatabase.getInstance(application).wordDao

        binding.lifecycleOwner = this.viewLifecycleOwner

        // Get the word object from the argument passed to it so that it can
        // be passed the view model via the view model factory.
        val word = AddWordFragmentArgs.fromBundle(arguments!!).wordDef

        val viewModelFactory = AddWordViewModelFactory(word, application, dao)

        // The ViewModelProvider uses the factory to create the view model.
        viewModel =ViewModelProvider(this, viewModelFactory).get(AddWordViewModel::class.java)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(AddWordViewModel::class.java)

        // Navigates back to the search fragment
        binding.searchAgain.setOnClickListener {
            findNavController().navigate(R.id.action_addWordFragment_to_searchWordFragment)
        }

        fun addData() {
            lifecycleScope.launch {
                val id = word.id
                if (!dao.wordExists(id)) {
                    binding.addWord.setOnClickListener {
                        Snackbar.make(it, "Word added to the database", Snackbar.LENGTH_SHORT)
                            .show()
                        lifecycleScope.launch {
                            viewModel.insert(word)
                            findNavController().navigate(R.id.action_addWordFragment_to_dictWordsFragment)
                        }
                    }
                } else {
                    binding.addWord.setOnClickListener {
                        Snackbar.make(it, "Word already exists", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }

            }

        }

        addData()

        return binding.root
    }

}