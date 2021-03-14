package com.ridhoafni.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridhoafni.core.domain.model.Movie
import com.ridhoafni.core.ui.MovieAdapter
import com.ridhoafni.core.utils.ItemDecorator
import com.ridhoafni.core.utils.gone
import com.ridhoafni.core.utils.visible
import com.ridhoafni.favorite.di.favoriteModule
import com.ridhoafni.kitabisamoviedb.R
import com.ridhoafni.kitabisamoviedb.databinding.FavoriteMovieFragmentBinding
import com.ridhoafni.kitabisamoviedb.view.detail.DetailMovieFragment.Companion.favoriteFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteMovieFragment : Fragment() {

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModel()
    private var _binding: FavoriteMovieFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteMovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.favorite_movie)
        setHasOptionsMenu(true)
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        val favoriteMovieAdapter = MovieAdapter()
        favoriteMovieViewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
            if (movies.isNotEmpty()) {
                binding.progressBar.gone()
                binding.recyclerViewFavoriteMovie.visible()
                movies.let {
                    favoriteMovieAdapter.setData(it)
                }
            }else{
                binding.progressBar.gone()
                binding.recyclerViewFavoriteMovie.gone()
                binding.viewEmpty.contentEmpty.visible()
            }
        })

        with(binding.recyclerViewFavoriteMovie) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(ItemDecorator(resources.getDimensionPixelSize(R.dimen.radius),resources.getDimensionPixelSize(R.dimen.radius)))
            setHasFixedSize(true)
            adapter = favoriteMovieAdapter
        }

        favoriteMovieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback{
            override fun onItemClicked(movie: Movie) {
                val toDetailFavoriteMovieFragment =
                    FavoriteMovieFragmentDirections.actionFavoriteMovieFragmentToDetailMovieFragment(
                        movie,
                        favoriteFragment
                    )
                findNavController().navigate(toDetailFavoriteMovieFragment)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            val toMovieFragment =
                FavoriteMovieFragmentDirections.actionFavoriteMovieFragmentToMovieFragment()
            findNavController().navigate(toMovieFragment)
        }
        return super.onOptionsItemSelected(item)
    }

}