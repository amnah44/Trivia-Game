package com.example.triviatask.ui.game

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.triviatask.R
import com.example.triviatask.databinding.FragmentGameBinding
import com.example.triviatask.ui.base.BaseFragment

class GameFragment : BaseFragment<FragmentGameBinding>() {

    val args: GameFragmentArgs by navArgs()
    override val LOG_TAG: String = "HOME_FRAGMENT"
    override val layoutId: Int = R.layout.fragment_game
    override val viewModel: GameViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentGameBinding =
        DataBindingUtil::inflate

    @SuppressLint("SetTextI18n")
    override fun setUp() {

        binding?.apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = this@GameFragment.viewModel

            viewModel?.positionOfQuestion?.observe(this@GameFragment) {
                this.progressOfTotalQuestion.text = "Question Number " +
                        "${it.plus(1)} /5"
            }

            this.recyclerOfQuestion.adapter =
                OptionsAdapter(mutableListOf(), this@GameFragment.viewModel)

        }
        setScoreToResultNavigation()
        startGame()

    }

    private fun startGame() {
        args.gameConfiguration.apply {
            viewModel.getQuestion(
                questionNumber,
                categoryGameId,
                difficultyGame,
                gameType
            )
        }
    }

    private fun setScoreToResultNavigation() {
        viewModel.scoreOfQuestionEvent.observe(this) {
            view?.let { viewNavigation ->
                Navigation
                    .findNavController(viewNavigation)
                    .navigate(
                        GameFragmentDirections
                            .actionGameFragmentToResultFragment(
                                it,
                                args.gameConfiguration.questionNumber
                            )
                    )
            }
        }
    }
}