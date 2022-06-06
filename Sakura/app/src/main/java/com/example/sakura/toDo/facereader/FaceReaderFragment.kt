//package com.example.sakura.toDo.facereader
//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import android.widget.ImageView
//import androidx.camera.core.AspectRatio.RATIO_16_9
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import com.example.sakura.R
//import com.example.sakura.databinding.FragmentFaceReaderBinding
//import com.example.sakura.toDo.home.HomeFragment
//import java.lang.Exception
//
//class FaceReaderFragment: Fragment() {
//    private var _binding: FragmentFaceReaderBinding? =  null
//    private val binding get() = _binding!!
//    private lateinit var menuButton: ImageView
//    private var url = "https://stream.facereader-online.com/experiment?ExperimentID=584a11bfca264282a521b1aecb142344"
//    private lateinit var webView: WebView
//    private var imageCapture:ImageCapture? = null
//
//    companion object{
//        private const val REQUEST_CODE : Int = 123
//        private const val REQUEST_RECORD_AUDIO_PERMISSION = 200
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentFaceReaderBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if(!checkCameraPermission())
//            askForCameraPermission()
//    }
//
//    fun checkCameraPermission() : Boolean{
//        if(this.context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) } == PackageManager.PERMISSION_GRANTED )
//        {
//            return true
//        }
//        return false
//    }
//
//    fun checkAudioPermission() : Boolean{
//        if(this.context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.RECORD_AUDIO) } == PackageManager.PERMISSION_GRANTED )
//        {
//            return true
//        }
//        return false
//    }
//
//    fun askForAudioPermission(){
//        requestPermissions(
//            arrayOf(Manifest.permission.RECORD_AUDIO),
//            FaceReaderFragment.REQUEST_RECORD_AUDIO_PERMISSION
//        )
//    }
//
//    fun askForCameraPermission(){
//        requestPermissions(
//            arrayOf(Manifest.permission.CAMERA),
//            FaceReaderFragment.REQUEST_CODE
//        )
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        if(requestCode == FaceReaderFragment.REQUEST_CODE)
//        {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                startCamera()
//        }
//    }
//
//    private fun startCamera(){
//        val cameraProviderFuture = context?.let { ProcessCameraProvider.getInstance(it) }
//        cameraProviderFuture?.addListener({
//
//            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
//
//            val preview = Preview.Builder()
//                .build()
//
//            imageCapture = ImageCapture.Builder()
//                .setTargetAspectRatio(RATIO_16_9)
//                .build()
//            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//            try {
//                cameraProvider.unbindAll()
//                cameraProvider.bindToLifecycle(
//                    this,
//                    cameraSelector,
//                    preview,
//                    imageCapture
//                )
//
//            }catch (e: Exception){
//                Log.d("Camera","Camera not working")
//            }
//
//        },ContextCompat.getMainExecutor(requireContext()))
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        webView = binding.webViewFaceTask
//        webView.settings.javaScriptEnabled = true;
//        webView.webViewClient = WebViewClient()
//        webView.loadUrl(url)
//
//        menuButton = binding.menuButton
//        menuButton.setOnClickListener{
//            inflateHomeLayout()
//        }
//    }
//
//    private fun inflateHomeLayout()
//    {
//        val transaction = parentFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container, HomeFragment())
//        transaction.commit()
//    }
//}