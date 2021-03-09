package com.ridhoafni.kitabisamoviedb.view.favorite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ridhoafni.kitabisamoviedb.R

class FavoriteMovieFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteMovieFragment()
    }

    private lateinit var viewModel: FavoriteMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteMovieViewModel::class.java)
        // TODO: Use the ViewModel
    }

}