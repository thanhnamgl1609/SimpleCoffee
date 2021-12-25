package com.project.simplecoffee.presentation.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.project.simplecoffee.R
import com.project.simplecoffee.databinding.FragmentOrderDetailCustomerBinding
import com.project.simplecoffee.domain.model.Order
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OrderDetailCustomerFragment : Fragment() {

    @Inject
    lateinit var orderDetailVM: OrderDetailCustomerVM
    private lateinit var binding: FragmentOrderDetailCustomerBinding
    var orderID: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_order_detail_customer,
            container, false
        )
        binding.apply {
            viewModel = orderDetailVM
            lifecycleOwner = viewLifecycleOwner
        }
        orderID?.apply { orderDetailVM.setOrder(this) }
        return binding.root
    }
}