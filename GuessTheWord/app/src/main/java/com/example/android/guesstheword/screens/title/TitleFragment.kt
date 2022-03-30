/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.title

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.TitleFragmentBinding
import com.example.android.guesstheword.screens.game.GameFragmentDirections
import com.example.android.guesstheword.screens.game.GameViewModel
import kotlinx.android.synthetic.main.game_fragment.*
import kotlinx.android.synthetic.main.title_fragment.*


/**
 * Fragment for the starting or title screen of the app
 */
class TitleFragment: Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // Inflate the layout for this fragment
        val binding: TitleFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.title_fragment, container, false)

        binding.playGameButton.setOnClickListener{
            val action = TitleFragmentDirections.actionTitleToGame()
            if (binding.switchButton.isChecked) {

                action.seconds = binding.slider1.value.toInt()
                findNavController().navigate(action)
            }
            else{
                findNavController().navigate(TitleFragmentDirections.actionTitleToGame())

            }



        }

        binding.switchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Switch is enabled and slider is enabled
                binding.slider1.setEnabled(true)
            } else {
                // seekbar is disabled
                binding.slider1.setEnabled(false)


            }
        }



        return binding.root

    }
}
