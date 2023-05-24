package com.tavant.demo.ui.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tavant.demo.R
import com.tavant.demo.databinding.FragmentDetailBinding
import com.tavant.demo.util.extensions.toastMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val webViewModel: DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webViewToolbar.appTitle.text = getText(R.string.webViewToolbar)
        setData()
        clickFloatingButton()
        checkFavoriteIcon()
        clickBackButton()
        initObservers()
    }

    private fun setData(){
        binding.itemTitleTextView.text=args.product.title
        binding.itemDescriptionTextView.text=args.product.description
        Glide.with(this).load(args.product.image).into(binding.itemImageView)

    }

    private fun checkFavoriteIcon(){
        if (!args.fromFavorites && !args.product.isFav)
            binding.floatingButton.setImageResource(R.drawable.fav_icon_outline)
        else if (args.fromHome)
            binding.floatingButton.setImageResource(R.drawable.fav_icon_fill)
        else
            binding.floatingButton.visibility = View.GONE
    }

    private fun clickFloatingButton(){
        binding.floatingButton.setOnClickListener {
            webViewModel.addFavoriteArticle(args.product)
        }
    }

    private fun initObservers(){
        webViewModel.isAddedFavorite.observe(viewLifecycleOwner){
            if (it){
                binding.floatingButton.setImageResource(R.drawable.fav_icon_fill)
                requireContext().toastMessage(getString(R.string.article_added_message))
            }
            else{
                requireContext().toastMessage(getString(R.string.article_already_saved))
            }
        }
    }

    private fun clickBackButton(){
        binding.webViewToolbar.customToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}