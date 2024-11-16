package com.example.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.isVisible
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random

@AndroidEntryPoint
class LuckFragment : Fragment() {

    // Create the _binding that might be null
    private var _binding: FragmentLuckBinding? = null

    // This property access the binding of the luck Fragment (access to its value, and it's not null). This is for the onCreateView, it's a read-only property
    private val binding get() = _binding!!

    // Once the View is created, this code will be executed
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
    }

    // This function will initialize the UI (generally)
    private fun initUI() {
        initListeners()
    }

    private fun initListeners() {
        binding.ivLuckyWheel.setOnClickListener { spinWheel() }
    }

    // This function will rotate the roulette
    private fun spinWheel() {

        // The animation below it's one of the most basics animations in all of Android

        // The "RANDOM" library allows us to generate random numbers
        val random = Random()
        val degrees = random.nextInt(1440) + 720

        // This is the object that "animates" the View
        val animator = ObjectAnimator.ofFloat(binding.ivLuckyWheel, View.ROTATION, 0f, degrees.toFloat())

        // The animation will last 2 seconds
        animator.duration = 2000

        // This feature makes the roulette to go slowing down, until it's completely stopped
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    // This function will slide the card from the roulette
    private fun slideCard(){

        // The animation below will be created as an Animation res file
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        /**
         * This handles the animation state
         * Why is it an AnimationListener?
         * Because, before, we could set the doOnEnd {  }, or doOnStart, etc, but for an
         * animation created in an XML, we can't do that. We need to set a Listener, by passing it
         * as an object, that contains three "states" of the animation
         * What it does when it starts
         * What it does when it ends
         * And what it does when it as to be repeated

         */

        // This function will handle the "going-up" animation, for the card
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.ivSmallCard.isVisible = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                growCard()
            }

            override fun onAnimationRepeat(p0: Animation?) {}

        })

        // Starts the animation, once we already managed it
        binding.ivSmallCard.startAnimation(slideUpAnimation)
    }

    // This function will handle the "getting-bigger" animation, for the card
    fun growCard() {

        // This sets the pivots to the required value so the card will expand equally in both directions
        binding.ivSmallCard.apply {
            pivotX = this.width / 2f
            pivotY = this.height / 2f
        }

        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow_card)

        growAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.ivSmallCard.isVisible = false
                showPredictionView()
            }

            override fun onAnimationRepeat(p0: Animation?) {}

        })

        // Start the animation, with the already defined animation
        binding.ivSmallCard.startAnimation(growAnimation)
    }

    // This function will handle the "showing-premonition" animation
    private fun showPredictionView() {

        /**
         * This function called "AlphaAnimation", takes two values of some View:
         * In this case, it takes a value on a 100% opacity, and takes it to a 0% opacity
         * (Everyting is a Float value)
         */
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)

        /**
         * This function called "AlphaAnimation", takes two values of some View:
         * In this case, it takes a value on a 0% opacity, and takes it to a 100% opacity
         * (Everyting is a Float value)
         */

        val appearAnimation = AlphaAnimation(0.0f, 1.0f)

        disappearAnimation.duration = 200

        appearAnimation.duration = 1000

        // This function handles the "disappearing" animation
        disappearAnimation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {  }

            override fun onAnimationEnd(p0: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }

            override fun onAnimationRepeat(p0: Animation?) {  }

        })

        // Apply the disappearing animation to the Main View
        binding.preview.startAnimation(disappearAnimation)

        binding.prediction.startAnimation(appearAnimation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}