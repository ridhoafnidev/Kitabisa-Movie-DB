package com.ridhoafni.kitabisamoviedb.view.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.ridhoafni.core.data.Resource
import com.ridhoafni.core.domain.model.Movie
import com.ridhoafni.kitabisamoviedb.BuildConfig
import com.ridhoafni.core.utils.gone
import com.ridhoafni.core.utils.visible
import com.ridhoafni.kitabisamoviedb.R
import com.ridhoafni.kitabisamoviedb.databinding.DetailMovieFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieFragment : Fragment() {

    private val movieDetailViewModel: DetailMovieViewModel by viewModel()
    private var _binding: DetailMovieFragmentBinding? = null
    private val binding get() = _binding!!
    private var movie: Movie? = null
    private var type: String? = null

    companion object{
        const val favoriteFragment = "favorite_fragment"
        const val movieFragment = "movie_fragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailMovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        arguments?.let { data ->
            movie = DetailMovieFragmentArgs.fromBundle(data).data
            type = DetailMovieFragmentArgs.fromBundle(data).type
        }
        getMovie()
    }

    private fun getMovie() {
        movie?.id?.let { it ->
            movieDetailViewModel.getMovie(it).observe(viewLifecycleOwner) { dataMovie ->
                if (dataMovie != null) {
                    when (dataMovie) {
                        is Resource.Success -> {
                            binding.progressBar.gone()
                            binding.viewDetailMovie.apply {
                                movie = dataMovie.data
                                (activity as AppCompatActivity).supportActionBar?.title = dataMovie.data?.title
                                Glide.with(requireActivity())
                                    .load("${BuildConfig.IMAGE_ENDPOINT}${dataMovie.data?.posterPath}")
                                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                                    .into(ivPoster)
                                tvMovieTitle.text = dataMovie.data?.title
                                tvOverview.text = dataMovie.data?.overview
                                tvReleaseDate.text = dataMovie.data?.releaseDate
                                dataMovie.data?.favorite?.let {
                                    setFavorite(it)
                                }
                                btnFavorite.setOnClickListener {
                                    dataMovie.data?.let {
                                        val newStateFavorite = !it.favorite
                                        movieDetailViewModel.setFavoriteMovie(it, newStateFavorite)
                                        setFavorite(newStateFavorite)
                                        showSnackBar(newStateFavorite)
                                    }
                                }
                            }
                        }

                        is Resource.Loading -> {
                            binding.progressBar.visible()
                        }

                        is Resource.Error -> {
                            binding.progressBar.gone()
                            binding.viewError.tvError.visible()
                            binding.viewDetailMovie.viewMovie.gone()
                        }
                    }

                }
            }
        }
    }

    private fun showSnackBar(favorite: Boolean) {
        if (favorite){
            val snackbar = Snackbar.make(view as View, resources.getString(R.string.add_favorite_text, movie?.title), Snackbar.LENGTH_LONG)
            snackbar.show()
        }else{
            val snackbar = Snackbar.make(view as View, resources.getString(R.string.delete_favorite_text, movie?.title), Snackbar.LENGTH_LONG)
            snackbar.setAction(R.string.message_ok) {
                movie?.let { movieDetailViewModel.setFavoriteMovie(it, !it.favorite) }
            }
            snackbar.show()
        }
    }

    private fun setFavorite(favorite: Boolean) {
        if (favorite){
            binding.viewDetailMovie.btnFavorite.setImageDrawable(context?.let {
                ContextCompat.getDrawable(
                    it, R.drawable.ic_favorite)
            })
        }else{
            binding.viewDetailMovie.btnFavorite.setImageDrawable(context?.let {
                ContextCompat.getDrawable(
                    it, R.drawable.ic_favorite_border)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.share_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            if (type == movieFragment){
                val toMovieFragment = DetailMovieFragmentDirections.actionDetailMovieFragmentToMovieFragment()
                findNavController().navigate(toMovieFragment)
            }else{
                val toFavoriteFragment = DetailMovieFragmentDirections.actionDetailMovieFragmentToFavoriteMovieFragment()
                findNavController().navigate(toFavoriteFragment)
            }

        }else if(item.itemId == R.id.action_share){
            activity?.let {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder
                    .from(it)
                    .setType(mimeType)
                    .setChooserTitle(getString(R.string.title_share))
                    .setText(resources.getString(R.string.share_text, movie?.title))
                    .startChooser()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}