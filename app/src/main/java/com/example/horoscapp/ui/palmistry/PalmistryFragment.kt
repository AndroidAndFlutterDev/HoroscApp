package com.example.horoscapp.ui.palmistry

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PalmistryFragment : Fragment() {

    companion object {
        private const val CAMERA_PERMISSION = Manifest.permission.CAMERA
    }

    // Create the _binding that might be null
    private var _binding: FragmentPalmistryBinding? = null

    // This property access the binding of the Palmistry Fragment (access to its value, and it's not null). This is for the onCreateView, it's a read-only property
    private val binding get() = _binding!!

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startCamera()
        } else {
            // The user has denied the permission
            Toast.makeText(requireContext(), R.string.no_camera_permission, Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment, and return the binding (which can't be null)
        _binding = FragmentPalmistryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (checkCameraPermission()) {
            // Has allowed permission
            startCamera()
        } else {
            // Has not allowed permission
            requestPermissionLauncher.launch(CAMERA_PERMISSION)
        }
    }

    private fun startCamera() {

        // This is the provider of the camera
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
            }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try{
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview)
            }catch (e:Exception){
                Log.e("JulianAndroidDev", "Error: ${e.message}")
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun checkCameraPermission(): Boolean {

        /**
         * What we are doing in this code is checking if the option selected by the user is granted or not.
         * Then, we return True if it is, and False if it isn't
         */

        // We use the PermissionChecker to check if the user has granted the permission
        val permission = PermissionChecker.checkSelfPermission(
            requireContext(),
            CAMERA_PERMISSION
        ) == PermissionChecker.PERMISSION_GRANTED
        return permission
    }
}