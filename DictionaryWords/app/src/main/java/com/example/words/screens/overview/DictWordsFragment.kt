package com.example.words.screens.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.words.R
import com.example.words.database.WordDatabase
import com.example.words.databinding.FragmentDictWordsBinding
import com.example.words.network.WordApiFilter

class DictWordsFragment : Fragment() {

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    lateinit var viewModel: DictWordsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDictWordsBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val dao = WordDatabase.getInstance(application).wordDao

        val viewModelFactory = DictWordsViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DictWordsViewModel::class.java)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.dictWords.adapter = DictWordsListAdapter()
        val adapter = DictWordsListAdapter()


        viewModel.dictWords.observe(this.viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        binding.dictWords.adapter = adapter

        viewModel.currentFilter.observe(this.viewLifecycleOwner) {
            // A new LiveData object has to be observed because the filter
            // has changed. Remove any observers that may already exist.
            viewModel.dictWords.removeObservers(this.viewLifecycleOwner)
            viewModel.dictWords.observe(this.viewLifecycleOwner) { list ->
                adapter.submitList(list)
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_word_menu) {
            findNavController()
                .navigate(DictWordsFragmentDirections.actionDictWordsFragmentToSearchWordFragment())
        } else {
            viewModel.changeFilter(
                when (item.itemId) {
                    R.id.show_active_menu -> WordApiFilter.SHOW_ACTIVE
                    R.id.show_inactive_menu -> WordApiFilter.SHOW_INACTIVE
                    else -> WordApiFilter.SHOW_ALL
                }
            )
        }
        return true
    }
}