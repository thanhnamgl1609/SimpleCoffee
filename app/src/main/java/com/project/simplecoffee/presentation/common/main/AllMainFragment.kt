package com.project.simplecoffee.presentation.common.main

import androidx.fragment.app.Fragment
import com.project.simplecoffee.domain.model.Order
import com.project.simplecoffee.domain.model.details.Role
import com.project.simplecoffee.presentation.common.setting.SettingFragment
import com.project.simplecoffee.presentation.contact.ContactDetailFragment
import com.project.simplecoffee.presentation.contact.ContactFragment
import com.project.simplecoffee.presentation.contact.ContactSelectFragment
import com.project.simplecoffee.presentation.inventory.MenuFragment
import com.project.simplecoffee.presentation.order.*
import com.project.simplecoffee.presentation.user.AccountInfoFragment
import com.project.simplecoffee.presentation.user.CartCustomerFragment
import com.project.simplecoffee.presentation.user.CartStaffFragment

sealed class AllMainFragment {
    object OrderDetail : AllMainFragment() {
        override fun createFragment(withRole: Role?) = OrderDetailStatsFragment()
        fun createFragment(orderID: String) =
            OrderDetailStatsFragment().apply { this.orderID = orderID }
    }

    object OrderDetailCustomer : AllMainFragment() {
        override fun createFragment(withRole: Role?) = OrderDetailCustomerFragment()
        fun createFragment(orderID: String) =
            OrderDetailCustomerFragment().apply { this.orderID = orderID }
    }

    object OrderDetailStaff : AllMainFragment() {
        override fun createFragment(withRole: Role?) = OrderDetailStaffFragment()
        fun createFragment(orderID: String) =
            OrderDetailStaffFragment().apply { this.orderID = orderID }
    }

    object OrderDetailTable : AllMainFragment() {
        override fun createFragment(withRole: Role?) = OrderDetailTableFragment()
        fun createFragment(orderID: String) =
            OrderDetailTableFragment().apply { this.orderID = orderID }
    }

    object TableDetail : AllMainFragment() {
        override fun createFragment(withRole: Role?) = TableDetailFragment()
        fun createFragment(tableID: String) =
            TableDetailFragment().apply { this.tableID = tableID }
    }

    object CurrentOrder : AllMainFragment() {
        override fun createFragment(withRole: Role?): Fragment {
            if (withRole !is Role.Customer)
                return OrderProcessingFragment()
            return OrderTrackingFragment()
        }
    }

    object AccountInfo : AllMainFragment() {
        override fun createFragment(withRole: Role?) = AccountInfoFragment()
    }

    object Setting : AllMainFragment() {
        override fun createFragment(withRole: Role?) = SettingFragment()
    }

    object Menu : AllMainFragment() {
        override fun createFragment(withRole: Role?) = MenuFragment()
    }

    object Contact : AllMainFragment() {
        override fun createFragment(withRole: Role?) = ContactFragment()
    }

    object ContactDetailFragment : AllMainFragment() {
        override fun createFragment(withRole: Role?) = ContactDetailFragment()
        fun createFragment(contact: com.project.simplecoffee.domain.model.Contact) =
            ContactDetailFragment().apply { this.contact = contact }
    }

    object Revenue : AllMainFragment() {
        override fun createFragment(withRole: Role?) = RevenueFragment()

    }

    object OrderHistory : AllMainFragment() {
        override fun createFragment(withRole: Role?) = OrderHistoryFragment()
    }

    object CartStaff : AllMainFragment() {
        override fun createFragment(withRole: Role?) = CartStaffFragment()
    }

    object CartCustomer : AllMainFragment() {
        override fun createFragment(withRole: Role?) = CartCustomerFragment()
    }

    object ContactSelect : AllMainFragment() {
        override fun createFragment(withRole: Role?) = ContactSelectFragment()
    }

    object TableStatus : AllMainFragment() {
        override fun createFragment(withRole: Role?) = TableStatusFragment()
    }

    abstract fun createFragment(withRole: Role? = null): Fragment
}