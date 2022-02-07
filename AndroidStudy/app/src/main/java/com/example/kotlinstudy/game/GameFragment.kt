package com.example.kotlinstudy.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.kotlinstudy.R
import com.example.kotlinstudy.databinding.FragmentGameBinding
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        binding = FragmentGameBinding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gameViewModel = viewModel
        binding.maxNoOfWords = MAX_NO_OF_WORDS
        binding.lifecycleOwner = viewLifecycleOwner

        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener { onSkipWord() }
//        updateNextWordOnScreen()
//        binding.score.text = getString(R.string.score, 0)
//        binding.wordCount.text = getString(
//            R.string.word_count, 0, MAX_NO_OF_WORDS
//        )
        //updateNextWordOnScreen메소드 대신에 livedata로 변경
        // -> view에 livedata 객체 바인딩해줘서 삭제함.
//        viewModel.currentScrambledWord.observe(viewLifecycleOwner,
//            { newWord ->
//                binding.textViewUnscrambledWord.text = newWord
//            })
        //score
//        viewModel.score.observe(viewLifecycleOwner,
//            { newScore ->
//                binding.score.text = getString(R.string.score, newScore)
//            })
        //wordCount
//        viewModel.currentWordCount.observe(viewLifecycleOwner,
//            { newWordCount ->
//                binding.wordCount.text =
//                    getString(R.string.word_count, newWordCount, MAX_NO_OF_WORDS)
//            })
    }

    private fun onSubmitWord() {
        val playerWord = binding.textInputEditText.text.toString()

        if (viewModel.isUserWordCorrect(playerWord)) {
            setErrorTextField(false)
            if (!viewModel.nextWord()) {
                showFinalScoreDialog()
            }
        } else {
            setErrorTextField(true)
        }
    }

        private fun onSkipWord() {
            if (viewModel.nextWord()) {
                setErrorTextField(false)
                //updateNextWordOnScreen()
            } else {
                showFinalScoreDialog()
            }
        }


        private fun restartGame() {
           viewModel.reinitializeData()
            setErrorTextField(false)
            //updateNextWordOnScreen()
        }

        private fun exitGame() {
            activity?.finish()
        }


        private fun setErrorTextField(error: Boolean) {
            if (error) {
                binding.textField.isErrorEnabled = true
                binding.textField.error = getString(R.string.try_again)
            } else {
                binding.textField.isErrorEnabled = false
                binding.textInputEditText.text = null
            }
        }

//    private fun updateNextWordOnScreen() {
//        binding.textViewUnscrambledWord.text = viewModel.currentScrambledWord
//    }

        private fun showFinalScoreDialog() {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.congratulations))
                .setMessage(getString(R.string.you_scored, viewModel.score.value))
                .setCancelable(false)
                .setNegativeButton(getString(R.string.exit)) { _, _ ->
                    exitGame()
                }
                .setPositiveButton(getString(R.string.play_again)) { _, _ ->
                    restartGame()
                }
                .show()
        }
    }


// 뷰모델 적용 전 함수들
/*
private fun onSkipWord() {
        currentScrambledWord = getNextScrambledWord()
        currentWordCount++
        binding.wordCount.text = getString(R.string.word_count, currentWordCount, MAX_NO_OF_WORDS)
        setErrorTextField(false)
        updateNextWordOnScreen()
    }*/