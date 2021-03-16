package com.ridhoafni.kitabisamoviedb.view.movie

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ridhoafni.core.data.Resource
import com.ridhoafni.core.domain.model.Movie
import com.ridhoafni.core.ui.MovieAdapter
import com.ridhoafni.core.utils.ItemDecorator
import com.ridhoafni.core.utils.gone
import com.ridhoafni.core.utils.visible
import com.ridhoafni.kitabisamoviedb.R
import com.ridhoafni.kitabisamoviedb.databinding.MovieFragmentBinding
import com.ridhoafni.kitabisamoviedb.databinding.SheetFloatingBinding
import com.ridhoafni.kitabisamoviedb.view.detail.DetailMovieFragment.Companion.movieFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private var _binding: MovieFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mBehavior: BottomSheetBehavior<FrameLayout>
    private lateinit var mBottomSheetDialog: BottomSheetDialog
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        movieAdapter = MovieAdapter()

        binding.category.setOnClickListener { showBottomSheetDialog() }

        getMovies()

        with(binding.recyclerViewMovie) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                ItemDecorator(
                    resources.getDimensionPixelSize(R.dimen.radius),
                    resources.getDimensionPixelSize(
                        R.dimen.radius
                    )
                )
            )
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: Movie) {
                val toDetailGithubFragment =
                    MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(
                        movie,
                        movieFragment
                    )

                findNavController().navigate(toDetailGithubFragment)
            }
        })
    }

    private fun showBottomSheetDialog() {
        mBehavior = BottomSheetBehavior.from(binding.bottomSheet)

        if (mBehavior.state === BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val sheetBinding = SheetFloatingBinding.inflate(layoutInflater)

        mBottomSheetDialog = context?.let { BottomSheetDialog(it) }!!

        sheetBinding.btClose.setOnClickListener {
            mBottomSheetDialog.hide()
        }

        sheetBinding.ripplePopular.setOnClickListener {
            getPopularMovies()
            mBottomSheetDialog.hide()
        }

        sheetBinding.rippleNowPlaying.setOnClickListener {
            getNowPlayingMovies()
            mBottomSheetDialog.hide()
        }

        sheetBinding.rippleTopRated.setOnClickListener {
            getTopRatedMovies()
            mBottomSheetDialog.hide()
        }

        sheetBinding.rippleUpcoming.setOnClickListener {
            getUpcomingMovies()
            mBottomSheetDialog.hide()
        }

        mBottomSheetDialog.setContentView(sheetBinding.root)

        mBottomSheetDialog.show()

    }

    private fun getUpcomingMovies() {
        movieViewModel.getUpcomingMovies().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                        binding.recyclerViewMovie.gone()
                    }
                    is Resource.Success -> {
                        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.upcoming)
                        binding.progressBar.gone()
                        binding.recyclerViewMovie.visible()
                        movieAdapter.setData(movies.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        binding.viewError.tvError.visible()
                    }
                }
            }
        })
    }

    private fun getTopRatedMovies() {
        movieViewModel.getTopRatedMovies().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                        binding.recyclerViewMovie.gone()
                    }
                    is Resource.Success -> {
                        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.top_rated)
                        binding.progressBar.gone()
                        binding.recyclerViewMovie.visible()
                        movieAdapter.setData(movies.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        binding.viewError.tvError.visible()
                    }
                }
            }
        })
    }

    private fun getNowPlayingMovies() {
        movieViewModel.getNowPlayingMovies().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                        binding.recyclerViewMovie.gone()
                    }
                    is Resource.Success -> {
                        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.now_playing)
                        binding.progressBar.gone()
                        binding.recyclerViewMovie.visible()
                        movieAdapter.setData(movies.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        binding.viewError.tvError.visible()
                    }
                }
            }
        })
    }

    private fun getPopularMovies() {
        movieViewModel.getPopularMovies().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                        binding.recyclerViewMovie.gone()
                    }
                    is Resource.Success -> {
                        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.popular)
                        binding.progressBar.gone()
                        binding.recyclerViewMovie.visible()
                        movieAdapter.setData(movies.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        binding.viewError.tvError.visible()
                    }
                }
            }
        })
    }

    private fun getMovies() {
        movieViewModel.getPopularMovies().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                        binding.recyclerViewMovie.gone()
                    }
                    is Resource.Success -> {
                        binding.progressBar.gone()
                        binding.recyclerViewMovie.visible()
                        movieAdapter.setData(movies.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        binding.viewError.tvError.visible()
                    }
                }
            }
        })
    }

    override fun onDestroy() {
//        binding.recyclerViewMovie.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener{
//            override fun onViewAttachedToWindow(v: View) {}
//            override fun onViewDetachedFromWindow(v: View) {
//                binding.recyclerViewMovie.adapter=null
//            }
//        })
        super.onDestroy()
        _binding = null
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite){
            val toFavoriteFragment = MovieFragmentDirections.actionMovieFragmentToFavoriteMovieFragment()
            findNavController().navigate(toFavoriteFragment)
        }
        return super.onOptionsItemSelected(item)
    }

}