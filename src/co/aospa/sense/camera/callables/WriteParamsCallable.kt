package co.aospa.sense.camera.callables

import co.aospa.sense.R
import co.aospa.sense.SenseApp
import co.aospa.sense.camera.listeners.CameraListener
import java.lang.Exception

class WriteParamsCallable(listener: CameraListener?) : CameraCallable(listener) {

    override fun call(): CallableReturn {
        val camera = cameraData.mCamera
            ?: return CallableReturn(Exception("Camera isn't opened"))
        return try {
            val parameters = getCameraParameters()
            if (SenseApp.app!!.resources.getBoolean(R.bool.config_cameraRecordingHint)) {
                parameters?.setRecordingHint(true)
            }
            camera.parameters = parameters
            CallableReturn(null)
        } catch (e: Exception) {
            CallableReturn(e)
        }
    }

    override val tag: String?
        get() = WriteParamsCallable::class.java.simpleName
}