package com.descifrador.learnfragments.score

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.descifrador.learnfragments.R
import kotlinx.android.synthetic.main.score_fragement_fragment.*
import kotlinx.android.synthetic.main.score_fragement_fragment.view.*


class ScoreFragement : Fragment() {

    companion object {
        fun newInstance() = ScoreFragement()
    }

    private lateinit var viewModel: ScoreFragementViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.score_fragement_fragment, container, false)

        root.plus3A.setOnClickListener {
            viewModel.scoreTeamA+=3
            scoreTeamA.text = viewModel.scoreTeamA.toString();
        }
        root.plus2A.setOnClickListener {
            viewModel.scoreTeamA += 2
            scoreTeamA.text = viewModel.scoreTeamA.toString();
        }
        root.plus3B.setOnClickListener {
            viewModel.scoreTeamB += 3
            scoreTeamB.text = viewModel.scoreTeamB.toString();
        }
        root.plus2B.setOnClickListener {
            viewModel.scoreTeamB += 2
            scoreTeamB.text = viewModel.scoreTeamB.toString();
        }
        root.freeA.setOnClickListener {
            viewModel.scoreTeamA += 1
            scoreTeamA.text = viewModel.scoreTeamA.toString();
        }
        root.freeB.setOnClickListener {
            viewModel.scoreTeamB += 1
            scoreTeamB.text = viewModel.scoreTeamB.toString();
        }

        root.reset.setOnClickListener {
            var win = "";
            if(viewModel.scoreTeamA > viewModel.scoreTeamB){
                win = "Team A"
            }
            else{
                win = "Team B"
            }

            Toast.makeText(context,"Winner is $win",Toast.LENGTH_SHORT).show()

            viewModel.scoreTeamA = 0
            viewModel.scoreTeamB = 0
            scoreTeamA.text = viewModel.scoreTeamA.toString();
            scoreTeamB.text = viewModel.scoreTeamB.toString();
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ScoreFragementViewModel::class.java)
        // TODO: Use the ViewModel

        scoreTeamA.text = viewModel.scoreTeamA.toString();
        scoreTeamB.text = viewModel.scoreTeamB.toString();

    }



}
