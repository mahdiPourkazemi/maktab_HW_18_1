package com.pourkazemi.mahdi.maktab_hw_18_1.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pourkazemi.mahdi.maktab_hw_18_1.R
import com.pourkazemi.mahdi.maktab_hw_18_1.databinding.FragmentDetailBinding
import com.pourkazemi.mahdi.maktab_hw_18_1.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding: FragmentDetailBinding by viewBinding(
        FragmentDetailBinding ::bind)

    private val argument: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailPerson=argument.receivedPerson
        binding.apply {
            fullName.text = detailPerson.firstName + detailPerson.lastName
            nationalCode.text=detailPerson.nationalCode

            imageDetail.setOnClickListener {
               //#todo editing imageDetail
            }

/*            detailPerson.let { person ->
                Glide.with(binding.root)
                    .load(person.image)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                    )
                    .into(imageDetail)
            }*/
        }
    }
}