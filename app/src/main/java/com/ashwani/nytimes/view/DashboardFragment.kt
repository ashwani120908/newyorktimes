package com.ashwani.nytimes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashwani.nytimes.adapters.SectionedListAdapter
import com.ashwani.nytimes.databinding.FragmentDashboardBinding
import com.ashwani.nytimes.model.ListViewType
import com.ashwani.nytimes.model.Result
import com.ashwani.nytimes.model.SectionedList
import com.ashwani.nytimes.network.NetworkResult
import com.ashwani.nytimes.utils.Constants
import com.ashwani.nytimes.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val dashboardViewModel by activityViewModels<DashboardViewModel>()
    private var sectionedList: ArrayList<SectionedList>? = ArrayList()
    private var sectionedListAdapter: SectionedListAdapter? = null

    private fun fetchLatestPosts() {
        showLoader()
        dashboardViewModel.fetchPost()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        fetchLatestPosts()
        populateAdapterWithInfo()
        setupSwipeRefreshLayout()
        return binding.root
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            sectionedList = null
            fetchLatestPosts()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel.postsResponseLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    hideLoader()
                    it.data?.let { it1 ->
                        setupLists(it.data.postType, it1.results)
                        sectionedListAdapter?.notifyDataSetChanged()
                    }
                }

                is NetworkResult.Error -> {
                    hideLoader()
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {
                }
            }
        })
    }

    private fun populateAdapterWithInfo() {
        sectionedListAdapter = SectionedListAdapter(requireContext(), sectionedList!!)
        sectionedListAdapter?.let {
            val layoutManager = LinearLayoutManager(context)
            binding.rvPosts.layoutManager = layoutManager
            binding.rvPosts.adapter = it
            binding.rvPosts.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setupLists(postType: Constants.PostType?, results: List<Result>) {
        val headerItem = SectionedList(ListViewType.HEADER, postType, null)
        sectionedList?.add(headerItem)
        for (i in 1..3) {
            sectionedList?.add(SectionedList(ListViewType.ITEM, null, results[i]))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        sectionedList = null
    }

    private fun showLoader() {
        binding.rvPosts.visibility = View.GONE
        binding.layoutLoader.myLoadingLayout.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.rvPosts.visibility = View.VISIBLE
        binding.layoutLoader.myLoadingLayout.visibility = View.GONE
    }
}