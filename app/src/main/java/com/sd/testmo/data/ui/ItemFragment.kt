package com.sd.testmo.data.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sd.testmo.R
import com.sd.testmo.data.ui.viewmodel.ItemViewModel
import com.sd.testmo.databinding.ItemFragmentBinding
import com.sd.testmo.utils.Resource
import com.sd.testmo.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class ItemFragment : Fragment(),ItemAdapter.CharacterItemListener{

  private var binding: ItemFragmentBinding by autoCleared()
  private val viewModel: ItemViewModel by viewModels()
  private lateinit var adapter: ItemAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = ItemFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecyclerView()
    setupObservers()
  }

  private fun setupRecyclerView() {
    adapter = ItemAdapter(this)
    binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
    binding.charactersRv.adapter = adapter
  }

  private fun setupObservers() {
    viewModel.items.observe(viewLifecycleOwner, Observer {
      when (it.status) {
        Resource.Status.SUCCESS -> {
          binding.progressBar.visibility = View.GONE
          if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
        }
        Resource.Status.ERROR ->
          Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

        Resource.Status.LOADING ->
          binding.progressBar.visibility = View.VISIBLE
      }
    })
  }

  override fun onClickedCharacter(characterId: Int) {
    findNavController().navigate(
      R.id.action_charactersFragment_to_characterDetailFragment,
      bundleOf("id" to characterId)
    )
  }
}