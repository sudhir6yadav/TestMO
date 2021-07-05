package com.sd.testmo.data.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.sd.testmo.data.entities.Item
import com.sd.testmo.data.ui.viewmodel.ItemDetailViewModel
import com.sd.testmo.databinding.ItemDetailFragmentBinding
import com.sd.testmo.utils.Resource
import com.sd.testmo.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment : Fragment() {

    private var binding: ItemDetailFragmentBinding by autoCleared()
    private val viewModel: ItemDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        binding = ItemDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.items.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binditems(it.data!!)
                    binding.progressBar.visibility = View.GONE

                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun binditems(items: Item) {
        binding.tvName.text = items.name
        binding.tvLanguage.text = "Language : "+items.language
        binding.tvStar.text = "Star : "+items.stargazers_count.toString()
        binding.tvView.text = "Views : "+items.watchers_count.toString()
        binding.tvShare.text = "Share : "+items.forks_count.toString()
        binding.tvDesc.text = items.description
        Glide.with(binding.root).load(items.owner?.avatar_url).transform(CircleCrop()).into(binding.ivImage)
    }

}
